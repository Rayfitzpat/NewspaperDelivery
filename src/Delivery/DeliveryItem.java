package Delivery;

/*Base class for all items that must be included in the delivery docket.
* I created this class to ba able to store these items in the same list and reduce amount of code,
* but still separate invoice and publication deliveries*/
public class DeliveryItem {
    private int id;
    private int customerID;
    private String customerName;
    private String customerAddress;
    private boolean isDelivered;
    private String type; // invoice or publication

    // constructor with no id
    public DeliveryItem( int customerID, String customerName, String customerAddress, String type, boolean isDelivered) throws DeliveryDocketExceptionHandler {

        id = 0;
        // validating the input, if all ok, the exception will not be thrown
        // and the values will be initialised
        try {
            validateCustomerId(customerID);
            validateCustomerName(customerName);
            validateCustomerAddress(customerAddress);
            validateType(type);
        }
        catch (DeliveryDocketExceptionHandler e) {
            throw e;
        }

        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.type = type;
        this.isDelivered = isDelivered;
    }


    private void validateType(String type) {
        // can be either invoice or publication
    }

    public void validateCustomerAddress(String customerAddress) {
        // can validate for length
    }

    public void validateCustomerName(String customerName) throws DeliveryDocketExceptionHandler {
        // check if exists in db
    }

    public void validateCustomerId(int customerID) throws DeliveryDocketExceptionHandler {
        // check if exists in db
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
