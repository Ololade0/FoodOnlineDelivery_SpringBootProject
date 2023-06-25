package onlinefooddeliveryapp.onlinefooddelivery.controller;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.AuthToken;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Users;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.PlaceOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.SignUpUserRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateUserProfileRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UserLoginRequestModel;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.UpdateUserResponse;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.UserCannotBeFoundException;
import onlinefooddeliveryapp.onlinefooddelivery.security.jwt.TokenProvider;
import onlinefooddeliveryapp.onlinefooddelivery.service.RestaurantService;
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
    private final RestaurantService restaurantService;
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


    @GetMapping("user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable String id){
        Users user = userService.findUserByuserId(id);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("user/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email){
        Users user = userService.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("user/{name}")
    public ResponseEntity<?> findUserByName(@PathVariable String name){
        Users user = userService.findUserByName(name);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @DeleteMapping("deleteuser/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllUsers(){
        return new ResponseEntity<>(userService.deleteAllUsers(), HttpStatus.CREATED);
    }


    @PutMapping("/updateuser")
    public ResponseEntity<?> updateUsers(@RequestBody UpdateUserProfileRequest updateUserProfile){
        UpdateUserResponse updatedUser = userService.updateUserProfile(updateUserProfile);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<?> addNewRestaurant(@RequestBody  Restaurants restaurants)  {
        Restaurants savedRestaurant = restaurantService.addNewResstaurant(restaurants);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{restaurantId}")
    public ResponseEntity<?> userCanBrowseRestaurantById(@PathVariable String userId, @PathVariable String restaurantId )  {
        Restaurants savedRestaurant = userService.userCanBrowseRestaurantById(userId,restaurantId);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.OK);
    }





    @GetMapping("name/{userId}/{restaurantName}")
    public ResponseEntity<?> browseRestaurantName(@PathVariable  String userId, @PathVariable String restaurantName)  {
        Restaurants restaurants = userService.userCanBrowseRestaurantByRestaurantName(userId, restaurantName);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }




    @PostMapping("/placeOrder")
    public ResponseEntity<?> userCanPlaceOrderInARestaurant(@RequestBody PlaceOrderRequest placeOrderRequest) throws OrderCannotBeFoundException,  OrderAlreadyExistException {
        Users placeOrder = userService.userCanPlaceOrderInARestaurant(placeOrderRequest);
        return new ResponseEntity<>(placeOrder, HttpStatus.OK);
    }

    @GetMapping("menuitem/{restaurantId}/{userId}")
    public ResponseEntity<?> userCanViewRestaurantMenu(@PathVariable String restaurantId,@PathVariable String userId) {
        List<MenuItems> menuItemsList = userService.userCanViewRestaurantMenu(restaurantId, userId);
        return new ResponseEntity<>(menuItemsList, HttpStatus.OK);
    }



}
