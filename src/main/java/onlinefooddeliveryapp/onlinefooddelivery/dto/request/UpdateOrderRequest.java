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
public class UpdateOrderRequest {
    private String orderId;


    private BigDecimal totalPrice;
    private LocalDateTime updatedAt;
    private Address address;
    private OrderStatus orderStatus;
}
