package onlinefooddeliveryapp.onlinefooddelivery.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateMenuRequest;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuItemCannotBeFoundException;
import onlinefooddeliveryapp.onlinefooddelivery.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class MenuController {
    private final MenuItemService menuItemService;

    @PostMapping("/menu")
    public ResponseEntity<?> addMenu(@NonNull @RequestBody AddMenuItemRequest addMenuItemRequest) throws MenuAlreadyExistException {
        MenuItems menuItems = menuItemService.addMenu(addMenuItemRequest);
        return new ResponseEntity<>(menuItems, HttpStatus.CREATED);
    }

    @GetMapping("menuId/{menuId}")
    public ResponseEntity<?> viewMenuById(@PathVariable String menuId) throws MenuItemCannotBeFoundException {
        MenuItems menuItems = menuItemService.viewMenuItemByMenuId(menuId);
        return new ResponseEntity<>(menuItems, HttpStatus.CREATED);
    }


    @GetMapping("menuName/{menuName}")
    public ResponseEntity<?> viewMenuByMenuName(@PathVariable String menuName) throws MenuItemCannotBeFoundException {
        MenuItems menuItems = menuItemService.viewMenuItemByMenuName(menuName);
        return new ResponseEntity<>(menuItems, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{menuId}")
    public ResponseEntity<?> deletedMenuById(@PathVariable String menuId) throws MenuItemCannotBeFoundException {
        String deletedMenuItems = menuItemService.deleteMenuItemByMenuId(menuId);
        return new ResponseEntity<>(deletedMenuItems, HttpStatus.CREATED);
    }


    @PutMapping("update/{menuName}")
    public ResponseEntity<?> deletedMenuById(@RequestBody UpdateMenuRequest updateMenuRequest, @PathVariable String menuName) throws MenuItemCannotBeFoundException {
        String updateMenuItems = menuItemService.updateMenuItem(updateMenuRequest, menuName);
        return new ResponseEntity<>(updateMenuItems, HttpStatus.CREATED);
    }







}
