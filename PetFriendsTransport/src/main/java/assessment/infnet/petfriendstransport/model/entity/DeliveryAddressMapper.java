package assessment.infnet.petfriendstransport.model.entity;

import assessment.infnet.petfriendstransport.model.event.OrderReadyToSendEvent;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface DeliveryAddressMapper {
    DeliveryAddress toEntity(OrderReadyToSendEvent.DeliveryAddress deliveryAddressFromOrder);
}