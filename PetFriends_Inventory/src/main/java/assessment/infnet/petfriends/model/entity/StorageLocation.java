package assessment.infnet.petfriends.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class StorageLocation {

    private String aisle;
    private String shelf;

}
