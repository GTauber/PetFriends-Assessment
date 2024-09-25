package assessment.infnet.perfriendsorders.model.event;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCreatedEvent {

    private String orderId;
    private List<OrderItem> itens;
    private LocalDateTime orderDate;

    @Data
    @Builder
    public static class OrderItem {
        private String itemId;
        private int qty;
    }

}
