package Delivery;

// this class will represent publication delivery item in the delivery docket
public class PublicationDeliveryItem extends DeliveryItem {

    private int publicationId;
    public PublicationDeliveryItem(int publicationId, int customerID, String customerName, String customerAddress, String type, boolean isDelivered) throws DeliveryDocketExceptionHandler {
        super(customerID, customerName, customerAddress, type, isDelivered);

        try {
            validatePublicationId(publicationId);
        }
        catch (DeliveryDocketExceptionHandler e) {
            throw e;
        }
    }

    public PublicationDeliveryItem() {

    }

    public void validatePublicationId(int publicationId) throws DeliveryDocketExceptionHandler
    {

    }
}
