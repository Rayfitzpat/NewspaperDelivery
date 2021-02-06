import java.sql.*;
import java.util.Scanner;

/**
 * Need to work on
 * - entering delivery areas
 */
public class CustomerMain {

    // connection with the database
    static Connection con = null;
    static Statement stmt = null;
    //static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);




    public static void main(String[] args) {

        init_db();  // open the connection to the database
        int menuChoice = 0; // variable used to store main menu choice
        final int STOP_APP = 7; //value from menu that is used to quit the application
        int customerID; // setting variable to temporary customer info storage
        CustomerView view;

        // initialising view
        try {
            // if view can be initialised with no errors, then connection
            // with db is set correctly
            view = new CustomerView(stmt);

            // running the menu
            while (menuChoice != STOP_APP) {
                displayCustomerMenu(); //display the primary customer menu
                if (in.hasNextInt()) {
                    //get the menu choice from the user
                    menuChoice = in.nextInt();
                    view.fetchCustomers(stmt); // resetting local copy of customers
                    switch (menuChoice) {
                        case 1 -> view.printCustomers();
                        case 2 -> {
                            customerID = askUserToEnterCustomerID(view);
                            System.out.println(view.getCustomers().get(customerID - 1));
                        }
                        case 3 -> addNewCustomer(view);
                        case 4 -> {
                            customerID = askUserToEnterCustomerID(view);
                            editCustomer(customerID); //You need to code this method below
                        }
                        case 5 -> {
                            customerID = askUserToEnterCustomerID(view);
                            deleteCustomer(customerID); //You need to code this method below
                        }
                        case 9 -> {
                            System.out.println("Program is closing...");
                            cleanup_resources();  // close the connection to the database when finished program
                        }
                        default -> System.out.println("You entered an invalid choice, please try again...");
                    }
                } else {
                    //clear the input buffer and start again
                    System.out.println("You entered an invalid choice, please try again...");
                }
            }
        }
        catch (CustomerExceptionHandler e) {

            System.out.println(e.getMessage());
        }

    }

    public static void addNewCustomer(CustomerView view) {
        boolean isValid = false;

        while(!isValid) {
            String firstName = askUserToEnterName("first name");
            String lastName = askUserToEnterName("last name");
            int address1 = askUserToEnterAddress1();
            String address2 = askUserToEnterAddress2("address line 2");
            String town = askUserToEnterAddress2("town");
            String eircode = askUserToEnterEircode();
            String phoneNumber = askUserToEnterPhone();
            String holidayStartDate = null;
            String holidayendDate = null;

            // if customer has a holiday in the future, initialise the holiday fields
            if (askCustomerAboutHoliday()) {
                holidayStartDate = askUserToEnterHolidayStart();
                holidayendDate = askUserToEnterHolidayEnd();
            }
            // status of a new customer is active by default
            boolean status = true;
            int deliveryAreaId = askUserToEnterDeliveryAreaId();

            // creating new customer object
            try {
                Customer registerCustomer = new Customer(firstName, lastName, address1, address2, town, eircode, phoneNumber, holidayStartDate, holidayendDate, status, deliveryAreaId);

                // if customer can be created, attempting insert into the db
                view.insertCustomer(registerCustomer, con);
                isValid = true;
            }
            catch ( CustomerExceptionHandler e) {
                System.out.println("***" + e.getMessage() + "***");
                System.out.println("There was an error with adding new customer. Please try again");
            }
        }

    }

    public static void deleteCustomer(int customerId) {

    }

    public static void editCustomer(int customerId ) {

    }

    public static int askUserToEnterCustomerID(CustomerView view) {
        Scanner in = new Scanner(System.in);
        boolean isValid = false;
        int customerID = 0;

        // getting id if the customer
        while (!isValid)
        {
            System.out.println("Enter id of the customer: ");
            if(in.hasNextInt())
            {
                customerID = in.nextInt();
                // checking if student exists
                if(view.ifCustomerExists(customerID))
                {
                    isValid = true;
                }
                else
                {
                    System.out.println("Customer with id " + customerID + " doesn`t exist");
                }
            }
            else
            {
                in.nextLine();
                System.out.println("Customer id should be a number");
            }
        }
        return customerID;
    }


