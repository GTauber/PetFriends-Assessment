package assessment.infnet.petfriendstransport.model.event;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderReadyToSendEvent {

    private String orderId;
    private DeliveryAddress deliveryAddress;
    private ContactInfo contactInfo;
    private LocalDateTime sendDate;

    @Data
    @Builder
    public static class DeliveryAddress {
        private String street;
        private String number;
        private String complement;
        private String neighborhood;
        private String city;
        private String state;
        private String zipCode;
    }

    @Builder
    @Data
    public static class ContactInfo {
        private String tel;
        private String email;
    }

}
