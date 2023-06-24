package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateMenuRequest;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuItemCannotBeFoundException;

public interface MenuItemService {
    MenuItems addMenu(AddMenuItemRequest addMenuItemRequest);

    MenuItems viewMenuItemByMenuName(String menuName) throws MenuItemCannotBeFoundException;

    MenuItems viewMenuItemByMenuId(String itemId) throws MenuItemCannotBeFoundException;

    String deleteMenuItemByMenuId(String itemId) throws MenuItemCannotBeFoundException;


    String updateMenuItem(UpdateMenuRequest updateMenuRequest, String menuName) throws MenuItemCannotBeFoundException;
}
