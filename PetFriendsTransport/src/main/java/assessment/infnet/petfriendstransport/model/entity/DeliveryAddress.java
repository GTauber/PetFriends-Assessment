package assessment.infnet.petfriendstransport.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddress {

    private String street;
    private String city;
    private String state;
    private String zipCode;

}
