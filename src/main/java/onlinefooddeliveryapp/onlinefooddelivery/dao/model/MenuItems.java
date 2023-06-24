package onlinefooddeliveryapp.onlinefooddelivery.dao.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("MenuItem")
public class MenuItems{
        @Id
        private String itemId;
        private String itemDescription;
        private BigDecimal price;
        private String menuName;



}
