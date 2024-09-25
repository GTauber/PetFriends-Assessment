package assessment.infnet.perfriendsorders.controller;

import assessment.infnet.perfriendsorders.model.event.OrderCreatedEvent;
import assessment.infnet.perfriendsorders.model.event.OrderReadyToSendEvent;
import assessment.infnet.perfriendsorders.service.OrderEventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderEventPublisher eventPublisher;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderCreatedEvent orderCreatedEvent) {
        try {
            eventPublisher.publishOrderCreatedEvent(orderCreatedEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error publishing OrderCreatedEvent", e);
        }
        return ResponseEntity.ok("OrderCreatedEvent published");
    }

    @PostMapping("/readyToShip")
    public ResponseEntity<String> readyToShipPedido(@RequestBody OrderReadyToSendEvent orderReadyToSendEvent) {
        try {
            eventPublisher.publishOrderReadyToSendEvent(orderReadyToSendEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error publishing OrderReadyToSendEvent", e);
        }
        return ResponseEntity.ok("OrderReadyToSendEvent published");
    }

}
