package onlinefooddeliveryapp.onlinefooddelivery.dao.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("Orders")
public class Order {
        @Id
        private String id;

        private LocalDateTime deliveryTime;
         private BigDecimal itemPrice;
         private BigDecimal totalPrice;
         private Integer quantity;
        private LocalDateTime ordered_at;
        private OrderStatus orderStatus;

        @DBRef
        private Address address = new Address();
    }

