package com.newspaper.order;

import com.newspaper.db.DBconnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderView {
    static Order order = new Order();

    public static void main(String[] args) throws SQLException {
        DBconnection.init_db();
        orderMenu();
    }

    public static void orderMenu() throws SQLException {

    Scanner in = new Scanner(System.in);

    int menuChoice = 0;

    final int STOP_APP = 7;

    while(menuChoice !=STOP_APP)

    {
        displayMainOrderMenu(); //display the primary menu
        if (in.hasNextInt()) {
            //get the menu choice from the user
            menuChoice = in.nextInt();

            switch (menuChoice) {
                case 1:
                    displayAllOrders();
                    break;
                case 2:
                    displayOrderByCustomerId();
                    break;
                case 3:
                    //addNewOrder();
                    break;
                case 4:
                    // editOrder();
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
    public static void displayMainOrderMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1: Display all Orders");
        System.out.println("2: Display an Order by customer ID ");
        System.out.println("3: Add new Order");
        System.out.println("4: Edit Order");
        System.out.println("5: Delete Order");
        System.out.println("6: Return to Main Menu\n");
        System.out.print("Enter your choice: ");
    }

// ******************************************************************************************************
// Beginning of display all orders
// ******************************************************************************************************
//Prints out the publication table
    public static void displayAllOrders() {

        String str = "Select * from orders";

        try {
            ResultSet rs = DBconnection.stmt.executeQuery(str);

            System.out.printf("\n%-12s %-15s %-15s\n", "Customer ID", "Publication ID", "Frequency");
            while (rs.next())
            {
                int customer_id = rs.getInt("customer_id");
                int publication_id = rs.getInt("publication_id");
                String frequency = rs.getString("frequency");

                System.out.printf("%-12s %-15s %-15s\n", customer_id, publication_id, frequency);
            }

        } catch (SQLException sqle)
        {
            System.out.println("Error: failed to display all Orders.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }

    /**
     * Method that accesses the DB, gets all the order records and returns it in a form of
     * ArrayList of Order objects
     * @return An ArrayList of Orders
     * @throws OrderExceptionHandler is thrown in case of error with DB or validation of the data
     */
//    public ArrayList<Order> getOrders() throws OrderExceptionHandler {
//        // array list for saving all the objects of the Order class
//        ArrayList<Order> orders = new ArrayList<>();
//
//        String query = "Select * from orders";
//        ResultSet rs;
//        try {
//            Statement stmt = DBconnection.con.createStatement();
//            rs = stmt.executeQuery(query);
//            while (rs.next()) {
//
//                int customer_id = rs.getInt("customer_id");
//                int publication_id = rs.getInt("publication_id");
//                int freq = rs.getInt("frequency");
//
//                orders.add(new Order(customer_id, publication_id, freq));
//            }
//
//        } catch (SQLException sqle) {
//            System.out.println(sqle.getMessage());
//            System.out.println(query);
//
//            throw new OrderExceptionHandler("Error: failed to read all orders.");
//        } catch (OrderExceptionHandler e) {
//            throw e;
//        }
//
//        return orders;
//    }

// ******************************************************************************************************
// Beginning of display a certain publication with entered ID.
// ******************************************************************************************************

    public static void displayOrderByCustomerId() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the ID of the customer whose order you want to display");

        String st="";
        int count;

        String id = in.next();

        //checks if the entered id is a whole number.
        boolean validId = order.validateAWholeNumber(id);
        if(validId) {

            st = "select count(*) as total from orders where customer_id = " + id;
            ResultSet rss = DBconnection.stmt.executeQuery(st);
            count = 0;
            while (rss.next()) {
                count = rss.getInt("total");
            }
            if(count > 0)
            {
                //checks if the id entered is a valid ID in the list of publications, if it is, print out the associated data with that entry.
                String str = "Select * from orders where customer_id = " + id;

                try
                {
                    ResultSet rs = DBconnection.stmt.executeQuery(str);

                    System.out.printf("\n%-12s %-15s %-15s\n", "Customer ID", "Publication ID", "Frequency");
                    while (rs.next())
                    {
                        int customer_id = rs.getInt("customer_id");
                        int publication_id = rs.getInt("publication_id");
                        String frequency = rs.getString("frequency");

                        System.out.printf("%-12s %-15s %-15s\n", customer_id, publication_id, frequency);
                    }

                }
                catch (SQLException sqle)
                {
                    System.out.println("Error: failed to display Order(s).");
                    System.out.println(str);
                }
            }
            else
            {   //if the valid ID check fails, the below is printed.
                System.out.println("Customer does not exist\n");
                displayOrderByCustomerId();
            }
        }
        else
        {
            System.out.println("You have entered an invalid ID\n");
            displayOrderByCustomerId();
        }
    }
}
