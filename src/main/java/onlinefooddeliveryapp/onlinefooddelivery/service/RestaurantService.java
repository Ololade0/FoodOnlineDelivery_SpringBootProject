package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllRestaurantRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.AddMenuItemResponse;
import org.springframework.data.domain.Page;

public interface RestaurantService {
    Restaurants addNewResstaurant(Restaurants newRestaurant);

    Restaurants browseRestaurantById(String restaurantId) ;

    Restaurants browseRestaurantByName(String restaurantName) ;

    Page<Restaurants> browseAllRestaurant(FindAllRestaurantRequest findAllRestaurantRequest);

    AddMenuItemResponse saveMenuItem(AddMenuItemRequest addMenuItem);

    void deleteAll();
}
