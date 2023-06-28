package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllRestaurantRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.RegisterRestaurantRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateMenuRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.AddMenuItemResponse;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuItemCannotBeFoundException;
import org.springframework.data.domain.Page;

public interface RestaurantService {
    Restaurants addNewResstaurant(RegisterRestaurantRequest registerRestaurantRequest);

    Restaurants browseRestaurantById(String restaurantId) ;

    Restaurants browseRestaurantByName(String restaurantName) ;

    Page<Restaurants> browseAllRestaurant(FindAllRestaurantRequest findAllRestaurantRequest);

    AddMenuItemResponse saveMenuItem(AddMenuItemRequest addMenuItem) throws MenuAlreadyExistException;

    void deleteAll();

    MenuItems findMenuById(String restaurantId, String menuId) throws MenuItemCannotBeFoundException;

    String  deleteAllMenu(String restaurantId);

    MenuItems findMenuByName(String restaurantId, String menuName) throws MenuItemCannotBeFoundException;

  String deleteMenuById(String restaurantId, String menuId) throws MenuItemCannotBeFoundException;

    String updateMenuItem(UpdateMenuRequest updateMenuRequest, String restaurantId) throws MenuItemCannotBeFoundException;
}
