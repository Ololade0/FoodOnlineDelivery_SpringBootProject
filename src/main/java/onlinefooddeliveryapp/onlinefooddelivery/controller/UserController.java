package onlinefooddeliveryapp.onlinefooddelivery.controller;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.TokenProvider;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.AuthToken;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Users;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.PlaceOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.SignUpUserRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UserLoginRequestModel;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.UserCannotBeFoundException;
import onlinefooddeliveryapp.onlinefooddelivery.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;


    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@NonNull @RequestBody SignUpUserRequest signUpUserRequest)  {
        Users user = userService.signUpUser(signUpUserRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestModel loginRequest) throws UserCannotBeFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword())
        );
        userService.login(loginRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateJWTToken(authentication);
        Users user = userService.findUserByEmail(loginRequest.getEmail());
        return new ResponseEntity<>(new AuthToken(token, user.getId()), HttpStatus.OK);
    }



    @GetMapping("/{restaurantName}")
    public ResponseEntity<?> browseRestaurantName(@RequestBody  String userId, @PathVariable String restaurantName)  {
        Restaurants restaurants = userService.userCanBrowseRestaurantByRestaurantName(userId, restaurantName);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }



    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> browseRestaurantById(@RequestBody  String userId, @PathVariable String restaurantId)  {
        Restaurants restaurants = userService.userCanBrowseRestaurantById(userId, restaurantId);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<?> userCanPlaceOrderInARestaurant(@RequestBody PlaceOrderRequest placeOrderRequest) throws OrderCannotBeFoundException, OrderAlreadyExistException, OrderAlreadyExistException {
        Users placeOrder = userService.userCanPlaceOrderInARestaurant(placeOrderRequest);
        return new ResponseEntity<>(placeOrder, HttpStatus.OK);
    }

    @GetMapping("menuitem/{restaurantId}")
    public ResponseEntity<?> userCanViewRestaurantMenu(@RequestBody String userId,@PathVariable String restaurantId) {
        List<MenuItems> menuItemsList = userService.userCanViewRestaurantMenu(userId, restaurantId);
        return new ResponseEntity<>(menuItemsList, HttpStatus.OK);
    }



}
