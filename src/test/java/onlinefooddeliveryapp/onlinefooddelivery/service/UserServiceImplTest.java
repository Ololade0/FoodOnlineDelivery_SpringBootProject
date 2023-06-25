package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.*;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.*;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.AddMenuItemResponse;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.UserLoginResponse;
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
class UserServiceImplTest {
    Users registeredUser;
    AddMenuItemResponse addMenuItemResponse;
    Restaurants savedRestaurant;

    @Autowired
    private RestaurantService restaurantService;


    @Autowired
    private UserService userServices;

    @BeforeEach
    void setUp() {

        SignUpUserRequest signUpUserRequest = SignUpUserRequest.builder()

                .email("adesuyiololad@gmail.com")
                .firstName("Ololade")
                .lastName("Demilade")
                .phoneNo("08109093828")
                .password("12345")
                .build();
        registeredUser =   userServices.signUpUser(signUpUserRequest);



        Restaurants restaurants = Restaurants.builder()
                .contactAddress("N0 12, Emily Akinola Street")
                .location("Lekki")
                .restaurantName("Dami's Restaurant")
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
        userServices.deleteAllUsers();
        restaurantService.deleteAll();;

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
       Users registeredUser =   userServices.signUpUser(signUpUserRequest);
        assertThat(registeredUser.getId()).isNotNull();
        System.out.println(registeredUser);

    }

    @Test
    public void findUserByEmail() {
        Users foundUser  = userServices.findUserByEmail(registeredUser.getEmail());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(registeredUser.getEmail());
    }



    @Test
    public void findUserByuserId() {
        Users foundUser  = userServices.findUserByuserId(registeredUser.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(registeredUser.getEmail());

    }

    @Test
    public void findUserByName() {
        Users foundUser  = userServices.findUserByName(registeredUser.getFirstName());
        assertEquals("Ololade", foundUser.getFirstName());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getFirstName()).isEqualTo(registeredUser.getFirstName());

    }


    @Test
    void findAllUser(){
        FindAllUserRequest findAllUser = FindAllUserRequest.builder()
                .numberOfPages(1)
                .pages(2)
                .build();
        Page<Users> foundUser =   userServices.findAllUser(findAllUser);
        assertEquals(1, foundUser.getTotalElements());
        Assertions.assertThat(foundUser.getTotalElements()).isNotNull();
    }

    @Test
    public void findDeleteUserById() {
        String deleteUser  = userServices.deleteUserById(registeredUser.getId());
        assertThat(deleteUser).isNotNull();
        assertEquals("User can be deleted", deleteUser);
    }

    @Test
    public void findDeleteAllUser() {
        String deleteUser  = userServices.deleteAllUsers();
        assertThat(deleteUser).isNotNull();
        assertEquals("All Users successfully deleted", deleteUser);
    }


    @Test
    public void UserCanLogin() {
        UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel();
        userLoginRequestModel.setEmail(registeredUser.getEmail());
        userLoginRequestModel.setPassword(registeredUser.getPassword());
        UserLoginResponse user =  userServices.login(userLoginRequestModel);
        assertEquals("Login successful", user.getMessage());
        assertEquals(200, user.getCode());

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
    void userCanBrowseRestuarant() {
        Restaurants browseRestaurant =   userServices.userCanBrowseRestaurantById(registeredUser.getId(), savedRestaurant.getRestaurantId());
        assertThat(browseRestaurant.getRestaurantId()).isNotNull();
        assertThat(browseRestaurant.getRestaurantId()).isEqualTo(savedRestaurant.getRestaurantId());


    }

    @Test
    void userCanBrowseRestuarantByName() {
        Restaurants browseRestaurant = userServices.userCanBrowseRestaurantByRestaurantName(registeredUser.getId(), savedRestaurant.getRestaurantName());
        assertEquals("Dami's Restaurant", browseRestaurant.getRestaurantName());
        assertThat(browseRestaurant.getRestaurantName()).isNotNull();

    }

    @Test
    void userCanViewRestuarantMenuItem() {
        List<MenuItems> menuItemsList =  userServices.userCanViewRestaurantMenu(savedRestaurant.getRestaurantId(), registeredUser.getId());
        assertEquals("Rice and Beans", menuItemsList.get(0).getMenuName());
        assertThat(menuItemsList).isNotNull();


    }

    @Test
    void userCanPlaceOrderInARestaurant() throws OrderCannotBeFoundException, OrderAlreadyExistException {
        PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                .quantity(10)
                .restaurantId(savedRestaurant.getRestaurantId())
                .id(registeredUser.getId())
                .ordered_at(LocalDateTime.now())
                .orderStatus(OrderStatus.PLACED_ORDER)
                .itemPrice(BigDecimal.valueOf(100))
                .totalPrice(calculateTotalPrice(new PlaceOrderRequest()))
                .deliveryTime(LocalDateTime.now())
                .address(Address.builder()
                        .area("Yaba Area")
                        .city("Lagos")
                        .country("Nigeria")
                        .houseNumber("No 31")
                        .state("Lagos")
                        .build())
                .build();
        Users userThatPlacedOrders =  userServices.userCanPlaceOrderInARestaurant(placeOrderRequest);
        assertThat(userThatPlacedOrders.getOrdersList()).isNotNull();
        assertEquals(BigDecimal.valueOf(1000), userThatPlacedOrders.getOrdersList().get(0).getTotalPrice());
        assertEquals(BigDecimal.valueOf(100), userThatPlacedOrders.getOrdersList().get(0).getItemPrice());



    }

    public BigDecimal calculateTotalPrice(PlaceOrderRequest placeOrderRequest) {
        BigDecimal total = BigDecimal.ZERO;
        if (placeOrderRequest.getTotalPrice() != null && placeOrderRequest.getQuantity() != null) {
            total = placeOrderRequest.getTotalPrice().multiply(BigDecimal.valueOf(placeOrderRequest.getQuantity()));
        }
        return total;
    }



}
