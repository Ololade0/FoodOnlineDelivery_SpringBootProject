package onlinefooddeliveryapp.onlinefooddelivery.dao.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        @DBRef
        private Set<Role> roleHashSet = new HashSet<>();


        public Users(String firstName, String lastName, String email, String phoneNo, String password,  RoleType roleType) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phoneNo = phoneNo;
                this.password = password;
                this.addressList = new ArrayList<>();
                if (roleHashSet == null) {
                        roleHashSet = new HashSet<>();
                        roleHashSet.add(new Role(roleType));

                }
        }
}
