import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class DeliveryPersonView {
    DeliveryPersonDB dpDB = new DeliveryPersonDB();
    String editId = "";
    DeliveryPerson dp = new DeliveryPerson();
    String firstName = "";
    String lastName = "";
    String address1 = "";
    String address2 = "";
    String town = "";
    String deliveryPhoneNumber = "";
    String dateOfBirth = "";
    String userName = "";
    String password = "";
    boolean validName = true;
    boolean validHouseNumber = true;
    boolean validAddress2 = true;
    boolean validTown = true;
    boolean validPhone = true;
    boolean validUser = true;
    boolean validPw = false;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Scanner in = new Scanner(System.in);
    boolean inputValid = false;

    // ******************************************************************************************************
    // Beginning of the DISPLAY Delivery Person Section
    // ******************************************************************************************************

    public void displayAllDeliveryPerson(Statement stmt) {

        String str = "Select * from delivery_person";


        try {
            ResultSet rs = stmt.executeQuery(str);

            System.out.printf("\n%-12s %-15s %-20s %-10s %-20s %-10s %-15s %-15s %-15s %-10s %-20s %-10s\n", "DP ID", "First Name", "Last Name", "address1", "address2", "town", "Phone Number", "Date of Birth", "Access Level", "Status", "User Name", "Password");
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
                String user_name = rs.getString("user_name");
                String password = rs.getString("password");

                System.out.printf("%-12d %-15s %-20s %-10s %-20s %-10s %-15s %-15s %-15s %-10s %-20s %-10s\n", delivery_person_id, first_name, last_name, address1, address2, town, delivery_phone_number, dateOfBirth, access_level, delivery_status, user_name, password);
            }

        } catch (SQLException sqle) {
            System.out.println("Error: failed to display all Delivery People.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }
    // ******************************************************************************************************
    // Beginning of the DISPLAY ONE Delivery Person Section
    // ******************************************************************************************************

    public void displayDeliveryPerson(Statement stmt) throws SQLException {
        int count;
        String str;
        displayAllDeliveryPerson(stmt);
        System.out.println("Please select the id of the person you would like to display");


        String id = in.next();
        if (validateEntry(id)) {
            str = "select count(*) as total from delivery_Person where delivery_person_id = " + id;
            ResultSet rs = stmt.executeQuery(str);
            count = 0;
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if (count > 0) {
                str = "Select * from delivery_person where delivery_person_id = " + id;

                try {

                    rs = stmt.executeQuery(str);
                    System.out.printf("\n\n%-12s %-15s %-20s %-10s %-20s %-10s %-15s %-15s %-15s %-10s %-20s %-10s\n", "DP ID", "First Name", "Last Name", "address1", "address2", "town", "Phone Number", "Date of Birth", "Access Level", "Status", "User Name", "Password");
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
                        String user_name = rs.getString("user_name");
                        String password = rs.getString("password");

                        System.out.printf("%-12d %-15s %-20s %-10s %-20s %-10s %-15s %-15s %-15s %-10s %-20s %-10s\n", delivery_person_id, first_name, last_name, address1, address2, town, delivery_phone_number, dateOfBirth, access_level, delivery_status, user_name, password);
                    }

                } catch (SQLException sqle) {
                    System.out.println("valid input detected - please use only 1 or 2 numbers");
                    displayDeliveryPerson(stmt);
                }
            } else {
                System.out.println("That person does not exist, please try again");
                displayDeliveryPerson(stmt);
            }
        }
    }


    // ******************************************************************************************************
    // Beginning of the ADD NEW Delivery Person Section
    // ******************************************************************************************************
//
    public void addNewDeliveryPerson(Statement stmt) throws SQLException {


        System.out.println("Please enter the persons FIRST name");

        if (in.hasNextLine()) {
            firstName = in.next();
            dp.setFirstName(firstName);


            if (!validateString(firstName)) {
                System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
                addNewDeliveryPerson(stmt);
                validName = false;
            } else {
                validName = true;
            }
        }

        do {
            System.out.println("Please enter the persons LAST name");

            if (in.hasNext()) {
                lastName = in.next();
                dp.setLastName(lastName);

                if (!validateString(lastName)) {
                    System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
                    validName = false;
                } else {
                    validName = true;
                }
            }
        } while (!validName);


        do {
            System.out.println("Please enter the persons House Number");

            if (in.hasNext()) {
                address1 = in.next();
                dp.setAddress1(address1);
                if (!validateHouseNumber(address1)) {
                    System.out.println("The house Number must be between 1 and 99999");
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
                dp.setAddress2(address2);

                if (!validateString(address2)) {
                    System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
                    validAddress2 = false;
                } else {
                    validAddress2 = true;
                }
            }
        } while (!validAddress2);

        do {
            System.out.println("Please enter the persons Town");

            if (in.hasNext()) {
                town = in.next();
                dp.setTown(town);

                if (!validateString(town)) {
                    System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
                    validTown = false;
                } else {
                    validTown = true;
                }
            }
        } while (!validTown);


        System.out.println("Please enter the Phone Number i.e. 087 3987656");

        do {
            if (in.hasNext()) {
                deliveryPhoneNumber = in.next();
                dp.setDeliveryPhoneNumber(deliveryPhoneNumber);

                if (deliveryPhoneNumber.length() <= 7 || deliveryPhoneNumber.length() >= 13) {
                    System.out.println("The phone number must contain between 7 to 13 digits including prefix");
                    validPhone = false;

                } else {
                    validPhone = true;
                }
            } else {
                validPhone = false;
            }
        } while (!validPhone);

        do {
            System.out.println("Enter persons date of birth (yyyy-mm-dd):");
            inputValid = false;
            if (in.hasNext()) {
                try {
                    dateOfBirth = in.next();
                    dp.setDateOfBirth(dateOfBirth);
                    validateDate(dateOfBirth);
                    inputValid = true;
                } catch (Exception e) {
                    System.out.println("Date of Birth incorrect format");
                    inputValid = false;
                }
            }
        }
        while (!inputValid);


        do {
            System.out.println("Please enter the persons Username - between 2 & 20 characters");

            if (in.hasNext()) {
                userName = in.next();
                dp.setUserName(userName);

                if (!validateString(userName)) {
                    System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
                    validUser = false;
                } else {
                    validUser = true;
                }
            }
        } while (!validUser);

        do {
            System.out.println("Please enter the persons Password - must be 4 characters");
//            try {
                if (in.hasNext()) {
                    password = in.next();
                    dp.setPassword(password);
                    if (password.length() != 4) {
                        System.out.println("Invalid entry - please enter 4 characters");
                        validPw = false;
                    } else {
                        validPw = true;
                    }
                } else {
                    System.out.println("\"Invalid entry - please enter 4 characters");
                    validPw = false;
                }
//            } catch (Exception e) {
//                System.out.println("Invalid entry - please enter 4 characters");
//                validPw = false;
//            }
        } while (!validPw);


        Statement addNewPerson = dpDB.con.createStatement();
        addNewPerson.executeUpdate("insert into delivery_Person values (null ,'" + dp.getFirstName() + "','" + dp.getLastName() + "','" + dp.getAddress1() + "','" + dp.getAddress2() + "','" + dp.getTown() + "','" + dp.getDeliveryPhoneNumber() + "','" + dp.getDateOfBirth() + "','" + 2 + "','" + "true" + "','" + dp.getUserName() + "','" + dp.getPassword() + "')");

    }

    // ******************************************************************************************************
    // Beginning of the EDIT Delivery Person Section
    // ******************************************************************************************************

    //TODO Validate all edit methods
    //TODO Transfer all validation to DP.java

    public void editDeliveryPerson(Statement stmt) throws SQLException {
        DeliveryPersonView dpv = new DeliveryPersonView();
        String str;
        int count;
        displayAllDeliveryPerson(stmt);
        System.out.println("Please enter the id of the person you would like to edit");
        editId = in.next();
        if (validateEntry(editId)) {
            str = "select count(*) as total from delivery_Person where delivery_person_id = " + editId;
            ResultSet rs = stmt.executeQuery(str);
            count = 0;
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if (count > 0) {

                System.out.println("Please enter the id corresponding to the attribute you would like to edit");
                int menuChoiceEditPerson = 0; // variable used to store Edit menu choice
                int stopEdit = 12; //value from menu that is used to close the Edit Student process

                while (menuChoiceEditPerson != stopEdit) {
                    editDeliveryPersonMenu(); //display the primary menu
                    if (in.hasNextInt()) {
                        //get the menu choice from the user
                        menuChoiceEditPerson = in.nextInt();

                        switch (menuChoiceEditPerson) {
                            case 1:
                                editPersonFirstName(); //The code for this method is already done for you below
                                break;
                            case 2:
                                editPersonLastName(); //You need to code this method below
                                break;
                            case 3:
                                editPersonAddress1(); //You need to code this method below
                                break;
                            case 4:
                                editPersonAddress2(); //You need to code this method below
                                break;
                            case 5:
                                editPersonTown(); //You need to code this method below
                                break;
                            case 6:
                                editPersonPhoneNumber(); //You need to code this method below
                                break;
                            case 7:
                                editPersonDoB(); //You need to code this method below
                                break;
                            case 8:
                                editPersonAccessLevel(); //You need to code this method below
                                break;
                            case 9:
                                editPersonStatus(); //You need to code this method below
                                break;
                            case 10:
                                editPersonUserName(); //You need to code this method below
                                break;
                            case 11:
                                editPersonPassword(); //You need to code this method below
                                break;
                            case 12:
                                System.out.println("Returning to main Delivery Menu......");
                                dpv.displayAllDeliveryPerson(stmt);  // close the connection to the database when finished program
                                break;
                            default:
                                System.out.println("You entered an invalid choice, please try again...");
                        }
                    } else {
                        //clear the input buffer and start again
                        in.nextLine();
                        System.out.println("You entered an invalid choice, please try again...");
                    }
                }
            }
        }
    }



    // ******************************************************************************************************
//    // Beginning of the Edit Person Method Section
//    // ******************************************************************************************************

    public void editPersonFirstName() throws SQLException {
        System.out.println("Please enter a new First Name");
        String newName = in.next();
        if (!validateString(newName)) {
            System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
            validName = false;
        } else {
            validName = true;
            Statement editPerson = dpDB.con.createStatement();
            editPerson.executeUpdate("Update delivery_person SET first_name = '" + newName + "' where delivery_person_id = '" + editId + "'");
        }
    }


    public void editPersonLastName () throws SQLException {
        System.out.println("Please enter a new Last Name");
        String newName = in.next();
        if (!validateString(newName)) {
            System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
            validName = false;
        } else {
            validName = true;
            Statement editPerson = dpDB.con.createStatement();
            editPerson.executeUpdate("Update delivery_person SET last_name = '" + newName + "' where delivery_person_id = '" + editId + "'");
        }
    }


    public void editPersonAddress1() throws SQLException {
        System.out.println("Please enter a new House Number");
        String newHouse = in.next();
        if (!validateHouseNumber(newHouse)) {
            System.out.println("The house Number must be between 1 and 99999");
        }
        else {
            Statement editPerson = dpDB.con.createStatement();
            editPerson.executeUpdate("Update delivery_person SET address1 = '" + newHouse + "' where delivery_person_id = '" + editId + "'");
        }
    }

    public void editPersonAddress2() throws SQLException {
        System.out.println("Please enter a new Street Name");
        String newName = in.next();
        if (!validateString(newName)) {
            System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
            validName = false;
        } else {
            validName = true;
            Statement editPerson = dpDB.con.createStatement();
            editPerson.executeUpdate("Update delivery_person SET address2 = '" + newName + "' where delivery_person_id = '" + editId + "'");
        }
    }
    public void editPersonTown() throws SQLException {
        System.out.println("Please enter a new Town");
        String newName = in.next();
        if (!validateString(newName)) {
            System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
            validName = false;
        } else {
            validName = true;
            Statement editPerson = dpDB.con.createStatement();
            editPerson.executeUpdate("Update delivery_person SET town = '" + newName + "' where delivery_person_id = '" + editId + "'");
        }
    }

    public void editPersonPhoneNumber() throws SQLException {
        System.out.println("Please enter a new Phone Number");
        String newPhone = in.next();
        Statement editPerson = dpDB.con.createStatement();
        editPerson.executeUpdate("Update delivery_person SET delivery_phone_number = '" + newPhone + "' where delivery_person_id = '" +editId+"'");
    }

    public void editPersonDoB() throws SQLException {
        System.out.println("Please enter a new Date of Birth YYYY-MM-DD");
        String newDob = in.next();
        if (!validateDate(newDob)) {
            System.out.println("Date of Birth incorrect format");
        } else {
            Statement editPerson = dpDB.con.createStatement();
            editPerson.executeUpdate("Update delivery_person SET dob = '" + newDob + "' where delivery_person_id = '" + editId + "'");
        }
    }

    public void editPersonAccessLevel() throws SQLException {
        System.out.println("Please enter a new access level - 1 for admin, 2 for Delivery access");
        String newAccess = in.next();
        if (newAccess.equals("1") || newAccess.equals("2")) {
            Statement editPerson = dpDB.con.createStatement();
            editPerson.executeUpdate("Update delivery_person SET access_level = '" + newAccess + "' where delivery_person_id = '" + editId + "'");
        } else {
            System.out.println("Invalid input, please enter 1 for admin, 2 for Delivery access");
        }
    }

    public void editPersonStatus() throws SQLException {
        System.out.println("Please enter a new status - true = active, false = inactive");
        String newStatus = in.next();
        if (newStatus.equals("true") || newStatus.equals("false")) {
            Statement editPerson = dpDB.con.createStatement();
            editPerson.executeUpdate("Update delivery_person SET delivery_status = '" + newStatus + "' where delivery_person_id = '" + editId + "'");
        } else {
            System.out.println("Invalid input, please enter true for active or false for inactive");
        }
    }

    public void editPersonUserName() throws SQLException {
        System.out.println("Please enter a new User Name - 20 characters max");
        String newName = in.next();
        if (!validateString(newName)) {
            System.out.println("Names cannot contain numbers and must be between 1 to 20 characters");
            validName = false;
        } else {
            validName = true;
            Statement editPerson = dpDB.con.createStatement();
            editPerson.executeUpdate("Update delivery_person SET user_name = '" + newName + "' where delivery_person_id = '" + editId + "'");
        }
    }

    public void editPersonPassword() throws SQLException {
        boolean valid;
        do {
            try {
                System.out.println("Please enter a new Password - 4 Numbers");
                String newPw = in.next();
                if(newPw.length() == 4) {
                    Statement editPerson = dpDB.con.createStatement();
                    valid = true;
                    editPerson.executeUpdate("Update delivery_person SET password = '" + newPw + "' where delivery_person_id = '" + editId + "'");
                }
                else{
                    System.out.println("invalid entry, please enter 4 Numbers only");
                    valid = false;
                }
            }catch (Exception e){
                System.out.println("invalid entry, please enter 4 Numbers only");
                valid = false;
            }
            }while(!valid);
    }

//    // ******************************************************************************************************
//    // Beginning of the DELETE Delivery Person Section
//    // ******************************************************************************************************
//
               public void deleteDeliveryPerson (Statement stmt) throws SQLException {
                   int deleteCount;
                   String str;
                   ResultSet rs;
                   DeliveryPersonDB dpm = new DeliveryPersonDB();
                   displayAllDeliveryPerson(stmt);
                   System.out.println("Please enter the id number of the delivery person that you want to delete");

                   String id = in.next();
                   if (validateEntry(id)) {
                       str = "select count(*) as total from delivery_Person where delivery_person_id = " + id;

                       rs = stmt.executeQuery(str);
                       deleteCount = 0;
                       while (rs.next()) {
                           deleteCount = rs.getInt("total");
                       }

                       if (deleteCount > 0) {

                           try {
                               Statement deletePerson = dpm.con.createStatement();
                               deletePerson.executeUpdate("delete from delivery_Person where delivery_person_id =" + id + "");
                               System.out.println("Delivery Person with id: " + id + " has been deleted");
                           } catch (Exception e) {
                               System.out.println("unable to delete delivery person");
                           }
                       } else {
                           System.out.println("That person does not exist, please try again");
                       }
                   }

               }

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    // ******************************************************************************************************
//    // Beginning of the VALIDATION Section
//    // ******************************************************************************************************
//
//        public static boolean validateString (String name){
//            name = name.toLowerCase();
//            char[] nameArray = name.toCharArray();
//            for (int i = 0; i < nameArray.length; i++) {
//                char ch = nameArray[i];
//                if (ch >= 'a' && ch <= 'z') {
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        public void validatePhoneNumber (String deliveryPhoneNumber){
//        boolean validPhone = true;
//            if (deliveryPhoneNumber.length() < 7 || deliveryPhoneNumber.length() > 11) {
//                System.out.println("The phone number must contain between 7 to 11 digits including prefix");
//                validPhone = false;
//            } else {
//                deliveryPhoneNumber = deliveryPhoneNumber.toLowerCase();
//                char[] phoneArray = deliveryPhoneNumber.toCharArray();
//                for (int i = 0; i < phoneArray.length; i++) {
//                    char ch = phoneArray[i];
//                    if (ch >= '0' && ch <= '9') {
//                        validPhone = true;
//                    } else {
//                        validPhone = false;
//                    }
//                }
//            }
//        }
//
//    public static boolean validateEntry(String id) {
//        if (id.length() > 2) {
//            System.out.println("valid entry, you must enter no more than 2 numbers");
//            return false;
//        }
//        else
//        {
//            try {
//                int tempId = Integer.parseInt(id);
//            }
//            catch (Exception e){
//                System.out.println("valid Text entered, please enter a number");
//                return false;
//            }
//        }return true;
//    }
//    }


        public boolean validateEntry (String id){
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
            return true;
        }
//TODO fix validate string to include a space
        public boolean validateString (String name){
        if(name.length()>1 && name.length()<20) {
            name = name.toLowerCase();
            char[] nameArray = name.toCharArray();
            for (int i = 0; i < nameArray.length; i++) {
                char ch = nameArray[i];
                if (ch >= 'a' && ch <= 'z') {
                    validName = true;
                    return true;
                }
            }
            return false;
        }
        else
            return false;
        }

    public boolean validateHouseNumber (String name){
        if(name.length()>=1 && name.length()<5) {
            name = name.toLowerCase();
            char[] nameArray = name.toCharArray();
            for (int i = 0; i < nameArray.length; i++) {
                char ch = nameArray[i];
                if (ch >= '0' && ch <= '9') {
                    validHouseNumber = true;
                    return true;
                }
            }
            return false;
        }
        else
            validHouseNumber = false;
            return false;
    }

    public boolean validateDate(String date)  {

        // setting the format for date 2021-02-29
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        // checking the format of start date
        try {
            Date date2 = format.parse(date);
            boolean inputValid = true;
        }
        catch (ParseException e)
        {
            System.out.println("date format is incorrect");
        }
        return false;
    }

    public static void cleanup_resources()
    {
        DeliveryPersonDB dpm = new DeliveryPersonDB();
        try
        {
            dpm.con.close();
        }
        catch (SQLException sqle)
        {
            System.out.println("Error: failed to close the database");
        }
    }

    public static void displayMainMenu()
    {
        System.out.println("\nDelivery Person Main Menu");
        System.out.println("1: Display all Delivery People");
        System.out.println("2: Display a particular Delivery Person ");
        System.out.println("3: Create a new Delivery Person ");
        System.out.println("4: Edit a Person's details ");
        System.out.println("5: Delete a Delivery Person ");
        System.out.println("6: Return to Main Menu\n");
        System.out.print("Enter your choice: ");
    }

    public static void editDeliveryPersonMenu() {
        System.out.println("\nEdit Person Menu");
        System.out.println("1: Edit Person's First Name");
        System.out.println("2: Edit Person's Last Name");
        System.out.println("3: Edit Person's House Number");
        System.out.println("4: Edit Person's Street name");
        System.out.println("5: Edit Person's Town");
        System.out.println("6: Edit Person's Phone Number'");
        System.out.println("7: Edit Person Date of Birth");
        System.out.println("8: Edit Person's Access level");
        System.out.println("9: Edit Person's Status");
        System.out.println("10: Edit Person's Username");
        System.out.println("11: Edit Person's Password");
        System.out.println("12: Exit to Main Menu\n");
        System.out.print("Enter your choice: ");
    }
//
//
}


