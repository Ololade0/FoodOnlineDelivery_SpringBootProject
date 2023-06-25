package onlinefooddeliveryapp.onlinefooddelivery.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserResponse {
    private String userId;
    private String email;
}
