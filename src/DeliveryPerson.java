import java.util.Scanner;

public class DeliveryPerson {

    private String name;
    private int deliveryPersonId;
    private String firstName;
    private String lastName;
    private int address1;
    private String address2;
    private String town;
    private String deliveryPhoneNumber;
    private String dateOfBirth;
    private String accessLevel;
    private String deliveryStatus;

    Scanner in = new Scanner(System.in);


    public int getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(int deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAddress1() {
        return address1;
    }

    public void setAddress1(int address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDeliveryPhoneNumber() {
        return deliveryPhoneNumber;
    }

    public void setDeliveryPhoneNumber(String deliveryPhoneNumber) {
        this.deliveryPhoneNumber = deliveryPhoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public DeliveryPerson() {

    }

    public DeliveryPerson(int deliveryPersonId, String firstName, String lastName, int address1, String address2, String town, String deliveryPhoneNumber, String dateOfBirth, String accessLevel, String deliveryStatus) {
        this.deliveryPersonId = deliveryPersonId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.town = town;
        this.deliveryPhoneNumber = deliveryPhoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.accessLevel = accessLevel;
        this.deliveryStatus = deliveryStatus;
    }


}
