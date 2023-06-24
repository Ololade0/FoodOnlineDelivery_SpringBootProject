package onlinefooddeliveryapp.onlinefooddelivery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLoginRequestModel {
    private String email;
    private String password;
}
