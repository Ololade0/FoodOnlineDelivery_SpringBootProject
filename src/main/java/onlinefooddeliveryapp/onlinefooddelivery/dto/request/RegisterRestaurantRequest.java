package onlinefooddeliveryapp.onlinefooddelivery.dto.request;

import lombok.*;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterRestaurantRequest {

    private String restaurantId;
    private String contactAddress;
    private String restaurantName;
    private String location;
    private AddMenuItemRequest addMenuItemRequest;


}
