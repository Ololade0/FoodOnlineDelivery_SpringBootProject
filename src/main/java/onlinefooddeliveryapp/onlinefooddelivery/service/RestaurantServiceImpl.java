package onlinefooddeliveryapp.onlinefooddelivery.service;

import com.example.onlinefooddelivery.dao.model.MenuItems;
import com.example.onlinefooddelivery.dao.model.Restaurants;
import com.example.onlinefooddelivery.dao.repository.RestaurantRepository;
import com.example.onlinefooddelivery.dto.request.AddMenuItemRequest;
import com.example.onlinefooddelivery.dto.request.FindAllRestaurantRequest;
import com.example.onlinefooddelivery.dto.response.AddMenuItemResponse;
import com.example.onlinefooddelivery.exception.RestaurantCannotBeFound;
import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import onlinefooddeliveryapp.onlinefooddelivery.dao.repository.RestaurantRepository;
import onlinefooddeliveryapp.onlinefooddelivery.exception.RestaurantCannotBeFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemService menuItemService;

    @Override
    public Restaurants addNewResstaurant(Restaurants newRestaurant) {
        List<MenuItems> menuItems = new ArrayList<>();
        Restaurants restaurants = Restaurants.builder()
                .restaurantName(newRestaurant.getRestaurantName())
                .location(newRestaurant.getLocation())
                .contactAddress(newRestaurant.getContactAddress())
                .menuItemsList(newRestaurant.getMenuItemsList())
                .build();
        return restaurantRepository.save(restaurants);
    }

//    @Service
//    public class RestaurantService {
//        @Autowired
//        private RestaurantRepository restaurantRepository;
//        @Autowired
//        private MenuItemService menuItemService;
//
//        public Restaurant addRestaurant(AddRestaurantRequest newRestaurant) {
//            Restaurant restaurant = Restaurant.builder()
//                    .restaurantName(newRestaurant.getRestaurantName())
//                    .location(newRestaurant.getLocation())
//                    .contactAddress(newRestaurant.getContactAddress())
//                    .build();
//            restaurant = restaurantRepository.save(restaurant);
//            List<MenuItems> menuItems = menuItemService.addMenu(newRestaurant.getAddMenuItemRequest(), restaurant.getId());
//            restaurant.setMenuItemsList(menuItems);
//            return restaurantRepository.save(restaurant);
//        }
//    }

    @Override
    public Restaurants browseRestaurantById(String restaurantId) {
        Optional<Restaurants> foundRestaurant = restaurantRepository.findById(restaurantId);
        if (foundRestaurant.isPresent()) {
            return foundRestaurant.get();
        } else {
            throw new RestaurantCannotBeFound("Restuarant with " + restaurantId + "cannot ne found");

        }
    }

    @Override
    public Restaurants browseRestaurantByName(String restaurantName) {
        Optional<Restaurants> foundRestaurant = restaurantRepository.findRestaurantsByRestaurantName(restaurantName);
        if (foundRestaurant.isPresent()) {
            return foundRestaurant.get();
        } else {
            throw new RestaurantCannotBeFound("Restuarant with " + restaurantName + "cannot ne found");

        }
    }

    @Override
    public Page<Restaurants> browseAllRestaurant(FindAllRestaurantRequest findAllRestaurantRequest) {
        Pageable pageable = PageRequest.of(findAllRestaurantRequest.getPages() - 1, findAllRestaurantRequest.getNumberOfPages());
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public AddMenuItemResponse saveMenuItem(AddMenuItemRequest addMenuItem) {
        MenuItems savedMenuItem = menuItemService.addMenu(addMenuItem);
        Optional<Restaurants> foundRestaurant = restaurantRepository.findRestaurantsByRestaurantId(addMenuItem.getRestaurantId());
        if (foundRestaurant.isPresent()) {
                foundRestaurant.get().getMenuItemsList().add(savedMenuItem);
                restaurantRepository.save(foundRestaurant.get());

                return AddMenuItemResponse
                        .builder()
                        .menuId(savedMenuItem.getItemId())
                        .menuDescription(savedMenuItem.getItemDescription())
                        .name(savedMenuItem.getMenuName())
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

}


