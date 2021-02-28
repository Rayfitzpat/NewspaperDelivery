package com.newspaper.order;

import com.newspaper.db.DBconnection;

import java.sql.ResultSet;
import java.sql.SQLException;


//all validation
public class Order {
    private String customer_id;
    private String publication_id;
    private String frequency;

    //constructor

    public Order(String customer_id, String publication_id, String frequency) throws OrderExceptionHandler {
        this.customer_id = customer_id;
        this.publication_id = publication_id;
        this.frequency = frequency;

        try {
            validateCustomerId(customer_id);
        } catch (OrderExceptionHandler e) {
            throw e;
        }
    }

    public Order() {

    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(String publication_id) {
        this.publication_id = publication_id;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }


    public void validateCustomerId(String customer_id) throws OrderExceptionHandler {
        String query = "select count(*) as total from customer where customer_id = " + customer_id + ";";
        ResultSet rs;
        int count = 0;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if (count == 0) {
                throw new OrderExceptionHandler("Customer id does not exist");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }
    }

    public void validatePublicationId(String publication_id) throws OrderExceptionHandler {
        String query = "select count(*) as total from publication where publication_id = " + publication_id + ";";
        ResultSet rs;
        int count = 0;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if (count == 0) {
                throw new OrderExceptionHandler("Publication id does not exist");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }
    }

    public boolean validateAWholeNumber(String customer_id) {
        if (customer_id.matches("[1-9][0-9]*")) {

            return true;


        } else {
            return false;
        }

    }

    // *****************************************************************************************
// Validates that the string entered only consists of the words "Daily" or "Weekly"
// *****************************************************************************************
    public void validatePublicationFrequency(String frequency) throws OrderExceptionHandler
    {
        //uses regex to check if the entered name is between a-z
        try {
            if (frequency.matches("[a-zA-Z]+"))
            {
                //checks if the users entered either "Daily" or "Weekly" or "Monthly"

                if (frequency.matches("daily") || frequency.matches("weekly") || frequency.matches("monthly") || frequency.matches("Weekly") || frequency.matches("Daily") || frequency.matches("Monthly"))
                {
                    frequency = frequency.toLowerCase();
                }
                else
                {
                    throw new OrderExceptionHandler("Please only enter the words, 'Daily', 'Weekly' or 'Monthly'");
                }
            }


            }
            catch(Exception sqle) {
                System.out.println(sqle.getMessage());
            }
    }
}