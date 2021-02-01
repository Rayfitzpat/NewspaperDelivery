import java.sql.SQLException;
import java.util.Scanner;

public class DeliveryPersonView {

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

    public DeliveryPersonView() {

    }

    public DeliveryPersonView(int deliveryPersonId, String firstName, String lastName, int address1, String address2, String town, String deliveryPhoneNumber, String dateOfBirth, String accessLevel, String deliveryStatus) {
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

    public static void displayAllDeliveryPerson() {

        String str = "Select * from delivery_person";

        try {
            rs = stmt.executeQuery(str);
            System.out.printf("\n%-12s %-15s %-20s %-10s %-20s %-10s %-15s %-15s %-10s %-10s\n", "DP ID", "First Name", "Last Name", "address1", "address2", "town", "Phone Number", "Date of Birth", "Access Level", "Status");
            while (rs.next()) {
                int delivery_person_id = rs.getInt("delivery_person_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String address1 = rs.getString("address1");
                String address2 = rs.getString("address2");
                String town = rs.getString("town");
                String delivery_phone_number = rs.getString("delivery_phone_number");
                String dateOfBirth = rs.getString("dob");
                String access_level = rs.getString("access_level");
                String delivery_status = rs.getString("delivery_status");

                System.out.printf("%-12d %-15s %-20s %-10s %-20s %-10s %-15s %-15s %-10s %-10s\n", delivery_person_id, first_name, last_name, address1, address2, town, delivery_phone_number, dateOfBirth, access_level, delivery_status);
            }

        } catch (SQLException sqle) {
            System.out.println("Error: failed to display all Delivery People.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }

    public void addNewDeliveryPerson() throws SQLException {
        String fn;
        String ln;
        int houseNumber;
        boolean validLN = true;
        boolean validHouseNumber = true;
        boolean validaddress2 = true;
        System.out.println("Please enter the persons FIRST name");

        if (in.hasNextLine()) {
            fn = in.next();

            if (fn.length() < 2 || fn.length() > 20) {
                System.out.println("The first name must contain between 2 and 20 characters");
                addNewDeliveryPerson();
            } else if (!validateString(fn)) {
                System.out.println("Names cannot contain numbers");
                addNewDeliveryPerson();
            }
        }

        do {
            System.out.println("Please enter the persons LAST name");

            if (in.hasNext()) {
                ln = in.next();

                if (ln.length() < 2 || ln.length() > 20) {
                    System.out.println("The last name must contain between 2 and 20 characters");
                    validLN = false;
                } else if (!validateString(ln)) {
                    System.out.println("Names cannot contain numbers");
                    validLN = false;
                } else {
                    validLN = true;
                }
            }
        } while (!validLN);
        do {
            System.out.println("Please enter the persons House Number");

            if (in.hasNextInt()) {
                houseNumber = in.nextInt();

                if (houseNumber < 1 || houseNumber > 2000) {
                    System.out.println("The house Number must contain between 1 and 2000");
                    validHouseNumber = false;
                } else {
                    validHouseNumber = true;
                }
            }
        }  while (!validHouseNumber) ;
        do {
            System.out.println("Please enter the persons Street name");

            if (in.hasNext()) {
                address2 = in.next();

                if (address2.length() < 2 || address2.length() > 30) {
                    System.out.println("The Street Name must contain between 2 and 30 characters");
                    validaddress2 = false;
                } else if (!validateString(address2)) {
                    System.out.println("Names cannot contain numbers");
                    validaddress2 = false;
                } else {
                    validaddress2 = true;
                }
            }
        } while (!validaddress2);
    }





    public boolean validateString(String name) {
        name = name.toLowerCase();
        char[] nameArray = name.toCharArray();
        for (int i = 0; i < nameArray.length; i++) {
            char ch = nameArray[i];
            if (ch >= 'a' && ch <= 'z') {
                return true;
            }
        }
        return false;
    }
}