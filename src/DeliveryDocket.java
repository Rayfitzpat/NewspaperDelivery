import java.util.ArrayList;

public class DeliveryDocket {
    private ArrayList<DeliveryItem> deliveryItems;
    private String date;
    private int deliveryAreaId;
    private String deliveryAreaName;
    private String deliveryPersonName;

    public DeliveryDocket(ArrayList<DeliveryItem> deliveryItems, String date, int deliveryAreaId, String deliveryAreaName, String deliveryPersonName) throws DeliveryDocketExceptionHandler {

        try {
            validateDate(date);
            validateDeliveryAreaID(deliveryAreaId);
            validateDeliveryAreaName(deliveryAreaName);
            validateDeliveryPerson();
        }
        catch (DeliveryDocketExceptionHandler e) {
            throw e;
        }

        this.deliveryItems = deliveryItems;
        this.date = date;
        this.deliveryAreaId = deliveryAreaId;
        this.deliveryAreaName = deliveryAreaName;
        this.deliveryPersonName = deliveryPersonName;
    }

    public void validateDeliveryPerson() {
    }

    public void validateDeliveryAreaName(String deliveryAreaName) throws DeliveryDocketExceptionHandler{
    }

    public void validateDeliveryAreaID(int deliveryAreaId) throws DeliveryDocketExceptionHandler {
    }

    public void validateDate(String date) throws DeliveryDocketExceptionHandler{
    }


    public ArrayList<DeliveryItem> getDeliveryItems() {
        return deliveryItems;
    }

    public void setDeliveryItems(ArrayList<DeliveryItem> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDeliveryAreaId() {
        return deliveryAreaId;
    }

    public void setDeliveryAreaId(int deliveryAreaId) {
        this.deliveryAreaId = deliveryAreaId;
    }

    public String getDeliveryAreaName() {
        return deliveryAreaName;
    }

    public void setDeliveryAreaName(String deliveryAreaName) {
        this.deliveryAreaName = deliveryAreaName;
    }

    public String getDeliveryPersonName() {
        return deliveryPersonName;
    }

    public void setDeliveryPersonName(String deliveryPersonName) {
        this.deliveryPersonName = deliveryPersonName;
    }
}
