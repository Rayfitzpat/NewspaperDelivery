import java.sql.*;

public class DeliveryArea {

    private String dAreaName;
    private String description;
    private String desc = "";
    private int id, deliveryPersonId, minLength, maxLength;
    boolean validName = false;
    boolean validDesc = false;



    public DeliveryArea(int id, String dAreaName, String description, int deliveryPersonId) throws DeliveryAreaExceptionHandler
    {
        try
        {
            validateDeliveryAreaName(dAreaName);
            //validateDescription(description);
            //validateId(deliveryPersonId);
            //validateId(id);
        }
        catch (DeliveryAreaExceptionHandler e)
        {
            System.out.println(e.getMessage());
        }

        this.id = id;
        this.dAreaName = dAreaName;
        this.description = description;
        this.deliveryPersonId = deliveryPersonId;
    }

    public DeliveryArea(String dAreaName, String description, int deliveryPersonId) {
        this.id = id;
        this.dAreaName = dAreaName;
        this.description = description;
        this.deliveryPersonId = deliveryPersonId;
    }

    public DeliveryArea() {

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

// START OF Validation

    public void validateDeliveryAreaName(String DAname) throws DeliveryAreaExceptionHandler {
        if (DAname.isBlank() || DAname.isEmpty())
            throw new DeliveryAreaExceptionHandler("Delivery area not specified");
        else if (DAname.length() < 2)
            throw new DeliveryAreaExceptionHandler("Delivery area does not meet minimum length requirements");
        else if (DAname.length() > 19)
            throw new DeliveryAreaExceptionHandler("Delivery area name exceeds maximum length requirements");
    }

    public boolean validateString(String line) {
        int minLength = 2;
        int maxLength = 20;

        if (line == null) {
            return false;
        } else if (line.isBlank()) {
            return false;
        } else if (line.length() < minLength) {
            return false;
        } else if (line.length() > maxLength) {
            return false;
        } else {
            // checking if line has any numbers
            char[] charArray = line.toCharArray();
            for (char c : charArray) {
                if (Character.isDigit(c)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }



    public boolean validateDesc(String desc)
    {
        int minLength = 2;
        int maxLength = 14;

        if (desc == null)
        {
            return false;
        }
        else if (desc.isBlank()) {
            return false;
        }
        else if (desc.length() < minLength) {
            return false;
        }
        else if (desc.length() > maxLength) {
            return false;
        }
        else
            {
            //Checking for numbers
            char[] charArray = desc.toCharArray();
            for (char c : charArray) {
                if (Character.isDigit(c)) {
                    return false;
                }
                else {
                    return true;
                }
            }
        }
    }


    public boolean validateEntry(String id)
        {
        if (id.length() > 2) {
            System.out.println("invalid entry, you must enter no more than 2 numbers");
            return false;
        } else {
            try {
                int tempId = Integer.parseInt(id);
            } catch (Exception e) {
                System.out.println("invalid Text entered, please enter a number");
                return false;
            }
        }
    }
}
