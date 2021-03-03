package com.newspaper.deliverydocket;

import com.newspaper.db.DBconnection;
import com.newspaper.order.Order;
import com.newspaper.order.OrderExceptionHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class Utility {

    public String getPublicationByID(int id) throws DeliveryDocketExceptionHandler {
        String publicationName = "";
        if (publicationExists(id)) {
            String query = "SELECT publication_name " +
                    "FROM publication\n" +
                    "WHERE publication_id = " + id + ";";
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
        }

        return publicationName;

    }

    public boolean deliveryPersonExists(int deliveryPersonId) throws DeliveryDocketExceptionHandler {

        // set the flag
        boolean exists = false;

        String query = "select count(*) as total from delivery_person where delivery_person_id = " + deliveryPersonId + ";";
        ResultSet rs;
        int count = - 1;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
                exists = true;
            }
            if(count == 0)
            {
                throw new DeliveryDocketExceptionHandler("Delivery Person with id " + deliveryPersonId + " does not exist");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }

        return exists;
    }


    public boolean deliveryPersonActive(int deliveryPersonId) throws DeliveryDocketExceptionHandler {

        // set the flag
        boolean exists = false;

        String query = "select count(*) as total from delivery_person where delivery_person_id = " + deliveryPersonId + "" +
                " and delivery_status = 'true';";
        ResultSet rs;
        int count = - 1;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
                exists = true;
            }
            if(count == 0)
            {
                throw new DeliveryDocketExceptionHandler("Delivery Person with id " + deliveryPersonId + " is not active.");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }

        return exists;
    }



    /**
     * Method checks if publication exists in the DB by ID
     * @param publicationId The Id of publication that has to be checked
     * @return true if it exists, false if not
     * @throws DeliveryDocketExceptionHandler throwm in case of error
     */
    public boolean publicationExists(int publicationId) throws DeliveryDocketExceptionHandler {

        // set the flag
        boolean exists = false;

        String query = "select count(*) as total from publication where publication_id = " + publicationId + ";";
        ResultSet rs;
        int count = - 1;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
                exists = true;
            }
            if(count == 0)
            {
                throw new DeliveryDocketExceptionHandler("Publication with id " + publicationId + " does not exist");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }

        return exists;
    }

    /**
     * Method that accesses the DB, gets all the order records and returns it in a form of
     * ArrayList of Order objects
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

                orders.add(new Order(customer_id, publication_id, freq));
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

    public Order getOrder(int customerId, int publicationId) throws OrderExceptionHandler {
        // array list for saving all the objects of the Order class
        Order order = new Order();

        String query = "Select * from orders where customer_id = " + customerId + "" +
                " and publication_id = " + publicationId + ";";
        ResultSet rs;
        try {
            Statement stmt = DBconnection.con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {

                int customer_id = rs.getInt("customer_id");
                int publication_id = rs.getInt("publication_id");
                int freq = rs.getInt("frequency");

                order = new Order(customer_id, publication_id, freq);
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

            throw new OrderExceptionHandler("Error: failed to read orders.");
        } catch (OrderExceptionHandler e) {
            throw e;
        }
        return order;
    }

    /**
     * Method returns the day of the week (1-7) for the cpecisied date
     * @param date The date from which we are extracting the day of the week
     * @return A value in range from 1 to 7 representing days from Monday to Sunday
     */
    public int getDayNumber(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }

    /**
     * Method is displaying a table of "Delivery Person id " - "Delivery Person name" - " Delivery Area"
     */
    public void displayDeliperyPeopleWithDeliveryAreas() {

        String str = "SELECt delivery_person.delivery_person_id, delivery_person.first_name, delivery_person.last_name,  delivery_area.name\n" +
                "FROM delivery_person, delivery_area\n" +
                "WHERE delivery_person.delivery_person_id = delivery_area.delivery_person_id\n" +
                "ORDER BY delivery_person.delivery_person_id;";

        try {
            Statement stmt = DBconnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            // Sets the headings and returns the data from the DB
            System.out.printf("\n%-10s %-15s %-15s %-20s \n", "DP ID", "First Name", "Last Name", "Delivery Area");
            System.out.println("-----------------------------------------------------------------------------------");
            while (rs.next()) {
                int delivery_person_id = rs.getInt("delivery_person_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String daName = rs.getString("name");

                System.out.printf("%-10s %-15s %-15s %-20s \n", delivery_person_id, first_name, last_name, daName);
            }

        } catch (SQLException sqle) {
            System.out.println("Error: failed to display all Delivery People.");
            System.out.println(sqle.getMessage());
            System.out.println(str);
        }
    }

    /**
     * Method gets all the months in the Delivery table
     * @return An arraylist of months
     */
    public ArrayList<Integer> getMonths() {
        ArrayList<Integer> months = new ArrayList<>();

        // query is getting all unique months from the db
        String query = "SELECT DISTINCT MONTH(delivery_date) as 'month'\n" +
                "FROM delivery;";
        try {
            Statement stmt = DBconnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int month = rs.getInt("month");
                months.add(month);
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);
        }

        return months;
    }

}
