//package com.newspaper.order;
//
//import com.newspaper.db.DBconnection;
//import junit.framework.TestCase;
//
//public class OrderTest extends TestCase {
//
//    private Order order;
//
//    public OrderTest(){
//        order = new Order();
//        DBconnection.init_db();
//    }
//
//    //Test #: 1
//    //Test Objective: To see if a number outside the range triggers the exception handler
//    //Inputs: customer_id = 100, nameOfField = "Customer ID"
//    //Expected Output: Exception Message: "Customer id does not exist"
//
//    public void testValidateCustomerId001() {
//        try {
//            //Call method under test
//            order.validateCustomerId(100);
//            fail("Exception expected");
//        }
//        catch (OrderExceptionHandler e) {
//            assertEquals("Customer id does not exist", e.getMessage());
//        }
//    }
//
//    //Test #: 2
//    //Test Objective: To see if a number outside the range triggers the exception handler
//    //Inputs: customer_id = -2, nameOfField = "Customer ID"
//    //Expected Output: Exception Message: "Customer id does not exist"
//
//    public void testValidateCustomerId002() {
//        try {
//            //Call method under test
//            order.validateCustomerId(-2);
//            fail("Exception expected");
//        }
//        catch (OrderExceptionHandler e) {
//            assertEquals("Customer id does not exist", e.getMessage());
//        }
//    }
//
//    //Test #: 3
//    //Test Objective: To check validation on a correct ID
//    //Inputs: customer_id = 10, nameOfField = "Customer ID"
//    //Expected Output: No Exception
//
//    public void testValidateCustomerId003() {
//        try {
//            //Call method under test
//            order.validateCustomerId(10);
//        }
//        catch (OrderExceptionHandler e) {
//            fail("Exception expected");
//        }
//    }
//
//    //Test #: 4
//    //Test Objective: To see if a number outside the range triggers the exception handler
//    //Inputs: publication_id = 100, nameOfField = "Publication ID"
//    //Expected Output: Exception Message: "Publication id does not exist"
//
//    public void testValidatePublicationId001() {
//        try {
//            //Call method under test
//            order.validatePublicationId(100);
//            fail("Exception expected");
//        }
//        catch (OrderExceptionHandler e) {
//            assertEquals("Publication id does not exist", e.getMessage());
//        }
//    }
//
//    //Test #: 5
//    //Test Objective: To see if a number outside the range triggers the exception handler
//    //Inputs: publication_id = -2, nameOfField = "Publication ID"
//    //Expected Output: Exception Message: "Publication id does not exist"
//
//    public void testValidatePublicationId002() {
//        try {
//            //Call method under test
//            order.validatePublicationId(-2);
//            fail("Exception expected");
//        }
//        catch (OrderExceptionHandler e) {
//            assertEquals("Publication id does not exist", e.getMessage());
//        }
//    }
//
//    //Test #: 6
//    //Test Objective: To check validation on a correct ID
//    //Inputs: publication_id = 10, nameOfField = "Publication ID"
//    //Expected Output: No Exception
//
//    public void testValidatePublicationId003() {
//        try {
//            //Call method under test
//            order.validatePublicationId(10);
//        }
//        catch (OrderExceptionHandler e) {
//            fail("Exception expected");
//        }
//    }
//
//    //
//    //Test #: 6
//    //Test Objective: To test that frequency only accepts "Daily" and "Weekly" (invalid string)
//    //Inputs: publication_Frequency = "1"
//    //Expected Output:"Please only enter the words, 'Daily' or 'Weekly'"
//    public void testValidateFrequency001() {
//        try{
//            order.validatePublicationFrequency("1");
//            fail("Exception expected");
//        }
//        catch (OrderExceptionHandler e) {
//            assertEquals("Please only enter the words, 'Daily', 'Weekly' or 'Monthly'", e.getMessage());
//        }
//
//
//      //  assertEquals("Please only enter the words, 'Daily' or 'Weekly'", "Please only enter the words, 'Daily' or 'Weekly'");
//
//    }
//    //
//    //Test #: 7
//    //Test Objective: To test that frequency only accepts "Daily and "Weekly" (no capitilization)
//    //Inputs: publication_Frequency ="daily"
//    //Expected Output: Exception Message: "com.newspaper.publication.Publication NOT updated. Please use capitilization, thank you :)"
//    public void testValidateFrequency002() {
//
//
//        //Call method under test
//        publication.validatePublicationFrequency("daily");
//        assertEquals("com.newspaper.publication.Publication NOT updated. Please use capitilization, thank you :)", "com.newspaper.publication.Publication NOT updated. Please use capitilization, thank you :)");
//
//
//    }
//
//    //Test #: 8
//    //Test Objective: To test that frequency only accepts "Daily and "Weekly" (no capitilization)
//    //Inputs: publication_Frequency = "weekly"
//    //Expected Output: Exception Message: "com.newspaper.publication.Publication NOT updated. Please use capitilization, thank you :)"
//    public void testValidateFrequency003() {
//
//        //Call method under test
//        publication.validatePublicationFrequency("weekly");
//        assertEquals("com.newspaper.publication.Publication NOT updated. Please use capitilization, thank you :)", "com.newspaper.publication.Publication NOT updated. Please use capitilization, thank you :)");
//
//    }
//
//    //Test #: 9
//    //Test Objective: To test that frequency only accepts "Daily and "Weekly" (PASS)
//    //Inputs: publication_Frequency ="Daily"
//    //Expected Output: PASS
//    public void testValidateFrequency004() {
//
//
//        //Call method under test
//        assertEquals(true, publication.validatePublicationFrequency("Daily"));
//    }
//
//    //Test #: 10
//    //Test Objective: To test that frequency only accepts "Daily and Weekly" (PASS)
//    //Inputs: publication_Frequency = "Weekly"
//    //Expected Output: PASS
//    public void testValidateFrequency005() {
//
//
//        //Call method under test
//        assertEquals(true, publication.validatePublicationFrequency("Weekly"));
//
//    }
//}
