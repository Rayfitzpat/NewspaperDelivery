import junit.framework.TestCase;

public class DeliveryAreaTest extends TestCase {


    DeliveryArea deliveryArea = new DeliveryArea();


    //Test #: 1
    //Test Objective: To test validation of a correct delivery area name
    //Inputs: name="Wu"
    //Expected Output: no exception
    public void testValidateDeliveryAreaName001() {
        try {

            //Call method under test
            deliveryArea.validateDeliveryAreaName("Wu");
        }
        catch (DeliveryAreaExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    //Test #: 2
    //Test Objective: To test validation of an incorrect delivery area name
    //Inputs: name="Aaaaaaaaaaaaaaaaaaaa" (20 chars)
    //Expected Output: Exception thrown
    public void testValidateDeliveryAreaName002() {
        try {

            //Call method under test
            deliveryArea.validateDeliveryAreaName("Aaaaaaaaaaaaaaaaaaaa");
            fail("Exception expected");
        }
        catch (DeliveryAreaExceptionHandler e) {
            assertEquals("Delivery area name exceeds maximum length requirements", e.getMessage());
        }
    }
}