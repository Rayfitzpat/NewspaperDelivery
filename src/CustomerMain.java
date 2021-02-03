import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerMain {

    // connection with the database
    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);

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


    public static void main(String[] args) {
        init_db();  // open the connection to the database

        CustomerView view = new CustomerView();

        // testing read
        ArrayList<Customer> publicationsList =  view.getAllCustomers(stmt);
        view.printCustomers(publicationsList);

        // testing insert/create
        try {
            Customer customer = new Customer("Jack", "Martin", 23, "Dublin Road", "Athlone","M35UJ99","084 831 6481", null, null,  true, 6);
            view.insertCustomer(customer, con);
        }
        catch (CustomerExceptionHandler e) {
            System.out.println(e.getMessage());
        }

        // testing read
        publicationsList =  view.getAllCustomers(stmt);
        view.printCustomers(publicationsList);


    }
}
