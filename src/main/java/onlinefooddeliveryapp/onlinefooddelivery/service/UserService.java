package onlinefooddeliveryapp.onlinefooddelivery.service;

import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Users;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.PlaceOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.SignUpUserRequest;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;

import java.util.List;

public interface UserService {
    Users signUpUser(SignUpUserRequest signUpUserRequest);

    Restaurants userCanBrowseRestaurantById(String id, String restaurantId);

    Restaurants userCanBrowseRestaurantByRestaurantName(String id, String restaurantName);

    List<MenuItems> userCanViewRestaurantMenu(String restaurantId, String id);

    Users userCanPlaceOrderInARestaurant(PlaceOrderRequest placeOrderRequest) throws OrderCannotBeFoundException,  OrderAlreadyExistException;


}
