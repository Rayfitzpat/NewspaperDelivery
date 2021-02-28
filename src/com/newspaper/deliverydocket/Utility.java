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

    /**
     * Method returns the day of the week (1-7) for the cpecisied date
     * @param date The date from which we are extracting the day of the week
     * @return A value in range from 1 to 7 representing days from Monday to Sunday
     */
    public int getDayNumber(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }

}
