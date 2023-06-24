package onlinefooddeliveryapp.onlinefooddelivery.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantCannotBeFound extends RuntimeException{

    public RestaurantCannotBeFound(String message) {
        super(message);
    }


}

