package assessment.infnet.petfriendstransport.repository;

import assessment.infnet.petfriendstransport.model.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

}
