package com.newspaper.deliverydocket;

import com.newspaper.db.DBconnection;
import com.newspaper.deliverydocket.DeliveryDocketExceptionHandler;
import com.newspaper.deliverydocket.DeliveryItem;

import java.sql.ResultSet;
import java.sql.SQLException;

// this class will represent publication delivery item in the delivery docket
public class PublicationDeliveryItem extends DeliveryItem {

    private int publicationId;
    public PublicationDeliveryItem(int publicationId, int customerID, String customerName, String customerAddress, boolean isDelivered) throws DeliveryDocketExceptionHandler {
        super(customerID, customerName, customerAddress, "publication", isDelivered);

        try {
            validatePublicationId(publicationId);
        }
        catch (DeliveryDocketExceptionHandler e) {
            throw e;
        }
    }

    public PublicationDeliveryItem() {

    }

    public void validatePublicationId(int publicationId) throws DeliveryDocketExceptionHandler
    {
        String query = "select count(*) as total from publication where publication_id = " + publicationId + ";";
        ResultSet rs;
        int count = - 1;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if(count == 0)
            {
                throw new DeliveryDocketExceptionHandler("com.newspaper.publication.Publication with id " + publicationId + " does not exist");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }
    }
}