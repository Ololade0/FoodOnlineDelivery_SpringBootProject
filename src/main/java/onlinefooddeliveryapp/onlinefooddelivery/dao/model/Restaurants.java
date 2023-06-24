package onlinefooddeliveryapp.onlinefooddelivery.dao.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("Restaurant")
public class Restaurants {
        @Id
        private String restaurantId;
        private String contactAddress;
        private String restaurantName;
        private String location;
        @DBRef
        private List<MenuItems> menuItemsList = new ArrayList<>();

        @DBRef
        private List<Order> orderList = new ArrayList<>();
    }

