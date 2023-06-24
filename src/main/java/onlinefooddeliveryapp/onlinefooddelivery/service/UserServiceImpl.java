package onlinefooddeliveryapp.onlinefooddelivery.service;


import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.*;
import onlinefooddeliveryapp.onlinefooddelivery.dao.repository.UserRepository;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.PlaceOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.SignUpUserRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UserLoginRequestModel;
import onlinefooddeliveryapp.onlinefooddelivery.dto.response.UserLoginResponse;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.RestaurantCannotBeFound;
import onlinefooddeliveryapp.onlinefooddelivery.exception.UserCannotBeFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RestaurantService restaurantService;

    private final OrderService orderService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
                    .password(bCryptPasswordEncoder.encode(signUpUserRequest.getPassword()))
                    .phoneNo(signUpUserRequest.getPhoneNo())
                    .roleHashSet(new HashSet<>())
                    .build();
        Role userRole = new Role(RoleType.ROLE_USER);
        userRole = roleService.save(userRole);
        signUser.getRoleHashSet().add(userRole);
        return userRepository.save(signUser);
        }

    @Override
    public Users findUserByEmail(String email) {
      Optional<Users> foundUser =  userRepository.findUsersByEmail(email);
      if(foundUser.isPresent()){
          return foundUser.get();
      }
      throw new UserCannotBeFoundException("User cannot be found");
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

    }


    @Override
    public UserLoginResponse login(UserLoginRequestModel userLoginRequestModel) {
        var user = userRepository.findUsersByEmail(userLoginRequestModel.getEmail());
        if(user.isPresent() && user.get().getPassword().equals(userLoginRequestModel.getPassword()));
        return   buildSuccessfulLoginResponse(user.get());

    }


    private UserLoginResponse buildSuccessfulLoginResponse(Users user) {
        return UserLoginResponse.builder()
                .code(200)
                .message("Login successful")
                .build();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            Users user = userRepository.findUsersByEmail(username).orElse(null);
            if(user!= null){
                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user.getRoleHashSet()));
            }
            return null;
        }

        private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roleHashSet) {
            return roleHashSet.stream().map(role -> new SimpleGrantedAuthority(role.getRoleType().name())).collect(Collectors.toSet());
        }
    }







