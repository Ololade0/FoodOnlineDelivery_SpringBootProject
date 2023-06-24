package onlinefooddeliveryapp.onlinefooddelivery.dto.request;


import lombok.*;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Address;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpUserRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;
    private List<Address> address;
}
