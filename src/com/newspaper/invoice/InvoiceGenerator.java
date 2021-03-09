package com.newspaper.invoice;

import com.newspaper.customer.Customer;
import com.newspaper.customer.CustomerDB;
import com.newspaper.customer.CustomerExceptionHandler;
import com.newspaper.deliverydocket.Delivery;
import com.newspaper.deliverydocket.Utility;

import java.util.ArrayList;

public class InvoiceGenerator {

    Utility utility = new Utility();

    public void generateInvoices(String date) {

    }

    public ArrayList<Delivery> getAllDeliveriesOfCustomerOfMonth(int customerId, int month) {
        ArrayList<Delivery> deliveries = new ArrayList<>();

        String sqlQuery = "SELECT customer.customer_id, customer.first_name, customer.last_name, delivery.delivery_date as 'date of delivery', publication.publication_name, publication.publication_cost\n" +
                "FROM customer, delivery, publication\n" +
                "WHERE delivery.customer_id = customer.customer_id \n" +
                "\tAND delivery.publication_id = publication.publication_id\n" +
                "\tAND MONTH(delivery.delivery_date) = " + customerId +"\n" +
                "\tAND customer.customer_id = " + month + ";";

        return deliveries;
    }


    public void generateInvoicesIfNeeded(int month) {
        // generate only if invoices dont exist yet
        if (!utility.ifInvoicesExist(month)) {
            // every customer has an invoice, so getting all customers from the DB
            try {
                CustomerDB customerDB = new CustomerDB();
                ArrayList<Customer> customers = customerDB.fetchCustomers();

                for (Customer customer : customers) {
                    // if customer is active, generate invoice for him
                    if (customer.getStatus()) {

                    }
                }
            }
            catch (CustomerExceptionHandler e) {
                System.out.println(e.getMessage());
            }



        }
    }
}
