package com.newspaper.deliverydocket;

import com.newspaper.db.DBconnection;

import java.sql.ResultSet;
import java.sql.SQLException;

// this class will represent invoice delivery item in the delivery docket
public class InvoiceDeliveryItem extends DeliveryItem {

    private int invoiceId;

    public InvoiceDeliveryItem(int invoiceId, int customerID, String customerName, String customerAddress, boolean isDelivered) throws DeliveryDocketExceptionHandler {
        super(customerID, customerName, customerAddress, "invoice", isDelivered);

        this.invoiceId = invoiceId;
        try {
            validateInvoiceId(invoiceId);
        }
        catch(DeliveryDocketExceptionHandler e) {
            throw e;
        }

        this.invoiceId = invoiceId;
    }

    public InvoiceDeliveryItem() {

    }

    public void validateInvoiceId(int invoiceId) throws DeliveryDocketExceptionHandler{
        String query = "select count(*) as total from invoice where invoice_id = " + invoiceId + ";";
        ResultSet rs;
        int count = - 1;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if(count == 0)
            {
                throw new DeliveryDocketExceptionHandler("Invoice with id " + invoiceId + " does not exist");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }
    }

    @Override
    public void print() {
        System.out.printf("\ninv: %-10d cus: %-10s %-20s %-35s %-10s %-10s", invoiceId, this.getCustomerID(), this.getCustomerName(), this.getCustomerAddress(), this.isDelivered(), "invoice");
    }

}
