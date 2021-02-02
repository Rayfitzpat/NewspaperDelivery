//import junit.framework.TestCase;
//
//public class DeliveryPersonTest extends TestCase {
//
//    //Test #: 1
//    //Test Objective: To create a Customer Account
//    //Inputs: DeliveryPerson = "Harry", "Potter", 123, "Willow Park", "Athlone", "087 1231231", "11-10-2021", 2, "true"
//    //Expected Output: Customer Object created with id = 0, "Jack Daniels", custAddr = "Athlone", custPhone = "087-123123123"
//
//    public void testCustomer001() {
//
//        //Create the Customer Object
//        DeliveryPersonView delPerson = new DeliveryPersonView();
//
//        try {
//
//            //Call method under test
//            DeliveryPersonView delPerson = new DeliveryPersonView(null, "Harry", "Potter", 123, "Willow Park", "Athlone", "087 1231231", "11-10-2021", 2, "true");
//
//            // Use getters to check for object creation
//            assertEquals(0, delPerson.getDeliveryPersonId());
//            assertEquals("Harry", delPerson.getFirstName());
//            assertEquals("Potter", delPerson.getFirstName());
//            assertEquals(123, delPerson.getAddress1());
//            assertEquals("Willow Park", delPerson.getAddress2());
//            assertEquals("Athlone", delPerson.getTown());
//            assertEquals("087 1231231", delPerson.getDeliveryPhoneNumber());
//            assertEquals("11-10-2021", delPerson.getDateOfBirth());
//            assertEquals(2, delPerson.getAccessLevel());
//            assertEquals("true", delPerson.getDeliveryStatus());
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            fail("Exception not expected");
//        }
//
//    }
//
//    //Test #: 2
//    //Test Objective: To catch an invalid customer name
//    //Inputs: custName = "J"
//    //Expected Output: Exception Message: "Customer Name does not meet minimum length requirements"
//
//    public void testValidateName001() {
//
//        try {
//
//            //Call method under test
//            delPerson.validateName("J");
//            fail("Exception expected");
//        }
//        catch (CustomerExceptionHandler e) {
//            assertEquals("Customer Name does not meet minimum length requirements", e.getMessage());
//        }
//    }
//
//}
//
