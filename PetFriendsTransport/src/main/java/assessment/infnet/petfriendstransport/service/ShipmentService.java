package assessment.infnet.petfriendstransport.service;

import assessment.infnet.petfriendstransport.enums.ShipmentStatus;
import assessment.infnet.petfriendstransport.model.entity.DeliveryAddressMapper;
import assessment.infnet.petfriendstransport.model.entity.Shipment;
import assessment.infnet.petfriendstransport.model.event.OrderReadyToSendEvent;
import assessment.infnet.petfriendstransport.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final DeliveryAddressMapper deliveryAddressMapper;

    public void initShipmentProduct(OrderReadyToSendEvent order) {
        var shipment = Shipment.builder()
            .orderId(order.getOrderId())
            .deliveryAddress(deliveryAddressMapper.toEntity(order.getDeliveryAddress()))
            .status(ShipmentStatus.PENDING)
            .build();

        shipmentRepository.save(shipment);
        log.info("Init shipment for order: {}", order);
    }

    public void updateShipmentStatus(Long shipmentId, ShipmentStatus status) {
        var shipment = shipmentRepository.findById(shipmentId)
            .orElseThrow(() -> new IllegalArgumentException("Shipment not found"));

        shipment.setStatus(status);
        shipmentRepository.save(shipment);
        log.info("Updated shipment status: {}", shipment);
    }

}
