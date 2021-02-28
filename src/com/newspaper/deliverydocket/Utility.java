package com.newspaper.deliverydocket;

import com.newspaper.db.DBconnection;

import java.sql.ResultSet;
import java.sql.SQLException;

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


}
