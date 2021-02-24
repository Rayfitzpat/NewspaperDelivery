package Delivery;

// this class will represent invoice delivery item in the delivery docket
public class InvoiceDeliveryItem extends DeliveryItem {

    private int invoiceId;

    public InvoiceDeliveryItem(int invoiceId, int customerID, String customerName, String customerAddress, String type, boolean isDelivered) throws DeliveryDocketExceptionHandler {
        super(customerID, customerName, customerAddress, type, isDelivered);

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

    }
}
