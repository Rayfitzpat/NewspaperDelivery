package com.newspaper.invoice;

public class Invoice
{
    private int invoiceId;
    private int customerId;
    private String invoiceDate;
    private double price;
    private String invoicePaid;

    public Invoice(int invoiceId, int cusId, String invDate, double price, String invoicePaid)
    {
        this.invoiceId = invoiceId;
        this.customerId = cusId;
        this.invoiceDate = invDate;
        this.price = price;
        this.invoicePaid = invoicePaid;
    }

    public Invoice()
    {

    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInvoicePaid() {
        return invoicePaid;
    }

    public void setInvoicePaid(String invoicePaid) {
        this.invoicePaid = invoicePaid;
    }

//    public void validateCustomerName(String customerName) {
//
//        int maxLength = 52;
//
//        if (customerName ==  null) {
//            throw new DeliveryDocketExceptionHandler("Customer name cannot be null");
//        }
//        else if(customerName.isBlank() || customerName.isEmpty()){
//            throw new DeliveryDocketExceptionHandler("Customer name cannot be empty");
//        }
//        else if (customerName.length() < minLength) {
//            throw new DeliveryDocketExceptionHandler("Customer name does not meet the minimum length requirements");
//        }
//        else if (customerName.length() > maxLength) {
//            throw new DeliveryDocketExceptionHandler("Customer name exceeds the maximum length requirements");
//        }
//        else {
//            // checking if line has any numbers
//            char[] charArray = customerName.toCharArray();
//            for (char c : charArray) {
//                if (Character.isDigit(c)) {
//                    throw new DeliveryDocketExceptionHandler("Customer name cannot consist of numbers");
//                }
//            }
//        }
//    }


// Class for info, All database fields + comparisons.
    // ARRAY / ARRAYLIST of all publications, As objects.


}


