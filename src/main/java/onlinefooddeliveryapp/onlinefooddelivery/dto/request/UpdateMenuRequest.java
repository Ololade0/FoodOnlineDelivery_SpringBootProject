package onlinefooddeliveryapp.onlinefooddelivery.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMenuRequest {
    private String itemId;
    private String itemDescription;
    private BigDecimal price;
    private String menuName;
    private LocalDateTime updatedAt;


}
