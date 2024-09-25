package assessment.infnet.petfriends.repository;

import assessment.infnet.petfriends.model.entity.InventoryItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findInventoryItemByProductIdIn(List<String> productIds);

}
