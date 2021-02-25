import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InvoiceDB
{
    DeliveryAreaDB dav = new DeliveryAreaDB();
    Scanner in = new Scanner(System.in);
    int id;
    Invoice invoice = new Invoice();



    public void generateInvoice()
    {

    }

    public void getCustomerFromInvoice(Statement stmt)
    {
        id = in.nextInt();
        String str = "Select * from invoice where customer_id = "+id;
        dav.displayAllCustomers(DBconnection.stmt);

        System.out.println();
        System.out.println("Please select the Customer ID to see their invoices.");

        if (id > 100 || id < 0)
        {
            System.out.println("ERROR"); // VALIDATION HERE
        }
        else

    {
        try {
            ResultSet rs = stmt.executeQuery(str);
            System.out.printf("\n%-20s %-25s %-20s %-20s %-20s\n", "Invoice ID", "Customer ID", "Invoice Date", "Price", "Invoice Paid");
            while (rs.next()) {
                int invoice_id = rs.getInt("invoice_id");
                int customer_id = rs.getInt("customer_id");
                String invoice_date = rs.getString("invoice_date");
                String price = rs.getString("price");
                String invoice_paid = rs.getString("invoice_paid");
                System.out.printf("%-20s %-25s %-20s %-20s %-20s\n", invoice_id, customer_id, invoice_date, price, invoice_paid);
            }
        } catch (SQLException sqle) {
            System.out.println("Error: failed to areas.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }
    }

    public void getCustomerNameFromId(Statement stmt)
    {
        System.out.println("Please enter the customer ID: ");
        id = in.nextInt();
        String str = "Select * from customer where customer_id = "+id;

        if (id > 100 || id < 0) // VALIDATION HERE
        {
            System.out.println("ERROR");
        }
        else
        {
            try {
                ResultSet rs = stmt.executeQuery(str);
                System.out.printf("\n%-20s %-25s %-20s\n", "Customer ID","First Name", "Last Name");
                while (rs.next()) {
                    int customer_id = rs.getInt("customer_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    System.out.printf("%-20s %-25s %-20s\n", customer_id, firstName, lastName);
                }
            } catch (SQLException sqle) {
                System.out.println("Error: failed to areas.");
                System.out.println(sqle.getMessage());
                System.out.println(str);
            }
        }
    }

    public void getCusAddressFromInvoiceId(Statement stmt) //TODO ASK ABOUT HOW TO GET CUSTOMER ID OUT OF INVOICE ID
    {

        System.out.println("Please enter the Invoice ID: ");
        id = in.nextInt();
        String str = "Select * from invoice where invoice_id = " +id;

        if (id > 100 || id < 0) // VALIDATION HERE
        {
            System.out.println("ERROR");
        }
        else
        {
            try {

                ResultSet rs = stmt.executeQuery(str);
                while (rs.next())
                {
                    int customer_id = rs.getInt("customer_id");
                    String str2 = "Select * from customer where customer_id = "+customer_id;
                    try
                    {
                        ResultSet rss = stmt.executeQuery(str2);
                        String add1 = rss.getString("address1");
                        String add2 = rss.getString("address2");

                        System.out.printf("%-20s %-25s \n", add1, add2);
                    }
                    catch (SQLException sqle)
                    {
                        System.out.println("Error: failed to get customer address.");
                        System.out.println(sqle.getMessage());
                        System.out.println(str);
                    }
                }
            } catch (SQLException sqle) {
                System.out.println("Error: failed to get customer id.");
                System.out.println(sqle.getMessage());
                System.out.println(str);
            }
        }
    }

    public static void displayMainMenu()
    {
        System.out.println("\n Main Menu ");
        System.out.println("1: Get a customers Invoice");
        System.out.println("2: Get Customer Name");
        System.out.println("3: Get Customer's Address from Invoice.");
        System.out.println("4: Update Delivery Area Name");
        System.out.println("5: Delete a Delivery Area");
        System.out.println("6: Return to Main Menu");
    }
}
