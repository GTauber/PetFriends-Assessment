package assessment.infnet.perfriendsorders.service;

import assessment.infnet.perfriendsorders.config.RabbitMQConfig;
import assessment.infnet.perfriendsorders.model.event.OrderCreatedEvent;
import assessment.infnet.perfriendsorders.model.event.OrderReadyToSendEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void publishOrderCreatedEvent(OrderCreatedEvent event) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.ORDERS_EXCHANGE,
            RabbitMQConfig.ORDERS_INVENTORY_ROUTING_KEY,
            objectMapper.writeValueAsString(event)
        );
        log.info("Published PedidoCriadoEvent: {}", event.getOrderId());
    }

    public void publishOrderReadyToSendEvent(OrderReadyToSendEvent event) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.ORDERS_EXCHANGE,
            RabbitMQConfig.ORDERS_TRANSPORT_ROUTING_KEY,
            objectMapper.writeValueAsString(event)
        );
        log.info("Published PedidoProntoParaEnvioEvent: {}", event.getOrderId());
    }
}
