
package onlinefooddeliveryapp.onlinefooddelivery.dto.request;

import lombok.*;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Address;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PlaceOrderRequest {
        private String id;
        private String orderId;
        private String restaurantId;

        private LocalDateTime deliveryTime;
        private BigDecimal itemPrice;
        private BigDecimal totalPrice;
        private Integer quantity;
        private LocalDateTime ordered_at;
        private OrderStatus orderStatus;
        private Address address;


}
