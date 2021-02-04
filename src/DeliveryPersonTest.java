//import junit.framework.TestCase;
//
//public class DeliveryPersonTest extends TestCase {
//
//    //Test #: 1
//    //Test Objective: To create a Customer Record
//    //Inputs: firstName = "Jack", lastName = "Martin",  address1 = 23, address2 = "Dublin Road", town = "Athlone",
//    //      eircode="M35UJ99", phoneNumber = "084 831 6481", holidayStartDate = "2021-02-19", holidayEndDate = "2021-02-29", status = "true"
//    //Expected Output: Customer Object created with id = 0, firstName = "Jack", lastName = "Martin",  address1 = 23,
//    //      address2 = "Dublin Road", town = "Athlone",
//    //      eircode="M35UJ99", phoneNumber = "084 831 6481", holidayStartDate = "", holidayEndDate = "", status = "true"
//
//    public void testCustomer001() {
//
//        // create the Customer object
//
//        try {
//
//            // call constructor under test
//            Customer customer = new Customer("Jack", "Martin", 23, "Dublin Road", "Athlone","M35UJ99","084 831 6481", "2021-02-19", "2021-02-29",  true, 6);
//
//            // checking object creation
//            assertEquals(0, customer.getCustomerId());
//            assertEquals("Jack", customer.getFirstName());
//            assertEquals("Martin", customer.getLastName());
//            assertEquals(23, customer.getAddress1());
//            assertEquals("Dublin Road", customer.getAddress2());
//            assertEquals("Athlone", customer.getTown());
//            assertEquals("M35UJ99", customer.getEircode());
//            assertEquals("084 831 6481", customer.getPhoneNumber());
//            assertEquals("2021-02-19", customer.getHolidayStartDate());
//            assertEquals("2021-02-29", customer.getHolidayEndDate());
//            assertEquals(true, customer.getStatus());
//            assertEquals(6, customer.getDeliveryAreaId());
//        }
//        catch (CustomerExceptionHandler e) {
//            fail("Exception not expected");
//        }
//
//    }
//
//    //Test #: 2
//    //Test Objective: To catch an invalid customer name
//    //Inputs: line = "B", nameOfField = "First name"
//    //Expected Output: Exception Message: "Customer Name does not meet minimum length requirements"
//
//    public void testValidateName001() {
//        try {
//
//            //Call method under test
//            customer.validateName("B", "First name");
//            fail("Exception expected");
//        }
//        catch (CustomerExceptionHandler e) {
//            assertEquals("First name does not meet minimum length requirements", e.getMessage());
//        }
//    }
//
//    //Test #: 3
//    //Test Objective: To catch an invalid customer name (more than 25 symbolls in a name)
//    //Inputs: line = "aaaaaaaaaaaaaaaaaaaaaaaaaa", nameOfField = "First name"
//    //Expected Output: Exception Message: "First name exceeds maximum length requirements"
//    public void testValidateName002() {
//        try {
//
//            //Call method under test
//            customer.validateName("aaaaaaaaaaaaaaaaaaaaaaaaaa", "First name");
//            fail("Exception expected");
//        }
//        catch (CustomerExceptionHandler e) {
//            assertEquals("First name exceeds maximum length requirements", e.getMessage());
//        }
//    }
//
//    //Test #: 4
//    //Test Objective: To catch an invalid customer name (empty string)
//    //Inputs: line = "", nameOfField = "First name"
//    //Expected Output: Exception Message: "First name  NOT specified"
//    public void testValidateName003() {
//        try {
//
//            //Call method under test
//            customer.validateName("", "First name");
//            fail("Exception expected");
//        }
//        catch (CustomerExceptionHandler e) {
//            assertEquals("First name NOT specified", e.getMessage());
//        }
//    }
//
//    //Test #: 5
//    //Test Objective: To catch an invalid customer name (null string)
//    //Inputs: line = null, nameOfField = "First name"
//    //Expected Output: Exception Message: "NULL value in the arguments"
//    public void testValidateName004() {
//        try {
//
//            //Call method under test
//            customer.validateName(null, "First name");
//            fail("Exception expected");
//        }
//        catch (CustomerExceptionHandler e) {
//            assertEquals("NULL value in the arguments", e.getMessage());
//        }
//    }
//
//    //Test #: 6
//    //Test Objective: To catch an invalid customer name (containing digits)
//    //Inputs: line = "bbb23", nameOfField = "Last name"
//    //Expected Output: Exception Message: "Last name cannot include numbers"
//    public void testValidateName005() {
//        try {
//
//            //Call method under test
//            customer.validateName("bbb23", "Last name");
//            fail("Exception expected");
//        }
//        catch (CustomerExceptionHandler e) {
//            assertEquals("Last name cannot include numbers", e.getMessage());
//        }
//    }
//
//    //Test #: 7
//    //Test Objective: To catch an nameOfField exception.("First name" and "Last name" are the only valid strings)
//    //Inputs: line = "John", nameOfField = "Random string"
//    //Expected Output: Exception Message: "nameOfField is invalid"
//    public void testValidateName006() {
//        try {
//
//            //Call method under test
//            customer.validateName("John", "Random string");
//            fail("Exception expected");
//        }
//        catch (CustomerExceptionHandler e) {
//            assertEquals("nameOfField is invalid", e.getMessage());
//        }
//    }
//
//    //Test #: 8
//    //Test Objective: To catch an nameOfField exception (null value)
//    //Inputs: line = "Mary", nameOfField = null
//    //Expected Output: Exception Message: "NULL value in the arguments"
//    public void testValidateName007() {
//        try {
//
//            //Call method under test
//            customer.validateName("Mary", null);
//            fail("Exception expected");
//        }
//        catch (CustomerExceptionHandler e) {
//            assertEquals("NULL value in the arguments", e.getMessage());
//        }
//    }
//
//    //Test #: 9
//    //Test Objective: To test validation of a correct name
//    //Inputs: line = "Martin", nameOfField = "First name"
//    //Expected Output: no exceptions
//    public void testValidateName008() {
//        try {
//
//            //Call method under test
//            customer.validateName("Martin", "First name");
//        }
//        catch (CustomerExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//
//    //Test #: 10
//    //Test Objective: To test validation of a correct name
//    //Inputs: line = "O'Connor", nameOfField = "Last name"
//    //Expected Output: no exceptions
//    public void testValidateName009() {
//        try {
//
//            //Call method under test
//            customer.validateName("O'Connor", "Last name");
//        }
//        catch (CustomerExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//}
