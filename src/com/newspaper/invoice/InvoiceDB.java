package com.newspaper.invoice;

import com.newspaper.db.DBconnection;
import com.newspaper.deliveryarea.DeliveryAreaDB;
import com.newspaper.deliveryarea.DeliveryAreaMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InvoiceDB {
    DeliveryAreaDB dav = new DeliveryAreaDB();
    Scanner in = new Scanner(System.in);
    int id;
    Invoice invoice = new Invoice();


    public void generateInvoice() {

    }

    public void displayAllInvoices(Statement stmt) {
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

    public void getCustomerFromInvoice(Statement stmt) {
        id = in.nextInt();
        String str = "Select * from invoice where customer_id = " + id;
        dav.displayAllCustomers(DBconnection.stmt);

        System.out.println();
        System.out.println("Please select the Customer ID to see their invoices.");

        if (id > 100 || id < 0) {
            System.out.println("ERROR"); // VALIDATION HERE
        } else {
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

    public void getCustomerNameFromId(Statement stmt) {
        System.out.println("Please enter the customer ID: ");
        id = in.nextInt();
        String str = "Select * from customer where customer_id = " + id;

        if (id > 100 || id < 0) // VALIDATION HERE
        {
            System.out.println("ERROR");
        } else {
            try {
                ResultSet rs = stmt.executeQuery(str);
                System.out.printf("\n%-20s %-25s %-20s\n", "Customer ID", "First Name", "Last Name");
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

    public void getCusAddressFromInvoiceId(Statement stmt) {
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

    public void deleteInvoice(Statement stmt) throws SQLException {
        String str;
        ResultSet rs;
        Scanner in = new Scanner(System.in);
        displayAllInvoices(stmt);
        System.out.println("Please enter the id of the invoice you would like to delete: ");
        int deleteId = in.nextInt();

        if (deleteId < 0) {
            System.out.println("Please Input a valid ID");
        }
        if (deleteId > 0) {
            str = "Select count(*) as total from invoice where invoice_id = " + deleteId;
            Statement deletePerson = DBconnection.con.createStatement();
            deletePerson.executeUpdate("delete from invoice where invoice_id =" + deleteId + "");
            System.out.println("Invoice with id: " + deleteId + " has been deleted.");
        }
    }

    public void printPublications(Statement stmt) throws SQLException
    {
        int cusid;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter customer ID to check for publication.");
        cusid = in.nextInt();
        String str1 = "SELECT publication.publication_name, publication.publication_cost, delivery.delivery_date, customer.first_name, customer.last_name\n" +
                "FROM delivery, customer, publication\n" +
                "WHERE delivery.customer_id = customer.customer_id\n" +
                "\tAND delivery.publication_id = publication.publication_id\n" +
                "\tAND customer.customer_id =" + cusid + "\n" +
                "\tAND MONTH(delivery.delivery_date) = " + cusid;

        if (cusid > 100 || cusid < 0) {
            System.out.println("There was an error");
        } else {
            Statement printPublication = DBconnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(str1);
            while (rs.next()) {
                String pubName = rs.getString("publication_name");
                String pubCost = rs.getString("publication_cost");
                String deliveryDate = rs.getString("delivery_date");
                String firstname = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                System.out.printf("%-25s %-25s %-25s %-25s %-25s\n", pubName, pubCost, deliveryDate, firstname, lastName);

            }

        }
    }

    // BII6 VIEW THE INVOICE (INVOICE NUMBER, CUSTOMER NAME, CUSTOMER ADDRESS, LIST OF PUBLICATIONS)
    public void displayInvoiceID(Statement stmt) throws SQLException
    {
        int cusid;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter customer ID to find Invoce ID.");
        cusid = in.nextInt();
        String str1 = "SELECT customer.customer_id, invoice.invoice_id\n" +
                "FROM invoice, customer\n" +
                "WHERE invoice.customer_id = customer.customer_id\n" +
                "\tAND customer.customer_id =" + cusid;

        if (cusid > 100 || cusid < 0) {
            System.out.println("There was an error");
        } else {
            Statement printInvoiceID = DBconnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(str1);
            while (rs.next()) {
                String cusnum = rs.getString("customer_id");
                String invoicenum = rs.getString("invoice_id");
                System.out.printf("%-25s %-25s\n", cusnum, invoicenum);
            }

        }
    }


        public static void displayMainMenu ()
        {
            System.out.println("\n Main Menu ");
            System.out.println("1: Get a Customer from Invoice");
            System.out.println("2: Get Customer Name from Invoice ID");
            System.out.println("3: Get Customer Address from Invoice.");
            System.out.println("4: Get Customer Subscriptions from Customer ID");
            System.out.println("5: Delete a Delivery Area");
            System.out.println("6: Return to Main Menu");
        }

        public static void invoiceView()
        {
            System.out.println("\n Invoice View Menu");
            System.out.println("1. View Invoice Number");
            System.out.println("2. View Customer Name");
            System.out.println("3. View Customer Address");
            System.out.println("4. View the Publications");
        }

        public static void displayUpdateMenu ()
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

