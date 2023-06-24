package onlinefooddeliveryapp.onlinefooddelivery.service;

import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dao.repository.MenuItemRepository;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateMenuRequest;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuItemCannotBeFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Override
    public MenuItems addMenu(AddMenuItemRequest addMenuItemRequest) {
        MenuItems items = MenuItems.builder()
                .itemDescription(addMenuItemRequest.getItemDescription())
                .menuName(addMenuItemRequest.getName())
                .price(addMenuItemRequest.getPrice())
                .build();
        return menuItemRepository.save(items);
    }


    @Override
    public MenuItems viewMenuItemByMenuName(String menuName) throws MenuItemCannotBeFoundException {
        Optional<MenuItems> foundMenu = menuItemRepository.findMenuItemsByMenuName(menuName);
        if (foundMenu.isPresent()) {
            return foundMenu.get();
        } else {
            throw new MenuItemCannotBeFoundException("Menu cannot be found");
        }
    }

    @Override
    public MenuItems viewMenuItemByMenuId(String itemId) throws MenuItemCannotBeFoundException {
        Optional<MenuItems> foundMenu = menuItemRepository.findById(itemId);
        if (foundMenu.isPresent()) {
            return foundMenu.get();
        } else {
            throw new MenuItemCannotBeFoundException("Menu cannot be found");
        }

    }

    @Override
    public String deleteMenuItemByMenuId(String itemId) throws MenuItemCannotBeFoundException {
        Optional<MenuItems> foundMenu = menuItemRepository.findById(itemId);
        if (foundMenu.isPresent()) {
            return "Menu successfully deleted";
        } else {
            return "Error in deleting menu";
        }

    }

    @Override
    public String updateMenuItem(UpdateMenuRequest updateMenuRequest, String menuName) throws MenuItemCannotBeFoundException {
        Optional<MenuItems> updatedMenuItem = menuItemRepository.findMenuItemsByMenuName(menuName);
        if (updatedMenuItem.isPresent()) {
            updatedMenuItem.get().setItemDescription(updateMenuRequest.getItemDescription());
            updatedMenuItem.get().setMenuName(updateMenuRequest.getMenuName());
            updatedMenuItem.get().setPrice(updateMenuRequest.getPrice());
            menuItemRepository.save(updatedMenuItem.get());
            return "MenuItem successfully updated";
        } else {
            throw new MenuItemCannotBeFoundException("Menu with " + menuName + " cannot be found");
        }
    }

}



