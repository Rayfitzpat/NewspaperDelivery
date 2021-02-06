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

    }

    public static void deleteCustomer(int customerId) {

    }

    public static void editCustomer(int customerId ) {

    }

    public static int askUserToEnterCustomerID(CustomerView view) {
        Scanner in = new Scanner(System.in);
        boolean isValid = false;
        int customerID = 0;

        // geting id if the customer
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
