package com.newspaper.order;

import com.mysql.cj.xdevapi.Schema;
import com.newspaper.deliveryperson.DeliveryPerson;
import com.newspaper.customer.CustomerDB;
import com.newspaper.db.DBconnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderView {
    Order order = new Order();

    public static void main(String[] args) throws SQLException, OrderExceptionHandler {

        OrderView ov = new OrderView();
        DBconnection.init_db();
        ov.orderMenu();


//        ArrayList<Order> orders = ov.getOrders();
//        ov.printOrdersWithNames(orders);

        //ov.displayOrderByCustomerId();
    }

    public void orderMenu() throws SQLException, OrderExceptionHandler {

        Scanner in = new Scanner(System.in);

        int menuChoice = 0;

        final int STOP_APP = 7;

        while (menuChoice != STOP_APP) {
            displayMainOrderMenu(); //display the primary menu
            if (in.hasNextInt()) {
                //get the menu choice from the user
                menuChoice = in.nextInt();

                switch (menuChoice) {
                    case 1:
                        ArrayList<Order> orders = getOrders();
                        printOrdersWithNames(orders);
                        break;
                    case 2:
                        displayOrderByCustomerId();
                        break;
                    case 3:
                        //addNewOrder();
                        break;
                    case 4:
                        //editOrder();
                        break;
                    case 5:
                        //deleteOrder();
                        break;
                    case 6:
                        //return;
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

    public void displayMainOrderMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1: Display all Orders");
        System.out.println("2: Display an Order by customer ID ");
        System.out.println("3: Add new Order");
        System.out.println("4: Edit Order");
        System.out.println("5: Delete Order");
        System.out.println("6: Return to Main Menu\n");
        System.out.print("Enter your choice: ");
    }

    /**
     * Method that accesses the DB, gets all the order records and returns it in a form of
     * ArrayList of Order objects
     *
     * @return An ArrayList of Orders
     * @throws OrderExceptionHandler is thrown in case of error with DB or validation of the data
     */
    public ArrayList<Order> getOrders() throws OrderExceptionHandler {
        // array list for saving all the objects of the Order class
        ArrayList<Order> orders = new ArrayList<>();

        String query = "Select * from orders";
        ResultSet rs;
        try {
            Statement stmt = DBconnection.con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {

                int customer_id = rs.getInt("customer_id");
                int publication_id = rs.getInt("publication_id");
                int freq = rs.getInt("frequency");

                // creating an object of Order class, fill it with data from db
                Order order = new Order(customer_id, publication_id, freq);

                // add new object to array list
                orders.add(order);
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

            throw new OrderExceptionHandler("Error: failed to read all orders.");
        } catch (OrderExceptionHandler e) {
            throw e;
        }
        return orders;
    }

    public void printOrders(ArrayList<Order> orders) throws OrderExceptionHandler, SQLException {
        System.out.printf("\n%-30s %-10s %-35s\n", "Customer ID", "Publication ID", "Frequency");

        for (Order order : orders) {
            System.out.printf("%-30d %-10d %-35s\n", order.getCustomer_id(), order.getPublication_id(), order.getFrequency());
        }
    }

    public void printOrdersWithNames(ArrayList<Order> orders) throws OrderExceptionHandler, SQLException {
        System.out.printf("\n%-25s %-35s %-35s\n", "Customer ID", "Publication ID", "Frequency");

        for (Order order : orders) {
            String name = getCustomerName(order.getCustomer_id());
            String publication = getPublicationByID(order.getPublication_id());
            String frequency = convertFrequency(order.getFrequency());
            System.out.printf("%-25s %-35s %-35s\n", name, publication, frequency);
        }
    }

    public String getCustomerName(int customerID) {
        String name = "";

        String query = "SELECT first_name, last_name " +
                "FROM customer\n" +
                "WHERE customer_id = " + customerID + ";";
        ResultSet rs;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                name = fName + " " + lName;
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);
        }

        return name;
    }

    public String getPublicationByID(int publicationID) {
        String publicationName = "";

        String query = "SELECT publication_name " +
                "FROM publication\n" +
                "WHERE publication_id = " + publicationID + ";";
        ResultSet rs;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                publicationName = rs.getString("publication_name");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);
        }
        return publicationName;
    }

    public String convertFrequency(int frequency) throws SQLException {
        String day = "";

        String query = "Select frequency from orders;";

        ResultSet rs;

        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                //day = rs.getString("frequency");
                day = DayOfWeek.of(frequency).toString();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }
        return day;
    }
//******************************************************************************************************
// Beginning of display a certain order with entered ID.
//******************************************************************************************************

    public void displayOrderByCustomerId() throws SQLException {
        Scanner in = new Scanner(System.in);

        boolean isValid = false;

        while (!isValid) {
            System.out.println("Please enter the ID of the customer whose order you want to display");
            if (in.hasNextInt()) {


                String query = "";
                int id = 0;

                id = in.nextInt();

                //checks if the entered id is present in the db
                try {
                    // if id is not validated, the rest of the code won't execute
                    validateOrderCustomerId(id);
                    isValid = true;

                    //checks if the id entered is a valid ID in the list of publications, if it is, print out the associated data with that entry.
                    query = "Select * from orders where customer_id = " + id;

                    Statement stmt = DBconnection.con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    System.out.printf("\n%-20s %-25s %-15s\n", "Customer ID", "Publication ID", "Frequency");
                    while (rs.next()) {
                        int customer_id = rs.getInt("customer_id");
                        int publication_id = rs.getInt("publication_id");
                        int frequency = rs.getInt("frequency");

                        String day = DayOfWeek.of(frequency).toString();

                        System.out.printf("%-20s %-25s %-15s\n", getCustomerName(customer_id), getPublicationByID(publication_id), day);

                    }
                } catch (OrderExceptionHandler e) {
                    System.out.println(e.getMessage());
                }
            } else {
                in.nextLine();
                System.out.println("Input needs to be an integer");
                //displayOrderByCustomerId();
            }
        }
    }


//    public int askUserToEnterCustomerID(OrderView view) {
//        Scanner in = new Scanner(System.in);
//        boolean isValid = false;
//        int customerID = 0;
//
//        // getting id if the customer
//        while (!isValid)
//        {
//            System.out.println("Enter id of the customer: ");
//            if(in.hasNextInt())
//            {
//                customerID = in.nextInt();
//                // checking if student exists
//                if(view.ifCustomerExists(customerID))
//                {
//                    isValid = true;
//                }
//                else
//                {
//                    System.out.println("Customer with id " + customerID + " doesn`t exist");
//                }
//            }
//            else
//            {
//                in.next();
//                System.out.println("Customer id should be a number");
//            }
//        }
//        return customerID;
//    }
//
//    public boolean ifCustomerExists(int customerId) {
//
//        for (Customer c : order) {
//            if (c.getCustomerId() == customerId) {
//                return true;
//            }
//        }
//
//        // if return didn't happen in the foreach loop, then this is not duplicate
//        return false;
//    }

    /**
     * Methid is checking if customer_id is available in orders table
     *
     * @param customer_id customer id from the db
     * @throws OrderExceptionHandler thrown if order record with this customer_id is not in the db
     */
    public void validateOrderCustomerId(int customer_id) throws OrderExceptionHandler {
        String query = "select count(*) as total from orders where customer_id = " + customer_id + ";";
        ResultSet rs;
        int count = 0;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if (count == 0) {
                throw new OrderExceptionHandler("Customer does not have an order");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }
    }

}
