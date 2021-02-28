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
        int minLength = 5;
        int maxLength = 52;

        if (name ==  null) {
            throw new DeliveryDocketExceptionHandler("Delivery Person name cannot be null");
        }
        else if(name.isBlank() || name.isEmpty()){
            throw new DeliveryDocketExceptionHandler("Delivery Person name cannot be empty");
        }
        else if (name.length() < minLength) {
            throw new DeliveryDocketExceptionHandler("Delivery Person name does not meet the minimum length requirements");
        }
        else if (name.length() > maxLength) {
            throw new DeliveryDocketExceptionHandler("Delivery Person name exceeds the maximum length requirements");
        }
        else {
            // checking if line has any numbers
            char[] charArray = name.toCharArray();
            for (char c : charArray) {
                if (Character.isDigit(c)) {
                    throw new DeliveryDocketExceptionHandler("Delivery Person name cannot consist of numbers");
                }
            }
        }
    }

    public void validateDeliveryAreaName(String deliveryAreaName) throws DeliveryDocketExceptionHandler{
        int minLength = 2;
        int maxLength = 19;

        if (deliveryAreaName ==  null) {
            throw new DeliveryDocketExceptionHandler("Delivery area name cannot be null");
        }
        else if(deliveryAreaName.isBlank() || deliveryAreaName.isEmpty()){
            throw new DeliveryDocketExceptionHandler("Delivery area name cannot be empty");
        }
        else if (deliveryAreaName.length() < minLength) {
            throw new DeliveryDocketExceptionHandler("Delivery area name does not meet the minimum length requirements");
        }
        else if (deliveryAreaName.length() > maxLength) {
            throw new DeliveryDocketExceptionHandler("Delivery area name exceeds the maximum length requirements");
        }

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

    public boolean hasPublicationItems(ArrayList<DeliveryItem> items) {
        for(DeliveryItem d : items) {
            if (d.getType().equals("publication")){
                return true;
            }
        }
        return false;
    }

    public boolean hasInvoiceItems(ArrayList<DeliveryItem> items) {
        for(DeliveryItem d : items) {
            if (d.getType().equals("invoice")){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        // utility class has some helper methods, here used for getting name of the publication by id
        Utility utility = new Utility();
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\n****************************************************************************************");
            sb.append("\n************************************DELIVERY DOCKET*************************************");
            sb.append("\n****************************************************************************************");
            sb.append( String.format("\n%-25s %-25s", "DATE: " , getDate()));
            sb.append( String.format("\n%-25s %-25s", "Delivery Area No: ", getDeliveryAreaId() + ", " + getDeliveryAreaName()));
            sb.append(String.format("\n%-25s %-25s", "Delivery Person Name: " , getDeliveryPersonName()));

            // check if there are any publications to be delivered
            if (hasPublicationItems(deliveryItems)) {
                sb.append("\n\n\n*********************************** PUBLICATIONS *****************************************");
                sb.append(String.format("\n %-25s %-20s %-27s %-20s","Customer Address", "Customer Name", "Publication", "Is Delivered"));
                sb.append("\n------------------------------------------------------------------------------------------");
                for (DeliveryItem delivery : this.deliveryItems) {
                    if (delivery.getType().equals("publication")) {
                        sb.append(String.format("\n %-25s %-20s %-27s %-20s ", delivery.getCustomerAddress(), delivery.getCustomerName(),utility.getPublicationByID(delivery.getId()), delivery.isDelivered()));
                    }
                }
            }
            else {
                sb.append("\n\nNo publication delivery for today.");
            }

            // check if there is any invoices
            if (hasInvoiceItems(deliveryItems)) {
                // if there is, print the items in separate table
                sb.append("\n\n\n*************************************** INVOICES **************************************");
                sb.append(String.format("\n %-10s %-25s %-20s %-20s ", "ID","Customer Address", "Customer Name", "Is Delivered"));
                sb.append("\n---------------------------------------------------------------------------------------");
                for (DeliveryItem delivery : this.deliveryItems) {
                    if (delivery.getType().equals("invoice")) {
                        sb.append(String.format("\n %-10d %-25s %-20s %-20s", delivery.getId(), delivery.getCustomerAddress(), delivery.getCustomerName(), delivery.isDelivered()));
                    }
                }
            }
        }
        catch (DeliveryDocketExceptionHandler e) {
            System.out.println("Exception in DeliveryDocket toString() method: ");
            System.out.println(e.getMessage());
        }

        return sb.toString();
    }
}
