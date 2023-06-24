package onlinefooddeliveryapp.onlinefooddelivery.service;

import com.example.onlinefooddelivery.dao.model.MenuItems;
import com.example.onlinefooddelivery.dao.model.Order;
import com.example.onlinefooddelivery.dao.model.Restaurants;
import com.example.onlinefooddelivery.dao.model.Users;
import com.example.onlinefooddelivery.dao.repository.UserRepository;
import com.example.onlinefooddelivery.dto.request.PlaceOrderRequest;
import com.example.onlinefooddelivery.dto.request.SignUpUserRequest;
import com.example.onlinefooddelivery.exception.OrderAlreadyExistException;
import com.example.onlinefooddelivery.exception.OrderCannotBeFoundException;
import com.example.onlinefooddelivery.exception.RestaurantCannotBeFound;
import com.example.onlinefooddelivery.exception.UserCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestaurantService restaurantService;
    private final AddressService addressService;
    private final OrderService orderService;

    @Override
    public Users signUpUser(SignUpUserRequest signUpUserRequest) {
//        Optional<Users> foundUser = userRepository.findUsersByEmail(signUpUserRequest.getEmail());
//        if (foundUser.isPresent()) {
//            throw new UserCannotBeFoundException("User with email : " + foundUser.get().getEmail() + "already exist");
//        } else {
            Users signUser = Users.builder()
                    .email(signUpUserRequest.getEmail())
                    .firstName(signUpUserRequest.getFirstName())
                    .lastName(signUpUserRequest.getLastName())
                    .phoneNo(signUpUserRequest.getPhoneNo())
                    .build();
        return userRepository.save(signUser);
        }

    @Override
    public Restaurants userCanBrowseRestaurantById(String id, String restaurantId) {
        Restaurants browsedRestaurant = restaurantService.browseRestaurantById(restaurantId);
        Optional<Users> foundUser = userRepository.findById(id);
        if (foundUser.isPresent() && browsedRestaurant != null) {

            return browsedRestaurant;
            }


            else {
                throw new RestaurantCannotBeFound("Restaurant cannot be found");
            }

        }

    @Override
    public Restaurants userCanBrowseRestaurantByRestaurantName(String id, String restaurantName) {
        Restaurants browsedRestaurant = restaurantService.browseRestaurantByName(restaurantName);
        Optional<Users> foundUser = userRepository.findById(id);
        if (foundUser.isPresent() && browsedRestaurant != null) {

            return browsedRestaurant;
        }


        else {
            throw new RestaurantCannotBeFound("Restaurant cannot be found");
        }
    }

    @Override
    public List<MenuItems> userCanViewRestaurantMenu(String restaurantId, String id) {
      Optional<Users> foundUsers =   userRepository.findById(id);
       Restaurants browsedRestaurant = restaurantService.browseRestaurantById(restaurantId);
       if(foundUsers.isPresent()) {
           if(browsedRestaurant!=null){
               return browsedRestaurant.getMenuItemsList();
           }
           throw new RestaurantCannotBeFound("Restaurant Cannot be found");
       }
       throw new UserCannotBeFoundException("User cannot be found");

    }

    @Override
    public Users userCanPlaceOrderInARestaurant(PlaceOrderRequest placeOrderRequest) throws OrderCannotBeFoundException, OrderAlreadyExistException {
    Order placedOrder =    orderService.placedOrders(placeOrderRequest);
        Optional<Users> foundUsers =   userRepository.findById(placeOrderRequest.getId());
        Restaurants browsedRestaurant = restaurantService.browseRestaurantById(placeOrderRequest.getRestaurantId());
        if(foundUsers.isPresent()) {
            if(browsedRestaurant!=null){
             foundUsers.get().getOrdersList().add(placedOrder);
               return userRepository.save(foundUsers.get());
            }
            throw new RestaurantCannotBeFound("Restaurant Cannot be found");
    }
        throw new UserCannotBeFoundException("User cannot be found");

//    @Override
//    public String userCanPlaceOrderInARestaurant(String restaurantId, String id) {
//        Optional<Users> foundUsers =   userRepository.findById(id);
//        Restaurants browsedRestaurant = restaurantService.browseRestaurantById(restaurantId);
//        if(foundUsers.isPresent()) {
//            if(browsedRestaurant!=null){
//                orderService.placedOrders()
//
//            }
//
//            }
    }


}







