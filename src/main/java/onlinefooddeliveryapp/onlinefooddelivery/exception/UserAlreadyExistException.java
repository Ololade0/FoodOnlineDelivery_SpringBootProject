package onlinefooddeliveryapp.onlinefooddelivery.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAlreadyExistException extends RuntimeException {
    private int statusCode;
    public UserAlreadyExistException(String message) {
        super(message);
    }



    public UserAlreadyExistException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;

    }



}
