package assessment.infnet.petfriends.exception;

public class ItemNotFoundInInventoryException extends RuntimeException {

    public ItemNotFoundInInventoryException() {
        super("Item not found in inventory");
    }

}
