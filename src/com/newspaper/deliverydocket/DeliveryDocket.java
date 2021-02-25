package com.newspaper.deliverydocket;

import com.newspaper.db.DBconnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DeliveryDocket {
    private ArrayList<DeliveryItem> deliveryItems;
    private String date;
    private int deliveryAreaId;
    private String deliveryAreaName;
    private String deliveryPersonName;

    public DeliveryDocket(ArrayList<DeliveryItem> deliveryItems, String date, int deliveryAreaId, String deliveryAreaName, String deliveryPersonName) throws DeliveryDocketExceptionHandler {

        try {
            validateDate(date);
            validateDeliveryAreaID(deliveryAreaId);
            validateDeliveryAreaName(deliveryAreaName);
            validateDeliveryPersonName(deliveryPersonName);
        }
        catch (DeliveryDocketExceptionHandler e) {
            throw e;
        }

        this.deliveryItems = deliveryItems;
        this.date = date;
        this.deliveryAreaId = deliveryAreaId;
        this.deliveryAreaName = deliveryAreaName;
        this.deliveryPersonName = deliveryPersonName;
    }

    public DeliveryDocket() {

    }

    public void validateDeliveryPersonName(String name) throws DeliveryDocketExceptionHandler{
    }

    public void validateDeliveryAreaName(String deliveryAreaName) throws DeliveryDocketExceptionHandler{
    }

    public void validateDeliveryAreaID(int deliveryAreaId) throws DeliveryDocketExceptionHandler {
        String query = "select count(*) as total from delivery_area where delivery_area_id = " + deliveryAreaId + ";";
        ResultSet rs;
        int count = - 1;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if(count == 0)
            {
                throw new DeliveryDocketExceptionHandler("Delivery area with id " + deliveryAreaId + " does not exist");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }
    }

    public void validateDate(String date) throws DeliveryDocketExceptionHandler{
        // check for null values
        if (date == null) {
            throw new DeliveryDocketExceptionHandler("Delivery docket date cannot be null");
        }
        else {
            // setting the format for date 2021-02-29
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            // null is acceptable for holiday
            if (date != null) {
                // checking the format of start date
                try {
                    Date start = format.parse(date);

                }
                catch (ParseException e)
                {
                    throw new DeliveryDocketExceptionHandler("Date format is incorrect");
                }
            }
        }
    }


    public ArrayList<DeliveryItem> getDeliveryItems() {
        return deliveryItems;
    }

    public void setDeliveryItems(ArrayList<DeliveryItem> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDeliveryAreaId() {
        return deliveryAreaId;
    }

    public void setDeliveryAreaId(int deliveryAreaId) {
        this.deliveryAreaId = deliveryAreaId;
    }

    public String getDeliveryAreaName() {
        return deliveryAreaName;
    }

    public void setDeliveryAreaName(String deliveryAreaName) {
        this.deliveryAreaName = deliveryAreaName;
    }

    public String getDeliveryPersonName() {
        return deliveryPersonName;
    }

    public void setDeliveryPersonName(String deliveryPersonName) {
        this.deliveryPersonName = deliveryPersonName;
    }
}