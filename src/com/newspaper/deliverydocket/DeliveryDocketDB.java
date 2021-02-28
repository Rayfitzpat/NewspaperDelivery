package com.newspaper.deliverydocket;

import com.newspaper.db.DBconnection;
import com.newspaper.deliveryarea.DeliveryArea;
import com.newspaper.order.Order;
import com.newspaper.order.OrderExceptionHandler;

import java.io.File;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class DeliveryDocketDB {

    public static void main(String[] args) throws DeliveryDocketExceptionHandler {
        DBconnection.init_db();
        DeliveryDocketDB deliveryDocketDB = new DeliveryDocketDB();
        Utility ut = new Utility();
//        ArrayList <PublicationDeliveryItem> deliveries = deliveryDocketDB.getAllDeliveryItemsForDeliveryArea(3, "2021-03-07");
//        System.out.println(deliveries.size());
//        for (PublicationDeliveryItem p : deliveries){
//            p.print();
//        }


//        ArrayList <InvoiceDeliveryItem> deliveries = deliveryDocketDB.getInvoicesForDeliveryArea(3, 12);
//        System.out.println(deliveries.size());
//        for (InvoiceDeliveryItem p : deliveries){
//            p.print();
//        }


//        ArrayList <DeliveryItem> deliveries = deliveryDocketDB.getAllDeliveryItemsFor(4, "2021-03-07");
//        System.out.println(deliveries.size());
//        for (DeliveryItem p : deliveries){
//            p.print();
//        }


//        DeliveryDocket docket = deliveryDocketDB.createDeliveryDocketFor(4, "2021-02-01");
//        System.out.println(docket);
//        System.out.println("Saving...");
//        deliveryDocketDB.createDeliveryDocketFile(docket);

        ArrayList <Delivery> deliveries = deliveryDocketDB.generateDeliveriesForMonth(9);
        System.out.println(deliveries.size());
        for (Delivery p : deliveries){
            p.print();
        }
    }

    // create delivery docket
    public DeliveryDocket createDeliveryDocketFor(int deliveryPersonId, String date) throws DeliveryDocketExceptionHandler {

        // get the delivery area id where the delivery person is working
        DeliveryArea area = getDeliveryArea(deliveryPersonId);

        // get all deliveries for delivery docket
        ArrayList<DeliveryItem> deliveries = getAllDeliveryItemsFor(area.getId(), date);

        String deliveryPersonName = getDeliveryPersonName(deliveryPersonId);

        // create empty delivery docket object
        DeliveryDocket docket = new DeliveryDocket(deliveries, date, area.getId(), area.getDAreaName(), deliveryPersonName);
        return docket;
    }

    public void createDeliveryDocketFile(DeliveryDocket docket) {

        // create delivery docket text file
        File docketFile = new File(docket.getDeliveryAreaName() + "_delivery_docket_" + docket.getDate() + ".txt");

        try {
            PrintWriter pw = new PrintWriter(docketFile);
            pw.print(docket);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // create delivery docket for delivery person
    public ArrayList getAllDeliveryItemsFor(int deliveryAreaId, String date) throws DeliveryDocketExceptionHandler {

        // first convert the date into Date object
        LocalDate currDate = convertDate(date);
        // create lists of delivery items
        ArrayList<DeliveryItem> deliveries = new ArrayList<>();

        // check if date is not null
        if (currDate != null) {

            // check if its the beginning of the month
            if (currDate.getDayOfMonth() == 1) {
                // delivery docket should include invoices
                int prevMonth = currDate.getMonthValue() - 1;
                ArrayList<InvoiceDeliveryItem> invoices = getInvoicesForDeliveryArea(deliveryAreaId, prevMonth);
                deliveries.addAll(invoices);
            }

            // add all publications
            ArrayList<PublicationDeliveryItem> publications = getPublicationDeliveriesForDeliveryArea(deliveryAreaId, date);
            deliveries.addAll(publications);
        }
        return deliveries;
    }


    // get all invoices for delivery docket
    public ArrayList getInvoicesForDeliveryArea(int deliveryAreaId, int month) throws DeliveryDocketExceptionHandler {
        ArrayList<InvoiceDeliveryItem> invoices = new ArrayList<>();

        String query = "SELECT invoice.invoice_id,\n" +
                "\t\tcustomer.customer_id,   \n" +
                "\t\tcustomer.first_name, \n" +
                "\t\tcustomer.last_name, \n" +
                "\t\tcustomer.address1,\n" +
                "\t\tcustomer.address2, \n" +
                "\t\tcustomer.town, \n" +
                "\t\tinvoice.invoice_date,  \n" +
                "\t\tinvoice.is_delivered,\n" +
                "\t\tdelivery_area.name\n" +
                "FROM invoice, customer, delivery_area\n" +
                "WHERE invoice.customer_id = customer.customer_id\n" +
                "\tAND customer.delivery_area_id = delivery_area.delivery_area_id\n" +
                "\tAND MONTH(invoice.invoice_date) = " + month + "\n" +
                "\tAND  delivery_area.delivery_area_id = " + deliveryAreaId + ";";
        ResultSet rs;
        try {
            PreparedStatement stmt = DBconnection.con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {

                int invoiceId = rs.getInt("invoice.invoice_id");
                int customerId = rs.getInt("customer.customer_id");
                String customerName = rs.getString("customer.first_name") + " " + rs.getString("customer.last_name");
                String customerAddress = rs.getInt("customer.address1") + " " + rs.getString("customer.address2") + ", " + rs.getString("customer.town");
                String deliveryStatus = rs.getString("invoice.is_delivered");

                // in database we have "delivered or nor delivered" values for delivery status.
                // Here we need to convert it into true or false for easier manipulation with the data
                boolean isDelivered = false;
                if (deliveryStatus.equals("true")) {
                    isDelivered = true;
                }

                invoices.add(new InvoiceDeliveryItem(invoiceId,
                        customerId,
                        customerName,
                        customerAddress,
                        isDelivered));
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

            throw new DeliveryDocketExceptionHandler("Error: failed to read deliveries.");
        } catch (DeliveryDocketExceptionHandler e) {
            throw e;
        }

        return invoices;
    }

    // get all deliveries for delivery docket
    public ArrayList getPublicationDeliveriesForDeliveryArea(int deliveryAreaId, String day) throws DeliveryDocketExceptionHandler {
        ArrayList<PublicationDeliveryItem> deliveries = new ArrayList<>();

        String query = "SELECT  delivery.delivery_id, delivery.delivery_date, customer.customer_id, customer.first_name, customer.last_name, customer.address1, customer.address2, customer.town, delivery.publication_id, publication.publication_name , delivery.delivery_status, delivery_area.name\n" +
                "FROM publication, customer, delivery, delivery_area\n" +
                "WHERE delivery.publication_id = publication.publication_id\n" +
                " AND delivery.customer_id = customer.customer_id\n" +
                " AND customer.delivery_area_id = delivery_area.delivery_area_id\n" +
                " AND customer.delivery_area_id = " + deliveryAreaId + "\n" +
                " AND delivery.delivery_date = \"" + day + "\"\n" +
                " GROUP BY delivery.delivery_id;";
        ResultSet rs;
        try {
            PreparedStatement stmt = DBconnection.con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {

                int publicationId = rs.getInt("delivery.publication_id");
                int customerId = rs.getInt("customer.customer_id");
                String customerName = rs.getString("customer.first_name") + " " + rs.getString("customer.last_name");
                String customerAddress = rs.getInt("customer.address1") + " " + rs.getString("customer.address2") + ", " + rs.getString("customer.town");
                String deliveryStatus = rs.getString("delivery.delivery_status");
                boolean isDelivered = false;
                if (deliveryStatus.equals("delivered")) {
                    isDelivered = true;
                }

                deliveries.add(new PublicationDeliveryItem(publicationId,
                        customerId,
                        customerName,
                        customerAddress,
                        isDelivered));
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

            throw new DeliveryDocketExceptionHandler("Error: failed to read deliveries.");
        } catch (DeliveryDocketExceptionHandler e) {
            throw e;
        }
        return deliveries;
    }


    // generate deliveries for next month
    public ArrayList<Delivery> generateDeliveriesForMonth(int month) {

        // delivery record consist of id, customerId, publicationID,delivery_date, status
        Utility ut = new Utility();
        // this list will contain all generated deliveries
        ArrayList<Delivery> deliveryItems = new ArrayList<>();

        // first check if the deliveries for this month wasn't generated before
        if (!deliveriesForThisMonthExists(month)) {
            try {
                // get list of orders to be able to create deliveries
                ArrayList<Order> orders = ut.getOrders();
                String deliveryStatus = "not delivered"; // default value

                // padding month to always be 2 digits (e.g. 1 > 01, 2 > 02)
                String m;
                if (String.valueOf(month).length() == 1) {
                    m = "0" + month;
                } else {
                    m = "" + month;
                }
                LocalDate startDate = convertDate("2021-" + m + "-01");
                YearMonth thisYearMonth = YearMonth.of(2021, month);
                LocalDate endDate = thisYearMonth.atEndOfMonth();

                // loop through orders
                for (Order order : orders) {
                    int customer_id = order.getCustomer_id();
                    int publication_id = order.getPublication_id();
                    int freq = order.getFrequency();

                    // this loop is starting and the beginning of the month,
                    // on every day it checks if that day corresponds to frequency
                    // if yes, the new Delivery with that date is added to the DB
                    for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                        // check if date corresponds to frequency
                        if (ut.getDayNumber(date) == freq) {
                            deliveryItems.add(new Delivery(customer_id, publication_id, date, deliveryStatus));
                        }
                    }
                }

            } catch (OrderExceptionHandler e) {
                System.out.println(e.getMessage());
            }
        }
        return deliveryItems;
    }


    public void saveDeliveries(ArrayList<Delivery> deliveries) {

    }

    public boolean deliveriesForThisMonthExists(int month) {
        boolean exists = false;

        String query = "SELECt count(*) as total\n" +
                "FROM delivery\n" +
                "WHERE MONTH(delivery_date) = " + month + ";";
        ResultSet rs;
        int count = -1;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            if (count > 0) {
                exists = true;
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);

        }

        return exists;
    }

    /**
     * Method gets the object of delivery area where the delivery person works
     *
     * @param deliveryPersonId the id of the delivery person
     * @return an object of DelivryArea
     * @throws DeliveryDocketExceptionHandler if delivery person with deliveryPersonId is not registered on any area
     */
    public DeliveryArea getDeliveryArea(int deliveryPersonId) throws DeliveryDocketExceptionHandler {

        DeliveryArea area = new DeliveryArea();
        String query = "SELECT *\n" +
                "FROM delivery_area\n" +
                "WHERE delivery_person_id = " + deliveryPersonId + ";";
        ResultSet rs;
        int deliveryAreaId = -1;
        try {
            rs = DBconnection.stmt.executeQuery(query);
            while (rs.next()) {
                deliveryAreaId = rs.getInt("delivery_area_id");
                String name = rs.getString("name");

                area.setId(deliveryAreaId);
                area.setDAreaName(name);
            }
            if (deliveryAreaId == -1) {
                throw new DeliveryDocketExceptionHandler("Delivery person with id " + deliveryPersonId + " is inactive");
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(query);
        }
        return area;
    }

    public String getDeliveryPersonName(int deliveryPersonId) throws DeliveryDocketExceptionHandler {

        String name = "";
        Utility utility = new Utility();
        // check if delivery person exists
        if (utility.deliveryPersonExists(deliveryPersonId)) {
            String query = "SELECT first_name, last_name\n" +
                    "FROM delivery_person\n" +
                    "WHERE delivery_person_id = " + deliveryPersonId + ";";
            ResultSet rs;
            try {
                rs = DBconnection.stmt.executeQuery(query);
                while (rs.next()) {
                    name = rs.getString("first_name") + " " + rs.getString("last_name");
                }

            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
                System.out.println(query);
            }
        }
        return name;
    }


    public LocalDate convertDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate currDate = null;
        // null is acceptable for holiday
        if (date != null) {
            // checking the format of start date
            try {
                currDate = LocalDate.parse(date);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return currDate;
    }

}
