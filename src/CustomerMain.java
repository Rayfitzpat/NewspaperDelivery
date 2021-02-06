import java.sql.*;
import java.util.Scanner;

public class CustomerMain {

    // connection with the database
    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);




    public static void main(String[] args) {

        init_db();  // open the connection to the database
        int menuChoice = 0; // variable used to store main menu choice
        final int STOP_APP = 7; //value from menu that is used to quit the application
        CustomerView view = new CustomerView(stmt);
        int customerID = 0; // setting variable to temporary customer info storage

        while (menuChoice != STOP_APP) {
            displayCustomerMenu(); //display the primary customer menu
            if (in.hasNextInt()) {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice) {
                    case 1:
                        view.printCustomers();
                        break;
                    case 2:
                        customerID = askUserToEnterCustomerID(view);
                        System.out.println(view.getCustomers().get(customerID-1));
                        break;
                    case 3:
                        addNewCustomer();
                        break;
                    case 4:
                        customerID = askUserToEnterCustomerID(view);
                        editCustomer(customerID); //You need to code this method below
                        break;
                    case 5:
                        customerID = askUserToEnterCustomerID(view);
                        deleteCustomer(customerID); //You need to code this method below
                        break;
                    case 9:
                        System.out.println("Program is closing...");
                        cleanup_resources();  // close the connection to the database when finished program
                        break;
                    default:
                        System.out.println("You entered an invalid choice, please try again...");
                }
            } else {
                //clear the input buffer and start again
                System.out.println("You entered an invalid choice, please try again...");
            }
        }
    }

    public static void addNewCustomer() {
        Scanner in = new Scanner(System.in);
        boolean isValid = false;

        while(!isValid) {
            String firstName = askUserToEnterName("first name");
            String lastName = askUserToEnterName("last name");
            int address1 = askUserToEnterAddress1();
            String address2 = askUserToEnterAddress2("address line 2");
            String town = askUserToEnterAddress2("town");
            System.out.println("**************SUCCESS for now*********************");
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
                    c.setAddress1(address1);
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
