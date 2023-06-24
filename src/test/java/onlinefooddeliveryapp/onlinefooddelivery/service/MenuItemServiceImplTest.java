package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateMenuRequest;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuItemCannotBeFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class MenuItemServiceImplTest {

    MenuItems savedMenuItems;
    @Autowired
    private MenuItemService menuItemService;

    @BeforeEach
    void setUp() {
        AddMenuItemRequest addMenuItemRequest = AddMenuItemRequest.builder()
                .itemDescription("Appetizer:\n" +
                        "Caprese Salad - Fresh mozzarella, juicy vine-ripened tomatoes," +
                        " and fragrant basil drizzled with " +
                        "balsamic glaze.")
                .name("Rice and Beans")
                .price(BigDecimal.valueOf(4000))

                .build();
        savedMenuItems =  menuItemService.addMenu(addMenuItemRequest);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addMenu() {
        AddMenuItemRequest addMenuItemRequest = AddMenuItemRequest.builder()
                        .itemDescription("Appetizer:\n" +
                                "Caprese Salad - Fresh mozzarella, juicy vine-ripened tomatoes," +
                                " and fragrant basil drizzled with " +
                                "balsamic glaze.")
                        .name("Rice and Beans")
                        .price(BigDecimal.valueOf(4000))

                .build();
      MenuItems savedMenuItems =  menuItemService.addMenu(addMenuItemRequest);
        assertEquals("Rice and Beans", savedMenuItems.getMenuName());

    }



    @Test
    public void browseMenuItemByMenuName() throws MenuItemCannotBeFoundException {
        MenuItems foundMenuItem = menuItemService.viewMenuItemByMenuName(savedMenuItems.getMenuName());
        assertThat(foundMenuItem).isNotNull();
        assertThat(foundMenuItem.getMenuName()).isEqualTo(foundMenuItem.getMenuName());
        assertEquals("Rice and Beans", foundMenuItem.getMenuName());

    }


    @Test
    public void browseMenuItemById() throws MenuItemCannotBeFoundException {
        MenuItems foundMenuItem = menuItemService.viewMenuItemByMenuId(savedMenuItems.getItemId());
        assertThat(foundMenuItem).isNotNull();
        assertThat(foundMenuItem.getMenuName()).isEqualTo(foundMenuItem.getMenuName());
        assertEquals("Rice and Beans", foundMenuItem.getMenuName());

    }

    @Test
    public void deleteMenuItemById() throws MenuItemCannotBeFoundException {
        String foundMenuItem = menuItemService.deleteMenuItemByMenuId(savedMenuItems.getItemId());
        assertThat(foundMenuItem).isNotNull();
        assertEquals("Menu successfully deleted", foundMenuItem);
    }

    @Test
    public void updateMenuItem() throws MenuItemCannotBeFoundException {
        UpdateMenuRequest updateMenuRequest = UpdateMenuRequest.builder()
                .updatedAt(LocalDateTime.now())
                .menuName("Pasta")
                .itemDescription("\"Enjoy the rich flavors of our pasta dish," +
                        "This dish is perfect for those looking for a satisfying and healthy meal.\"")
                .price(BigDecimal.valueOf(40000))
                .build();
        String foundMenuItem = menuItemService.updateMenuItem(updateMenuRequest, savedMenuItems.getMenuName());
        assertThat(foundMenuItem).isNotNull();
        assertEquals("MenuItem successfully updated", foundMenuItem);
    }

}
