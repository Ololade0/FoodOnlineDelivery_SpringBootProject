package onlinefooddeliveryapp.onlinefooddelivery.dto.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ViewRestaurantMenu {

    private String itemDescription;
    private int quantity;
    private BigDecimal price;
    private String menuName;
    private String restaurantId;

}
