package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.*;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.AddMenuItemRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllRestaurantRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.PlaceOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.SignUpUserRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.AddMenuItemResponse;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class RestaurantServiceImplTest {


    @Autowired
    private RestaurantService restaurantService;



    @Autowired
    private UserService userService;

    Restaurants savedRestaurant;

    Users registeredUser;


    AddMenuItemResponse addMenuItemResponse;

    @BeforeEach
    void setUp() {
        Restaurants restaurants = Restaurants.builder()
                .contactAddress("N0 12, Emily Akinola Street")
                .location("Lekki")
                .restaurantName("Hulda Restaurant")
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


        SignUpUserRequest signUpUserRequest = SignUpUserRequest.builder()

                .email("adesuyiololad@gmail.com")
                .firstName("Ololade")
                .lastName("Demilade")
                .phoneNo("08109093828")
                .password("12345")
                .build();
        registeredUser = userService.signUpUser(signUpUserRequest);


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
    void retriveAllCustomers() {
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


    @Test
    void userCanBeRegister(){
        SignUpUserRequest signUpUserRequest = SignUpUserRequest.builder()

                .email("adesuyiololad@gmail.com")
                .firstName("Ololade")
                .lastName("Demilade")
                .phoneNo("08109093828")
                .password("12345")
                .build();
        Users registeredUser =   userService.signUpUser(signUpUserRequest);
        Assertions.assertThat(registeredUser.getId()).isNotNull();
        System.out.println(registeredUser);

    }

        @Test
    void userCanBrowseRestuarant() {
        Restaurants browseRestaurant =   userService.userCanBrowseRestaurantById(registeredUser.getId(), savedRestaurant.getRestaurantId());
       assertThat(browseRestaurant.getRestaurantId()).isNotNull();
        assertThat(browseRestaurant.getRestaurantId()).isEqualTo(savedRestaurant.getRestaurantId());


    }

    @Test
    void userCanBrowseRestuarantByName() {
        Restaurants browseRestaurant = userService.userCanBrowseRestaurantByRestaurantName(registeredUser.getId(), savedRestaurant.getRestaurantName());
        assertEquals("Hulda Restaurant", browseRestaurant.getRestaurantName());
        assertThat(browseRestaurant.getRestaurantName()).isNotNull();

    }

    @Test
    void userCanViewRestuarantMenuItem() {
    List<MenuItems> menuItemsList =  userService.userCanViewRestaurantMenu(savedRestaurant.getRestaurantId(), registeredUser.getId());
    assertEquals("Rice and Beans", menuItemsList.get(0).getMenuName());
        assertThat(menuItemsList).isNotNull();
        System.out.println(menuItemsList);

    }

    @Test
    void userCanPlaceOrderInARestaurant() throws OrderCannotBeFoundException, OrderAlreadyExistException {
        PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                .quantity(10)
                .restaurantId(savedRestaurant.getRestaurantId())
                .id(registeredUser.getId())
                .ordered_at(LocalDateTime.now())
                .orderStatus(OrderStatus.PLACED_ORDER)
                .totalPrice(BigDecimal.valueOf(2000))
                .deliveryTime(LocalDateTime.now())
                .address(Address.builder()
                        .area("Yaba Area")
                        .city("Lagos")
                        .country("Nigeria")
                        .houseNumber("No 31")
                        .state("Lagos")
                        .build())
                .build();
       Users userThatPlacedOrders =  userService.userCanPlaceOrderInARestaurant(placeOrderRequest);
       assertThat(userThatPlacedOrders.getOrdersList()).isNotNull();
        assertEquals(BigDecimal.valueOf(20000), userThatPlacedOrders.getOrdersList().get(0).getItemPrice());


    }












}
