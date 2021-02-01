import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DeliveryPersonView {

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

    // ******************************************************************************************************
    // Beginning of the DISPLAY Delivery Person Section
    // ******************************************************************************************************

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


    // ******************************************************************************************************
    // Beginning of the ADD NEW Delivery Person Section
    // ******************************************************************************************************

    public void addNewDeliveryPerson() throws SQLException {
        String fn;
        String ln;
        int houseNumber;
        boolean validLN = true;
        boolean validHouseNumber = true;
        boolean validaddress2 = true;
        boolean validTown = true;
        boolean validPhone = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Scanner in = new Scanner(System.in);
        String dob = "";
        boolean inputValid = false;

        System.out.println("Please enter the persons FIRST name");

        if (in.hasNextLine()) {
            firstName = in.next();

            if (firstName.length() < 2 || firstName.length() > 20) {
                System.out.println("The first name must contain between 2 and 20 characters");
                addNewDeliveryPerson();
            } else if (!validateString(firstName)) {
                System.out.println("Names cannot contain numbers");
                addNewDeliveryPerson();
            }
        }

        do {
            System.out.println("Please enter the persons LAST name");

            if (in.hasNext()) {
                lastName = in.next();

                if (lastName.length() < 2 || lastName.length() > 20) {
                    System.out.println("The last name must contain between 2 and 20 characters");
                    validLN = false;
                } else if (!validateString(lastName)) {
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
                address1 = in.nextInt();

                if (address1 < 1 || address1 > 2000) {
                    System.out.println("The house Number must contain between 1 and 2000");
                    validHouseNumber = false;
                } else {
                    validHouseNumber = true;
                }
            }
        } while (!validHouseNumber);
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

        do {
            System.out.println("Please enter the persons Town");

            if (in.hasNext()) {
                town = in.next();

                if (town.length() < 2 || town.length() > 20) {
                    System.out.println("The Town Name must contain between 2 and 20 characters");
                    validTown = false;
                } else if (!validateString(town)) {
                    System.out.println("Names cannot contain numbers");
                    validTown = false;
                } else {
                    validTown = true;
                }
            }
        } while (!validTown);

        do {
            System.out.println("Please enter the Phone Number i.e. 087 3987656");

            if (in.hasNext()) {
                deliveryPhoneNumber = in.next();
                if (deliveryPhoneNumber.length() < 7 || deliveryPhoneNumber.length() > 11) {
                    System.out.println("The phone number must contain between 7 to 11 digits including prefix");
                    validPhone = false;
                } else if (!validPhoneNumber(deliveryPhoneNumber)) {
                    System.out.println(" The phone number cannot contain any letters");
                } else {
                    validPhone = true;
                }
            }
        } while (!validPhone);

        do {
                System.out.println("Enter persons date of birth (yyyy-mm-dd):");
                if (in.hasNextLine()) {
                    try {
                        dateOfBirth = in.nextLine();
                        // if parsing the date didn't throw any exceptions,
                        // then string is in the correct format
                        Date date = format.parse(dateOfBirth);
                        inputValid = true;
                    } catch (ParseException e) {
                        System.out.println("Date format incorrect.");
                    }
                } else {
                    //clear the input buffer and start again
                    in.nextLine();
                    System.out.println("You entered an invalid date, please try again...");
                }
        }while (!inputValid);

        Statement addNewStudent = con.createStatement();
        addNewStudent.executeUpdate("insert into delivery_Person values (null,'" + firstName + "','" + lastName + "','" + address1 + "','" + address2 + "','" + town + "','" + deliveryPhoneNumber + "','" + dateOfBirth + 2 + "','" + "true" + "','" + "')");
    }

    // ******************************************************************************************************
    // Beginning of the DELETE Delivery Person Section
    // ******************************************************************************************************

    public void deleteDeliveryPerson() throws SQLException {

        displayAllDeliveryPerson();
        System.out.println("Please enter the id number of the delivery person that you want to delete");
        String id = in.next();
        if(validateEntry(id)) {
            String strStudent = "select count(*) as total from deliveryPerson where delivery_person_id = "+ id;
            rs = stmt.executeQuery(strStudent);
            deleteCount = 0;
            while (rs.next()) {
                deleteCount = rs.getInt("total");
            }

            if (deleteCount > 0) {

                try {
                    Statement deletePerson = con.createStatement();
                    deletePerson.executeUpdate("delete from deliveryPerson where delivery_person_id =" + id + "");
                } catch (Exception e) {
                    System.out.println("unable to delete delivery person");
                }
            }
            else {
                System.out.println("That person does not exist, please try again");
            }
        }

    }














    // ******************************************************************************************************
    // Beginning of the VALIDATION Section
    // ******************************************************************************************************

        public static boolean validateString (String name){
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

        public void validatePhoneNumber (String deliveryPhoneNumber){
        boolean validPhone = true;
            if (deliveryPhoneNumber.length() < 7 || deliveryPhoneNumber.length() > 11) {
                System.out.println("The phone number must contain between 7 to 11 digits including prefix");
                validPhone = false;
            } else {
                deliveryPhoneNumber = deliveryPhoneNumber.toLowerCase();
                char[] phoneArray = deliveryPhoneNumber.toCharArray();
                for (int i = 0; i < phoneArray.length; i++) {
                    char ch = phoneArray[i];
                    if (ch >= '0' && ch <= '9') {
                        validPhone = true;
                    } else {
                        validPhone = false;
                    }
                }
            }
        }

    public static boolean validateEntry(String id) {
        if (id.length() > 2) {
            System.out.println("valid entry, you must enter no more than 2 numbers");
            return false;
        }
        else
        {
            try {
                int tempId = Integer.parseInt(id);
            }
            catch (Exception e){
                System.out.println("valid Text entered, please enter a number");
                return false;
            }
        }return true;
    }
    }



