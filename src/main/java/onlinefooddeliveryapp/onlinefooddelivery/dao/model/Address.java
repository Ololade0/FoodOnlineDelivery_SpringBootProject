package onlinefooddeliveryapp.onlinefooddelivery.dao.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("Address")
public class Address {
    @Id
    private String addressId;
    private String area;
    private String city;
    private String state;
    private String country;
    private String houseNumber;
}

