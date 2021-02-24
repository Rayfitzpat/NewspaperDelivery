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


// Class for info, All database fields + comparisons.
    // ARRAY / ARRAYLIST of all publications, As objects.
}


//TODO Verify Each Invoice has individual number (ID) (DONE THROUGH AUTO INCREMEMTNS)
//TODO VERYIFY CUSTOMER NAME
//TODO VERIFY INVOICE CONTAINS CUSTOMER ADDRESS
//TODO VERIFY THAT EACH PURCAHSE OR PUBLICATION IS ITEMISED (NEEDS ACCESS TO THE DELIVERY DOCKET)
//TODO VERIFY INVOICE CONTAINS A LIST OF THE PUBLICATIONS DELIVERED (ALSO NEEDS ACCESS TO THE DELIVERY DOCKET)
//TODO VERIFY THAT EACH ITEM HAS A VAT RATE ((APPLY 23% INCREASE ON PRICE OF EACH ITEM)