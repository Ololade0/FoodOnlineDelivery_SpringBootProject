package onlinefooddeliveryapp.onlinefooddelivery.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllOrderRequest {
    private Long userId;
    private Long orderId;
    private int numberOfPages;
    private int pages;
}
