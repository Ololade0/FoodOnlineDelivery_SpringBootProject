package onlinefooddeliveryapp.onlinefooddelivery.dto.request;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AddMenuItemRequest {

    private String itemDescription;

    private BigDecimal price;
    private String name;
    private String restaurantId;

}
