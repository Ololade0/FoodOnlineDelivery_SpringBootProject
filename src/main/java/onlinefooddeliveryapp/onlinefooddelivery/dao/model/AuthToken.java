package onlinefooddeliveryapp.onlinefooddelivery.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {
    private String token;
    private String id;


}
