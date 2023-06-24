
package onlinefooddeliveryapp.onlinefooddelivery.dto.request;


import com.example.onlinefooddelivery.dao.model.Address;
import com.example.onlinefooddelivery.dao.model.OrderStatus;
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
        private String restaurantId;

        private LocalDateTime deliveryTime;
        private BigDecimal totalPrice;
        private Integer quantity;
        private LocalDateTime ordered_at;
        private OrderStatus orderStatus;
        private Address address;


}
