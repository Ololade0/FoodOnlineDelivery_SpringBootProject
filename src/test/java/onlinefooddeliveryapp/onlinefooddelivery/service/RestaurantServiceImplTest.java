package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.*;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllRestaurantRequest;

import onlinefooddeliveryapp.onlinefooddelivery.dto.response.AddMenuItemResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class RestaurantServiceImplTest {


    @Autowired
    private RestaurantService restaurantService;




    Restaurants savedRestaurant;




    AddMenuItemResponse addMenuItemResponse;

    @BeforeEach
    void setUp() {
        Restaurants restaurants = Restaurants.builder()
                .contactAddress("N0 12, Emily Akinola Street")
                .location("Lekki")
                .restaurantName("Huldaa Restaurant")
                .build();
        savedRestaurant = restaurantService.addNewResstaurant(restaurants);


        AddMenuItemRequest addMenuItem = AddMenuItemRequest.builder()

                .restaurantId(savedRestaurant.getRestaurantId())
                .itemDescription("Appetizer:\n" +
                        "Caprese Salad - Fresh mozzarella, juicy vine-ripened tomatoes," +
                        " and fragrant basil drizzled with " +
                        "balsamic glaze.")
                .name("Rice and Beans")
                .price(BigDecimal.valueOf(4000))

                .build();
        addMenuItemResponse = restaurantService.saveMenuItem(addMenuItem);

}
    @AfterEach
    void tearDown() {
        restaurantService.deleteAll();
    }



    @Test
    void addNewRestaurant(){
        Restaurants restaurants = Restaurants.builder()
                .contactAddress("N0 12, Emily Akinola Street")
                .location("Lekki")
                .restaurantName("Hulda Restaurant")

                .build();
        Restaurants savedRestaurant = restaurantService.addNewResstaurant(restaurants);
        assertEquals("Hulda Restaurant", savedRestaurant.getRestaurantName());
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
    void restaurantCanSaveMenuItem(){
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
        assertEquals("Rice and Beans", addMenuItemResponse.getName());
        assertEquals(BigDecimal.valueOf(4000), addMenuItemResponse.getPrice());
    }
















}
