package com.newspaper.order;

import com.newspaper.db.DBconnection;

import java.sql.ResultSet;
import java.sql.SQLException;


//all validation
public class Order {
    private int customer_id;
    private int publication_id;
    private int frequency;

    //constructor

    public Order(int customer_id, int publication_id, int frequency) throws OrderExceptionHandler {
        this.customer_id = customer_id;
        this.publication_id = publication_id;
        this.frequency = frequency;

        try {
            validateCustomerId(customer_id);
        }
        catch (OrderExceptionHandler e){
            throw e;
        }
    }

    public Order(){

    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


    public void validateCustomerId(int customer_id) throws OrderExceptionHandler {
        String query = "select count(*) as total from customer where customer_id = " + customer_id + ";";
        ResultSet rs;
        int count = 0;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if(count == 0)
            {
                throw new OrderExceptionHandler("com.newspaper.customer.Customer id does not exist");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }
    }
}