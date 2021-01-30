import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

public class DeliveryPerson {


    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);
    static String fn = "";
    static String ln = "";
    static boolean validLN = true;
    static boolean validDOB = true;
    static String dobYear;
    static String dobMonth;
    static String dobDay;
    static String studentId;
    static String registerId;
    static String editId;
    static int count = 0;
    static int deleteCount = 0;
    static int registerCount = 0;

    public static void main(String[] args) throws SQLException {
        init_db();  // open the connection to the database

        int menuChoice = 0; // variable used to store main menu choice
        final int STOP_APP = 7; //value from menu that is used to quit the application

        while (menuChoice != STOP_APP) {
            displayMainMenu(); //display the primary menu
            if (in.hasNextInt()) {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice) {
                    case 1:
                        displayAllDeliveryPerson(); //The code for this method is already done for you below
                        break;
                    case 2:
                        addNewDeliveryPerson(); //You need to code this method below
                        break;
                    case 3:
                        deleteDeliveryPerson(); //You need to code this method below
                        break;
                    case 4:
                        System.out.println("Program is closing...");
                        cleanup_resources();  // close the connection to the database when finished program
                        break;
                    default:
                        System.out.println("You entered an valid choice, please try again...");
                }
            } else {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an valid choice, please try again...");
            }
        }
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

                System.out.printf("%-12d %-15s %-20s %-10s %-20s %-10s %-15s %-15s %-10s %-10s\n", delivery_person_id, first_name, last_name, address1, address2, town, delivery_phone_number,dateOfBirth,access_level,delivery_status);
            }

        } catch (SQLException sqle) {
            System.out.println("Error: failed to display all Delivery People.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }



    public static void addNewDeliveryPerson() throws SQLException {
        //Your code should go here:
        //1: Get the required data from the user (i.e. the student data) and validate the data if needs be
        //2: Insert the data in the database

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


        System.out.println("Please enter the persons DOB");
        do {
            System.out.println("First, what year were they born  (1900 to current year, 4 numbers required)");
            if (in.hasNext()) {
                dobYear = in.next();
                validateYear(dobYear);

            }
        } while (!validDOB);
        validDOB = false;
        while (!validDOB) {
            System.out.println("Next, what month were they born in? (1 - 12, 2 Numbers required)");
            if (in.hasNext()) {
                dobMonth = in.next();
                validateMonth(dobMonth);
            }
        }
        validDOB = false;
        while (!validDOB) {
            System.out.println("finally the date they were born (1 - 31, 2 numbers required)");
            if (in.hasNext()) {
                dobDay = in.next();
                validateDate(dobDay);
            }
        }
        String dobFinal = dobYear + "-" + dobMonth + "-" + dobDay;

        System.out.println("Successfully added Person: " +fn + " "+ ln + " " + dobFinal);


        Statement addNewStudent = con.createStatement();
        addNewStudent.executeUpdate("insert into deliveryPerson values (null,'" + fn + "','" + ln + "','" + dobFinal + "')");

    }


    public static void deleteDeliveryPerson() throws SQLException {

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



    public static boolean validateString(String name) {
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

    public static void validateYear(String dobYear) {
        if (dobYear.length() != 4) {
            System.out.println("The year must contain 4 numbers");
            validDOB = false;

        } else {
            dobYear = dobYear.toLowerCase();
            char[] dobArray = dobYear.toCharArray();
            for (int i = 0; i < dobArray.length; i++) {
                char ch = dobArray[i];
                if (ch >= '0' && ch <= '9') {
                    int dobYearTest = Integer.parseInt(dobYear);
                    if (dobYearTest > 1900 && dobYearTest <= Calendar.getInstance().get(Calendar.YEAR)) {
                        validDOB = true;
                    } else {

                        validDOB = false;
                    }
                } else {
                    validDOB = false;
                }

            }
        }
    }

    public static void validateMonth(String dobYear) {
        if (dobMonth.length() != 2) {
            System.out.println("The month must contain 2 numbers");
            validDOB = false;

        } else {
            dobMonth = dobMonth.toLowerCase();
            char[] dobArray = dobYear.toCharArray();
            for (int i = 0; i < dobArray.length; i++) {
                char ch = dobArray[i];
                if (ch >= '0' && ch <= '9') {
                    int dobMonthTest = Integer.parseInt(dobMonth);
                    if (dobMonthTest >= 1 && dobMonthTest <= 12) {
                        validDOB = true;
                    } else {

                        validDOB = false;
                    }
                } else {
                    validDOB = false;
                }

            }
        }
    }

    public static void validateDate(String dobDay) {

        if (dobDay.length() != 2) {
            System.out.println("The date must contain 2 numbers");
            validDOB = false;
        } else {
            dobDay = dobDay.toLowerCase();
            char[] dobArray = dobDay.toCharArray();
            for (int i = 0; i < dobArray.length; i++) {
                char ch = dobArray[i];
                if (ch >= '0' && ch <= '9') {
                    int dobDayTest = Integer.parseInt(dobDay);
                    if (dobDayTest >= 1 && dobDayTest <= 31) {
                        validDOB = true;
                    } else {
                        validDOB = false;
                    }
                } else {
                    validDOB = false;
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




    public static void displayMainMenu()
    {
        System.out.println("\nMain Menu");
        System.out.println("1: Display all Delivery People");
        System.out.println("2: Display a particular student's modules ");
        System.out.println("3: Create a new student ");
        System.out.println("4: Delete a student ");
        System.out.println("5: Register a student ");
        System.out.println("6: Edit a student's details ");
        System.out.println("7: Exit application\n");
        System.out.print("Enter your choice: ");
    }

    //This menu was created to give the user options when editing a student so they would not have to edit the entire student at once
    public static void displayEditMenu() {
        System.out.println("\nEdit Student Menu");
        System.out.println("1: Edit Student First Name");
        System.out.println("2: Edit Student Last Name");
        System.out.println("3: Edit Student DOB");
        System.out.println("4: Exit to Main Menu\n");
        System.out.print("Enter your choice: ");
    }



    public static void finishEditStudent()
    {
        displayMainMenu();
    }

    public static void cleanup_resources()
    {
        try
        {
            con.close();
        }
        catch (SQLException sqle)
        {
            System.out.println("Error: failed to close the database");
        }
    }

    public static void init_db()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/databaseGroupProject?useTimezone=true&serverTimezone=UTC";
            con = DriverManager.getConnection(url, "root", "admin");
            stmt = con.createStatement();
        }
        catch(Exception e)
        {
            System.out.println("Error: Failed to connect to database\n" + e.getMessage());
        }
    }
}


