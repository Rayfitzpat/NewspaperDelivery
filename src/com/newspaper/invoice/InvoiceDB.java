package com.newspaper.invoice;

import com.newspaper.db.DBconnection;
import com.newspaper.deliveryarea.DeliveryAreaDB;
import com.newspaper.deliveryarea.DeliveryAreaMain;

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

    public void displayAllInvoices(Statement stmt)
    {
        //1: Query the database for all areas
        //2: Display the result set in an appropriate manner
        String str = "Select * from invoice";

        try {
            ResultSet rs = stmt.executeQuery(str);
            System.out.printf("\n%-20s %-25s %-20s %-20s %-20s\n", "Invoice ID", "Customer ID", "Invoice Date", "Price", "Invoice Status");
            while (rs.next()) {
                int invoice_id = rs.getInt("invoice_id");
                int cus_id = rs.getInt("customer_id");
                String date = rs.getString("invoice_date");
                String price = rs.getString("price");
                String invoiceStatus = rs.getString("invoice_paid");

                System.out.printf("%-20s %-25s %-20s %-20s %-20s\n", invoice_id, cus_id, date, price, invoiceStatus);
            }
        } catch (SQLException sqle) {
            System.out.println("Error: failed to areas.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }

    }

    public void getCustomerFromInvoice(Statement stmt)
    {
        id = in.nextInt();
        String str = "Select * from invoice where customer_id = "+id;
        dav.displayAllCustomers(DBconnection.stmt);

        System.out.println();
        System.out.println("Please select the com.newspaper.customer.Customer ID to see their invoices.");

        if (id > 100 || id < 0)
        {
            System.out.println("ERROR"); // VALIDATION HERE
        }
        else

    {
        try {
            ResultSet rs = stmt.executeQuery(str);
            System.out.printf("\n%-20s %-25s %-20s %-20s %-20s\n", "com.newspaper.invoice.Invoice ID", "com.newspaper.customer.Customer ID", "com.newspaper.invoice.Invoice Date", "Price", "com.newspaper.invoice.Invoice Paid");
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
                System.out.printf("\n%-20s %-25s %-20s\n", "com.newspaper.customer.Customer ID","First Name", "Last Name");
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

    public void getCusAddressFromInvoiceId(Statement stmt)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the Invoice ID: ");
        int id = in.nextInt();

        if (id > 100 || id < 0) // VALIDATION HERE
        {
            System.out.println("ERROR");
        } else {

            String str = "Select address1, address2, town " +
                    "from customer, invoice " +
                    "where invoice.invoice_id = " + id + " AND invoice.customer_id = customer.customer_id;";
            try {
                ResultSet rs = stmt.executeQuery(str);
                while (rs.next()) {
                    String add1 = rs.getString("address1");
                    String add2 = rs.getString("address2");
                    String town = rs.getString("town");
                    System.out.printf("%-20s %-25s %-25s\n", add1, add2, town);
                }
            } catch (SQLException sqle) {
                System.out.println("Error: failed to get customer address.");
                System.out.println(sqle.getMessage());
                System.out.println(str);
            }
        }
    }

    public void deleteInovice(Statement stmt) throws SQLException
    {
        String str;
        ResultSet rs;
        Scanner in = new Scanner(System.in);
        InvoiceMain IM = new InvoiceMain();
        displayAllInvoices(stmt);
        System.out.println("Please enter the id of the invoice you would like to delete: ");
        int deleteId = in.nextInt();

        if (deleteId < 0)
        {
            System.out.println("Please Input a valid ID");
        }
        if (deleteId > 0)
        {
            str = "Select count(*) as total from invoice where invoice_id = " + deleteId;
            Statement deletePerson = DBconnection.con.createStatement();
            deletePerson.executeUpdate("delete from invoice where invoice_id ="+deleteId+"");
            System.out.println("Invoice with id: "+deleteId+" has been deleted.");
        }
    }

    public static void displayMainMenu()
    {
        System.out.println("\n Main Menu ");
        System.out.println("1: Get a customers com.newspaper.invoice.Invoice");
        System.out.println("2: Get com.newspaper.customer.Customer Name");
        System.out.println("3: Get com.newspaper.customer.Customer's Address from com.newspaper.invoice.Invoice.");
        System.out.println("4: Update Delivery Area Name");
        System.out.println("5: Delete a Delivery Area");
        System.out.println("6: Return to Main Menu");
    }
}
