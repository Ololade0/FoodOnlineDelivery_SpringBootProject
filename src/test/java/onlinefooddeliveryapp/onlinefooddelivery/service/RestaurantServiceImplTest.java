package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.*;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllRestaurantRequest;

import onlinefooddeliveryapp.onlinefooddelivery.dto.request.RegisterRestaurantRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateMenuRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.AddMenuItemResponse;

import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.MenuItemCannotBeFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class RestaurantServiceImplTest {


    @Autowired
    private RestaurantService restaurantService;




    Restaurants savedRestaurant;

    AddMenuItemResponse addMenuItemResponse;


    @BeforeEach
    void setUp() throws MenuAlreadyExistException {
       RegisterRestaurantRequest registerRestaurantRequest= RegisterRestaurantRequest.builder()
                .contactAddress("N0 12, Emily Akinola Street")
                .location("Lekki")
                .restaurantName("Huldaa Restaurant")
                .build();
        savedRestaurant = restaurantService.addNewResstaurant(registerRestaurantRequest);
        System.out.println(savedRestaurant);


        AddMenuItemRequest addMenuItem = AddMenuItemRequest.builder()
                .restaurantId(savedRestaurant.getRestaurantId())
                .itemDescription("Appetizer:\n" +
                        "Caprese Salad - Fresh mozzarella, juicy vine-ripened tomatoes," +
                        " and fragrant basil drizzled with " +
                        "balsamic glaze.")
                .name("Rice dodo")
                .price(BigDecimal.valueOf(4000))
                .build();
        addMenuItemResponse = restaurantService.saveMenuItem(addMenuItem);

}
    @AfterEach
    void tearDown() {
        restaurantService.deleteAll();
        restaurantService.deleteAllMenu(savedRestaurant.getRestaurantId());;
    }



    @Test
    void addNewRestaurant(){
        RegisterRestaurantRequest registerRestaurantRequest= RegisterRestaurantRequest.builder()
                .contactAddress("N0 12, Emily Akinola Street")
                .location("Lekki")
                .restaurantName("Huldaa Restaurant")
                .build();
        savedRestaurant = restaurantService.addNewResstaurant(registerRestaurantRequest);

        assertEquals("Huldaa Restaurant", savedRestaurant.getRestaurantName());
        assertEquals("N0 12, Emily Akinola Street", savedRestaurant.getContactAddress());
        System.out.println(savedRestaurant);
    }

    @Test
    public void browseRestaurantById(){
       Restaurants foundRestaurant = restaurantService.browseRestaurantById(savedRestaurant.getRestaurantId());
        assertThat(foundRestaurant).isNotNull();
        assertThat(foundRestaurant.getRestaurantId()).isEqualTo(savedRestaurant.getRestaurantId());

    }

    @Test
    public void browseRestaurantByName() {
        Restaurants foundRestaurant = restaurantService.browseRestaurantByName(savedRestaurant.getRestaurantName());
        assertThat(foundRestaurant).isNotNull();
        assertThat(foundRestaurant.getRestaurantName()).isEqualTo(savedRestaurant.getRestaurantName());

    }

    @Test
    void retriveAllResturant() {
        FindAllRestaurantRequest findAllRestaurantRequest = FindAllRestaurantRequest.builder()
                .numberOfPages(1)
                .pages(1)
                .build();
        Page<Restaurants> foundOrder =
                restaurantService.browseAllRestaurant(findAllRestaurantRequest);
        assertThat(foundOrder.getTotalElements()).isNotNull();
        assertEquals(1L, foundOrder.getTotalElements());
    }

    @Test
    void restaurantCanSaveMenuItem() throws MenuAlreadyExistException {
        AddMenuItemRequest addMenuItem = AddMenuItemRequest.builder()
                .restaurantId(savedRestaurant.getRestaurantId())
                .itemDescription("Appetizer:\n" +
                                "Caprese Salad - Fresh mozzarella, juicy vine-ripened tomatoes," +
                                " and fragrant basil drizzled with " +
                                "balsamic glaze.")
                .name("Rice and Beans")
                .price(BigDecimal.valueOf(4000))


                .build();
      AddMenuItemResponse addMenuItemResponse = restaurantService.saveMenuItem(addMenuItem);
      assertEquals("Menu Added Successfully", addMenuItemResponse.getMessage());
        assertEquals("Rice and Beans", addMenuItemResponse.getMenuName());
        assertEquals(BigDecimal.valueOf(4000), addMenuItemResponse.getPrice());
    }

    @Test
    void restaurantFindMenuByMenuId() throws MenuItemCannotBeFoundException {
        MenuItems foundMenuItems = restaurantService.findMenuById(savedRestaurant.getRestaurantId(), addMenuItemResponse.getMenuId());
        assertEquals("Rice dodo", foundMenuItems.getMenuName());
        assertThat(foundMenuItems.getItemId()).isNotNull();
        assertThat(foundMenuItems.getItemId()).isEqualTo(addMenuItemResponse.getMenuId());
    }

    @Test
    void restaurantFindMenuByMenuMenuName() throws MenuItemCannotBeFoundException {
        MenuItems foundMenuItems = restaurantService.findMenuByName(savedRestaurant.getRestaurantId(), addMenuItemResponse.getMenuName());
        assertEquals("Rice dodo", foundMenuItems.getMenuName());
        assertThat(foundMenuItems.getItemId()).isNotNull();
        assertThat(foundMenuItems.getItemId()).isEqualTo(addMenuItemResponse.getMenuId());
    }

    @Test
    void restaurantDeleteMenuByMenuId() throws MenuItemCannotBeFoundException {
        String foundMenuItems = restaurantService.deleteMenuById(savedRestaurant.getRestaurantId(), addMenuItemResponse.getMenuId());
        assertEquals("Menu successfully deleted", foundMenuItems);
    }

    @Test
    void restaurantdeleteAllMenu()  {
        String deletedMenu = restaurantService.deleteAllMenu(savedRestaurant.getRestaurantId());
        assertEquals("All menu successfully deleted", deletedMenu);

    }

    @Test
    public void updateMenuItem() throws MenuItemCannotBeFoundException {
        UpdateMenuRequest updateMenuRequest = UpdateMenuRequest.builder()
                .updatedAt(LocalDateTime.now())
                .menuName(addMenuItemResponse.getMenuName())
                .itemDescription("\"Enjoy the rich flavors of our pasta dish," +
                        "This dish is perfect for those looking for a satisfying and healthy meal.\"")
                .price(BigDecimal.valueOf(40000))
                .build();
        String foundMenuItem = restaurantService.updateMenuItem(updateMenuRequest, savedRestaurant.getRestaurantId());
        assertThat(foundMenuItem).isNotNull();
        assertEquals("Menu list successfully updated", foundMenuItem);
    }

}

















