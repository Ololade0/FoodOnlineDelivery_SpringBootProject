package onlinefooddeliveryapp.onlinefooddelivery.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Users;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.PlaceOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.SignUpUserRequest;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;
import onlinefooddeliveryapp.onlinefooddelivery.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@NonNull @RequestBody SignUpUserRequest signUpUserRequest)  {
        Users user = userService.signUpUser(signUpUserRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
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
    public ResponseEntity<?> userCanPlaceOrderInARestaurant(@RequestBody PlaceOrderRequest placeOrderRequest) throws OrderCannotBeFoundException, OrderAlreadyExistException {
        Users placeOrder = userService.userCanPlaceOrderInARestaurant(placeOrderRequest);
        return new ResponseEntity<>(placeOrder, HttpStatus.OK);
    }

    @GetMapping("menuitem/{restaurantId}")
    public ResponseEntity<?> userCanViewRestaurantMenu(@RequestBody String userId,@PathVariable String restaurantId) {
        List<MenuItems> menuItemsList = userService.userCanViewRestaurantMenu(userId, restaurantId);
        return new ResponseEntity<>(menuItemsList, HttpStatus.OK);
    }



}
