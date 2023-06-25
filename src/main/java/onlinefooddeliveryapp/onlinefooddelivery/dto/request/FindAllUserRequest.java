package onlinefooddeliveryapp.onlinefooddelivery.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllUserRequest {
    private int numberOfPages;
    private int pages;
}
