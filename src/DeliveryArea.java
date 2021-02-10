import java.sql.*;

public class DeliveryArea {

    private String dAreaName;
    private String description;
    private int id, deliveryPersonId;


    public DeliveryArea(int id, String dAreaName, String description, int deliveryPersonId) throws DeliveryAreaExceptionHandler
    {

        try {
            validateDeliveryAreaName(dAreaName);
            //validateDescription(description);
            //validateId(deliveryPersonId);
            //validateId(id);
        }
        catch (DeliveryAreaExceptionHandler e) {
            System.out.println(e.getMessage());
        }

        this.id = id;
        this.dAreaName = dAreaName;
        this.description = description;
        this.deliveryPersonId = deliveryPersonId;
    }

    public DeliveryArea(String dAreaName, String description, int deliveryPersonId)
    {
        this.id = id;
        this.dAreaName = dAreaName;
        this.description = description;
        this.deliveryPersonId = deliveryPersonId;
    }

    public DeliveryArea()
    {

    }


    public int getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(int deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    private ResultSet rs = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDAreaName() {
        return dAreaName;
    }

    public void setDAreaName(String DAreaName) {
        this.dAreaName = DAreaName;
    }


    public void validateDeliveryAreaName(String DAname) throws  DeliveryAreaExceptionHandler {
        if (DAname.isBlank() || DAname.isEmpty())
            throw new DeliveryAreaExceptionHandler("Delivery area not specified");
        else if (DAname.length() < 2)
            throw new DeliveryAreaExceptionHandler("Delivery area does not meet minimum length requirements");
        else if (DAname.length() > 19)
            throw new DeliveryAreaExceptionHandler("Delivery area name exceeds maximum length requirements");
    }

}
