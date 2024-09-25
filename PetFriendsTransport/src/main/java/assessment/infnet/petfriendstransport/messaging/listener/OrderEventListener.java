package assessment.infnet.petfriendstransport.messaging.listener;

import assessment.infnet.petfriendstransport.config.RabbitMQConfig;
import assessment.infnet.petfriendstransport.model.event.OrderReadyToSendEvent;
import assessment.infnet.petfriendstransport.service.ShipmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEventListener {

    private final ObjectMapper objectMapper;
    private final ShipmentService shipmentService;

    @RabbitListener(queues = RabbitMQConfig.ORDERS_QUEUE)
    public void handlePedidoProntoParaEnvioEvent(@Payload String event) throws JsonProcessingException {
        var order = objectMapper.readValue(event, OrderReadyToSendEvent.class);
        log.info("Received OrderReadyToSendEvent: {}", order);
        shipmentService.initShipmentProduct(order);
    }

}
