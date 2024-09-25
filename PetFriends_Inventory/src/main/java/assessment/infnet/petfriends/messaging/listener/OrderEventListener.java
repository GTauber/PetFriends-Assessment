package assessment.infnet.petfriends.messaging.listener;

import assessment.infnet.petfriends.config.RabbitMQConfig;
import assessment.infnet.petfriends.model.event.OrderCreatedEvent;
import assessment.infnet.petfriends.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @RabbitListener(queues = RabbitMQConfig.ORDERS_QUEUE)
    public void handlePedidoCriadoEvent(@Payload String event) throws JsonProcessingException {
        var orderCreatedEvent = objectMapper.readValue(event, OrderCreatedEvent.class);
        log.info("Received OrderCreatedEvent: {}", orderCreatedEvent);
        orderService.initOrderProcess(orderCreatedEvent);
    }

}
