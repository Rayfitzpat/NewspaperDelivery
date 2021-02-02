import junit.framework.TestCase;

public class CustomerTest extends TestCase {

    private Customer customer;

    public CustomerTest(){
        customer = new Customer();
    }


    //Test #: 1
    //Test Objective: To create a Customer Record
    //Inputs: firstName = "Jack", lastName = "Martin",  address1 = 23, address2 = "Dublin Road", town = "Athlone",
    //      eircode="M35UJ99", phoneNumber = "084 831 6481", holidayStartDate = "2021-02-19", holidayEndDate = "2021-02-29", status = "true"
    //Expected Output: Customer Object created with id = 0, firstName = "Jack", lastName = "Martin",  address1 = 23,
    //      address2 = "Dublin Road", town = "Athlone",
    //      eircode="M35UJ99", phoneNumber = "084 831 6481", holidayStartDate = "", holidayEndDate = "", status = "true"

    public void testCustomer001() {

        // create the Customer object

        try {

            // call constructor under test
            Customer customer = new Customer("Jack", "Martin", 23, "Dublin Road", "Athlone","M35UJ99","084 831 6481", "2021-02-19", "2021-02-29",  true);

            // checking object creation
            assertEquals(0, customer.getCustomerId());
            assertEquals("Jack", customer.getFirstName());
            assertEquals("Martin", customer.getLastName());
            assertEquals(23, customer.getAddress1());
            assertEquals("Dublin Road", customer.getAddress2());
            assertEquals("Athlone", customer.getTown());
            assertEquals("M35UJ99", customer.getEircode());
            assertEquals("084 831 6481", customer.getPhoneNumber());
            assertEquals("2021-02-19", customer.getHolidayStartDate());
            assertEquals("2021-02-29", customer.getHolidayEndDate());
            assertEquals(true, customer.getStatus());
        }
        catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }

    }

    //Test #: 2
    //Test Objective: To catch an invalid customer name
    //Inputs: line = "B", nameOfField = "Customer name"
    //Expected Output: Exception Message: "Customer Name does not meet minimum length requirements"

    public void testValidateName001() {
        try {

            //Call method under test
            customer.validateName("B", "Customer name");
            fail("Exception expected");
        }
        catch (CustomerExceptionHandler e) {
            assertEquals("Customer name does not meet minimum length requirements", e.getMessage());
        }
    }
}
