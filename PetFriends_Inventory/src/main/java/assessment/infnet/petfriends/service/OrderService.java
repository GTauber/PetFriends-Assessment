package assessment.infnet.petfriends.service;

import assessment.infnet.petfriends.model.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final InventoryService inventoryService;

    public void initOrderProcess(OrderCreatedEvent orderCreatedEvent) {
        log.info("Order process started for order: {}", orderCreatedEvent);
        inventoryService.updateInventory(orderCreatedEvent);

    }

}
