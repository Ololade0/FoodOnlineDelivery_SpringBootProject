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
@Document("Users")
public class Users {
        @Id
        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNo;
        private String password;
        @DBRef
        private List<Order> ordersList = new ArrayList<>();

        @DBRef
        private List<Address> addressList = new ArrayList<>();
}
