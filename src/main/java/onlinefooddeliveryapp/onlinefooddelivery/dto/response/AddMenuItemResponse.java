package onlinefooddeliveryapp.onlinefooddelivery.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddMenuItemResponse {
    private String message;
    private String menuId;
    private String menuDescription;
    private BigDecimal price;
    private String menuName;
    private String restaurantId;
}
