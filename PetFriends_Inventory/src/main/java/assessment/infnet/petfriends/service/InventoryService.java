package assessment.infnet.petfriends.service;

import assessment.infnet.petfriends.exception.ItemNotFoundInInventoryException;
import assessment.infnet.petfriends.model.event.OrderCreatedEvent;
import assessment.infnet.petfriends.model.event.OrderCreatedEvent.OrderItem;
import assessment.infnet.petfriends.repository.InventoryItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;

    public void updateInventory(OrderCreatedEvent orderCreatedEvent) {
        log.info("Updating inventory for order: {}", orderCreatedEvent);
        var inventoryItens = inventoryItemRepository.findInventoryItemByProductIdIn(orderCreatedEvent.getItens().stream()
            .map(OrderItem::getItemId).toList());
        if (inventoryItens == null) throw new ItemNotFoundInInventoryException();
    }
}
