package onlinefooddeliveryapp.onlinefooddelivery.controller;

import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllRestaurantRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.RegisterRestaurantRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateMenuRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.AddMenuItemResponse;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuItemCannotBeFoundException;
import onlinefooddeliveryapp.onlinefooddelivery.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class RestaurantController {
    private final RestaurantService restaurantService;


    @PostMapping("/restaurant")
    public ResponseEntity<?> addNewRestaurant(@RequestBody RegisterRestaurantRequest registerRestaurantRequest)  {
        Restaurants savedRestaurant = restaurantService.addNewResstaurant(registerRestaurantRequest);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.OK);
    }

    @GetMapping("restaurantName/{restaurantName}")
    public ResponseEntity<?> findRestaurantByName(@PathVariable String restaurantName )  {
        Restaurants foundRestaurant = restaurantService.browseRestaurantByName(restaurantName);
        return new ResponseEntity<>(foundRestaurant, HttpStatus.OK);
    }

    @GetMapping("restaurantId/{restaurantId}")
    public ResponseEntity<?> findRestaurantById(@PathVariable String restaurantId)  {
        Restaurants browsedRestaurant = restaurantService.browseRestaurantById(restaurantId);
        return new ResponseEntity<>(browsedRestaurant, HttpStatus.OK);
    }

    @GetMapping("/findAllrestaurant")
    public ResponseEntity<?> findAllRestuarant(@RequestBody FindAllRestaurantRequest findAllRestaurantRequestfind)  {
        Page<Restaurants> browsedRestaurant = restaurantService.browseAllRestaurant(findAllRestaurantRequestfind);
        return new ResponseEntity<>(browsedRestaurant, HttpStatus.OK);
    }

    @PostMapping("restaurant/savedMenu")
    public ResponseEntity<?> saveMenuItem(@RequestBody AddMenuItemRequest addMenuItemRequest) throws MenuAlreadyExistException {
        AddMenuItemResponse savedMenu = restaurantService.saveMenuItem(addMenuItemRequest);
        return new ResponseEntity<>(savedMenu, HttpStatus.OK);
    }

    @GetMapping("restaurant/menuId{restaurantId}/{menuId}")
    public ResponseEntity<?> findMenuById(@PathVariable String restaurantId, @PathVariable String menuId) throws MenuItemCannotBeFoundException {
        MenuItems foundMenu = restaurantService.findMenuById(restaurantId,menuId);
        return new ResponseEntity<>(foundMenu, HttpStatus.OK);
    }

    @GetMapping("restaurant/menuName/{restaurantId}/{menuName}")
    public ResponseEntity<?> findMenuByName(@PathVariable String restaurantId, String menuName ) throws MenuAlreadyExistException, MenuItemCannotBeFoundException {
        MenuItems foundMenu = restaurantService.findMenuByName(restaurantId, menuName);
        return new ResponseEntity<>(foundMenu, HttpStatus.OK);
    }


    @DeleteMapping("deleteAllMenu/{restaurantId}")
    public ResponseEntity<?> deleteAllMenu(@PathVariable String restaurantId)  {
        String deletedMenu = restaurantService.deleteAllMenu(restaurantId);
        return new ResponseEntity<>(deletedMenu, HttpStatus.OK);
    }

    @DeleteMapping("deleteMenu/{restaurantId}/{menuId}")
    public ResponseEntity<?> deletedMenuById(@PathVariable String restaurantId, @PathVariable String menuId) throws MenuItemCannotBeFoundException{
        String deletedMenu = restaurantService.deleteMenuById(restaurantId, menuId);
        return new ResponseEntity<>(deletedMenu, HttpStatus.OK);
    }

    @PostMapping("updateMenu/{restaurantId}")
    public ResponseEntity<?> updateMenuItem(@RequestBody UpdateMenuRequest updateMenuRequest, @PathVariable String restaurantId) throws MenuItemCannotBeFoundException {
        String updatedMenu = restaurantService.updateMenuItem(updateMenuRequest, restaurantId);
        return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
    }



}
