package onlinefooddeliveryapp.onlinefooddelivery.dao.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Role")

public class Role {
    @Id

    private Long id;
    private RoleType roleType;

       public Role(RoleType roleType) {
            this.roleType = roleType;
        }


}
