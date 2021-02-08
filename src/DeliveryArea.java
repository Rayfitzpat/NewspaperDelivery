import java.sql.*;
import java.util.Scanner;

public class DeliveryArea {

    private String description;
    private String address;
    private String customer;
    private String dAreaName;
    private int id, deliveryPersonId;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomers() {
        return customer;
    }

    public void setCustomers(String customers) {
        this.customer = customers;
    }

    public String getDAreaName() {
        return dAreaName;
    }

    public void setDAreaName(String DAreaName) {
        this.dAreaName = DAreaName;
    }

    public DeliveryArea()
    {

    }

    public DeliveryArea(int id, String dAreaName, String description)
    {
        this.id = id;
        this.dAreaName = dAreaName;
        this.description = description;
    }
}
