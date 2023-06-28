package onlinefooddeliveryapp.onlinefooddelivery.service;

import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Users;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.*;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.UpdateUserResponse;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.UserLoginResponse;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Users signUpUser(SignUpUserRequest signUpUserRequest);


    Users findUserByEmail(String email);

    Users findUserByuserId(String id);

    Users findUserByName(String firstName);

    String deleteUserById(String id);

    String deleteAllUsers();

    UserLoginResponse login(UserLoginRequestModel userLoginRequestModel);

    Page<Users> findAllUser(FindAllUserRequest findAllUser);

    UpdateUserResponse updateUserProfile(UpdateUserProfileRequest updateUserProfile);

    Restaurants userCanBrowseRestaurantById(String id, String restaurantId);

    Restaurants userCanBrowseRestaurantByRestaurantName(String id, String restaurantName);

    List<MenuItems> userCanViewRestaurantMenu(String restaurantId, String id);

    Users userCanPlaceOrderInARestaurant(PlaceOrderRequest placeOrderRequest) throws OrderCannotBeFoundException,  OrderAlreadyExistException;


    Page<Restaurants> userCanBrowseAllRestaurants(FindAllRestaurantRequest findAllRestaurantRequest, String id);
}