    public static String askUserToEnterName(String initial)
    {
        Scanner in = new Scanner(System.in);
        Customer c = new Customer();
        String name = "";
        boolean inputValid = false;
        while (!inputValid)
        {
            System.out.println("Enter customer " + initial + " name(25 chars or less): ");
            if (in.hasNextLine())
            {
                name = in.nextLine();
                try {
                    c.setFirstName(name);
                    // if validation was successful
                    inputValid = true;
                }catch (CustomerExceptionHandler e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid " + initial + " name, please try again...");
            }
        }
        return name;
    }


    public static int askUserToEnterAddress1()
    {
        Scanner in = new Scanner(System.in);
        Customer c = new Customer();
        int address1 = 0;
        boolean inputValid = false;

        while (!inputValid)
        {
            System.out.println("Enter customer address line 1 (number of house or apartment): ");
            if (in.hasNextInt())
            {
                address1 = in.nextInt();
                try {
                    c.validateAddress1(address1);
                    // if validation was successful
                    inputValid = true;
                }catch (CustomerExceptionHandler e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid address line 1, please try again...");
            }
        }
        return address1;
    }

    public static String askUserToEnterEircode() {

        Scanner in = new Scanner(System.in);
        Customer c = new Customer();
        String eircode = "";
        boolean inputValid = false;

        while (!inputValid)
        {
            System.out.println("Enter customer eircode (Example: A11AA11): ");
            if (in.hasNextLine())
            {
                eircode = in.nextLine();
                try {
                    c.validateEircode(eircode);
                    // if validation was successful
                    inputValid = true;
                }catch (CustomerExceptionHandler e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid eircode, please try again...");
            }
        }

        return eircode;
    }


    // used for Address line2 and Town inputs
    public static String askUserToEnterAddress2(String addressType)
    {
        Scanner in = new Scanner(System.in);
        Customer c = new Customer();
        String address = "";
        boolean inputValid = false;
        while (!inputValid)
        {
            System.out.println("Enter customer " + addressType + " (35 chars or less): ");
            if (in.hasNextLine())
            {
                address = in.nextLine();
                try {
                    c.setAddress2(address);
                    // if validation was successful
                    inputValid = true;
                }catch (CustomerExceptionHandler e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid " + addressType + ", please try again...");
            }
        }
        return address;
    }

    public static String askUserToEnterPhone() {
        Scanner in = new Scanner(System.in);
        Customer c = new Customer();
        String phoneNumber = "";
        boolean inputValid = false;

        while (!inputValid)
        {
            System.out.println("Enter customer phone number (Example: 086 819 0499): ");
            if (in.hasNextLine())
            {
                phoneNumber = in.nextLine();
                try {
                    c.validatePhoneNumber(phoneNumber);
                    // if validation was successful
                    inputValid = true;
                }catch (CustomerExceptionHandler e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid phone number , please try again...");
            }
        }
        return phoneNumber;
    }

    // if a customer has a holiday, return true. If not, return false
    public static boolean askCustomerAboutHoliday() {
        Scanner in = new Scanner(System.in);
        String answer;
        boolean inputValid = false;
        boolean hasHoliday = false;

        while (!inputValid)
        {
            System.out.println("Does a customer have a planned holiday and doesn't want to receive the newspapers during that period? (yes/no)");
            if (in.hasNextLine())
            {
                // if customer reply is "yes" or "no", save it and exit the loo0
                answer = in.nextLine();
                if (answer.equals("yes") || answer.equals("Yes")) {
                    inputValid = true;
                    hasHoliday = true;
                }
                else if(answer.equals("no") || answer.equals("No")) {
                    inputValid = true;
                    hasHoliday = false;
                }
                else {
                    System.out.println("You entered an invalid answer, please use \"yes\" or \"no\"...");
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid answer, please use \"yes\" or \"no\"...");
            }
        }
        return hasHoliday;
    }

    public static String askUserToEnterHolidayStart() {
        Scanner in = new Scanner(System.in);
        Customer c = new Customer();
        String holidayStart = "";
        boolean inputValid = false;

        while (!inputValid)
        {
            System.out.println("Enter holiday start date (Example: 2021-08-27): ");
            if (in.hasNextLine())
            {
                holidayStart = in.nextLine();
                try {
                    c.validateDate(holidayStart);
                    // if validation was successful
                    inputValid = true;
                } catch (CustomerExceptionHandler e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid holiday start date, please try again...");
            }
        }
        return holidayStart;
    }

    public static String askUserToEnterHolidayEnd() {
        Scanner in = new Scanner(System.in);
        Customer c = new Customer();
        String holidayEnd = "";
        boolean inputValid = false;

        while (!inputValid)
        {
            System.out.println("Enter holiday end date (Example: 2021-08-27): ");
            if (in.hasNextLine())
            {
                holidayEnd = in.nextLine();
                try {
                    c.validateDate(holidayEnd);
                    // if validation was successful
                    inputValid = true;
                }catch (CustomerExceptionHandler e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid holiday end date, please try again...");
            }
        }
        return holidayEnd;
    }

    // any int is ok for now
    // Sprint2 - finish this
    public static int askUserToEnterDeliveryAreaId() {
        Scanner in = new Scanner(System.in);
        int deliveryAreaId = 0;
        boolean inputValid = false;

        while (!inputValid)
        {
            System.out.println("Enter customer delivery area id: ");
            if (in.hasNextInt())
            {
                deliveryAreaId = in.nextInt();
                inputValid = true;
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid delivery area id, please try again...");
            }
        }
        return deliveryAreaId;
    }

    public static void displayCustomerMenu () {
        System.out.println("\nCustomer Menu");
        System.out.println("1: Display all customers");
        System.out.println("2: Display customer by ID");
        System.out.println("3: Create a new customer ");
        System.out.println("4: Edit customer's details ");
        System.out.println("5: Delete(deactivate) a customer ");
        System.out.println("9: Exit application\n");
        System.out.print("Enter your choice: ");
    }

    public static void cleanup_resources() {
        try {
            con.close();
        } catch (SQLException sqle) {
            System.out.println("Error: failed to close the database");
        }
    }

    public static void init_db() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/databaseGroupProject?useTimezone=true&serverTimezone=UTC";
            con = DriverManager.getConnection(url, "root", "admin");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error: Failed to connect to database\n" + e.getMessage());
        }
    }
}
