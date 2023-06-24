package onlinefooddeliveryapp.onlinefooddelivery.service;

import com.example.onlinefooddelivery.dao.model.MenuItems;
import com.example.onlinefooddelivery.dao.model.Restaurants;
import com.example.onlinefooddelivery.dao.model.Users;
import com.example.onlinefooddelivery.dto.request.PlaceOrderRequest;
import com.example.onlinefooddelivery.dto.request.SignUpUserRequest;
import com.example.onlinefooddelivery.exception.OrderAlreadyExistException;
import com.example.onlinefooddelivery.exception.OrderCannotBeFoundException;

import java.util.List;

public interface UserService {
    Users signUpUser(SignUpUserRequest signUpUserRequest);

    Restaurants userCanBrowseRestaurantById(String id, String restaurantId);

    Restaurants userCanBrowseRestaurantByRestaurantName(String id, String restaurantName);

    List<MenuItems> userCanViewRestaurantMenu(String restaurantId, String id);

    Users userCanPlaceOrderInARestaurant(PlaceOrderRequest placeOrderRequest) throws OrderCannotBeFoundException, OrderAlreadyExistException;


//    String userCanPlaceOrderInARestaurant(String restaurantId, String id);
//
//    Order placedOrders(PlaceOrderRequest placeOrderRequest);

//    Users userCanBrowseRestaurant(String id);
}
