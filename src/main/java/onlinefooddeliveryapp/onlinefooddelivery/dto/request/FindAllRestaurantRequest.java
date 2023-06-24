package onlinefooddeliveryapp.onlinefooddelivery.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllRestaurantRequest {
    private int numberOfPages;
    private int pages;
}
