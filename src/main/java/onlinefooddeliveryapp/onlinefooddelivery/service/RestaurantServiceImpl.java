package onlinefooddeliveryapp.onlinefooddelivery.service;


import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import onlinefooddeliveryapp.onlinefooddelivery.dao.repository.RestaurantRepository;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllRestaurantRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.RegisterRestaurantRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateMenuRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.AddMenuItemResponse;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuItemCannotBeFoundException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.RestaurantCannotBeFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemService menuItemService;

    @Override
    public Restaurants addNewResstaurant(RegisterRestaurantRequest registerRestaurantRequest) {
        Restaurants restaurants = Restaurants.builder()
                .restaurantName(registerRestaurantRequest.getRestaurantName())
                .location(registerRestaurantRequest.getLocation())
                .contactAddress(registerRestaurantRequest.getContactAddress())
                .build();
//       MenuItems menuItems =  menuItemService.addMenu(registerRestaurantRequest.getAddMenuItemRequest());

//        restaurants.setMenuItemsList(menuItems);
        return restaurantRepository.save(restaurants);

    }


    @Override
    public Restaurants browseRestaurantById(String restaurantId) {
        Optional<Restaurants> foundRestaurant = restaurantRepository.findById(restaurantId);
        if (foundRestaurant.isPresent()) {
            return foundRestaurant.get();
        } else {
            throw new RestaurantCannotBeFound("Restuarant with " + restaurantId + " cannot be found");

        }
    }

    @Override
    public Restaurants browseRestaurantByName(String restaurantName) {
        Optional<Restaurants> foundRestaurant = restaurantRepository.findRestaurantsByRestaurantName(restaurantName);
        if (foundRestaurant.isPresent()) {
            return foundRestaurant.get();
        } else {
            throw new RestaurantCannotBeFound("Restuarant with " + restaurantName + " cannot be found");

        }
    }

    @Override
    public Page<Restaurants> browseAllRestaurant(FindAllRestaurantRequest findAllRestaurantRequest) {
        Pageable pageable = PageRequest.of(findAllRestaurantRequest.getPages() - 1, findAllRestaurantRequest.getNumberOfPages());
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public AddMenuItemResponse saveMenuItem(AddMenuItemRequest addMenuItem) throws MenuAlreadyExistException {
        MenuItems savedMenuItem = menuItemService.addMenu(addMenuItem);
        Optional<Restaurants> foundRestaurant = restaurantRepository.findRestaurantsByRestaurantId(addMenuItem.getRestaurantId());
        if (foundRestaurant.isPresent()) {
                foundRestaurant.get().getMenuItemsList().add(savedMenuItem);
                restaurantRepository.save(foundRestaurant.get());


                return AddMenuItemResponse
                        .builder()
                        .menuId(savedMenuItem.getItemId())
                        .menuDescription(savedMenuItem.getItemDescription())
                        .menuName(savedMenuItem.getMenuName())
                        .price(savedMenuItem.getPrice())
                        .message("Menu Added Successfully")
                        .restaurantId(foundRestaurant.get().getRestaurantId())
                        .build();
            }

        throw new RestaurantCannotBeFound("Restuarant cannot be found");
    }

    @Override
    public void deleteAll() {
        restaurantRepository.deleteAll();
    }

    @Override
    public MenuItems findMenuById(String restaurantId, String menuId) throws MenuItemCannotBeFoundException {
      Optional<Restaurants> foundRestuarant =  restaurantRepository.findRestaurantsByRestaurantId(restaurantId);
      if(foundRestuarant.isPresent()){
         return menuItemService.viewMenuItemByMenuId(menuId);
      }
      else {
          throw new RestaurantCannotBeFound("Restaurant cannot be found");
      }

    }

    @Override
    public String deleteAllMenu(String restaurantId) {
        menuItemService.deleteAll();
        return "All menu successfully deleted";
    }

    @Override
    public MenuItems findMenuByName(String restaurantId, String menuName) throws MenuItemCannotBeFoundException {
        Optional<Restaurants> foundRestuarant =  restaurantRepository.findRestaurantsByRestaurantId(restaurantId);
        if(foundRestuarant.isPresent()){
            return menuItemService.viewMenuItemByMenuName(menuName);
        }
        else {
            throw new RestaurantCannotBeFound("Restaurant cannot be found");
        }


    }

    @Override
    public String deleteMenuById(String restaurantId, String menuId) throws MenuItemCannotBeFoundException {
        Optional<Restaurants> foundRestuarant =  restaurantRepository.findRestaurantsByRestaurantId(restaurantId);
        if(foundRestuarant.isPresent()){
            return menuItemService.deleteMenuItemByMenuId(menuId);
        }
        else {
            throw new RestaurantCannotBeFound("Restaurant cannot be found");
        }
    }

    @Override
    public String updateMenuItem(UpdateMenuRequest updateMenuRequest, String restaurantId) throws MenuItemCannotBeFoundException {
        Optional<Restaurants> foundRestuarant =  restaurantRepository.findRestaurantsByRestaurantId(restaurantId);
        if(foundRestuarant.isPresent()){
         menuItemService.updateMenuItem(updateMenuRequest, updateMenuRequest.getMenuName());
         restaurantRepository.save(foundRestuarant.get());
         return "Menu list successfully updated";
        }
        throw new RestaurantCannotBeFound("Restaurant cannott be found");

    }

}


