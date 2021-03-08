package com.newspaper.order;

import com.newspaper.db.DBconnection;
import junit.framework.TestCase;

public class OrderTest extends TestCase {

    private Order order;

    public OrderTest(){
        order = new Order();
        DBconnection.init_db();
    }

    //Test #: 1
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: customer_id = 100, nameOfField = "Customer ID"
    //Expected Output: Exception Message: "Customer id does not exist"

    public void testValidateCustomerId001() {
        try {
            //Call method under test
            order.validateCustomerId(100);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Customer id does not exist", e.getMessage());
        }
    }

    //Test #: 2
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: customer_id = -2, nameOfField = "Customer ID"
    //Expected Output: Exception Message: "Customer id does not exist"

    public void testValidateCustomerId002() {
        try {
            //Call method under test
            order.validateCustomerId(-2);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Customer id does not exist", e.getMessage());
        }
    }

    //Test #: 3
    //Test Objective: To check validation on a correct ID
    //Inputs: customer_id = 10, nameOfField = "Customer ID"
    //Expected Output: No Exception

    public void testValidateCustomerId003() {
        try {
            //Call method under test
            order.validateCustomerId(10);
        }
        catch (OrderExceptionHandler e) {
            fail("Exception expected");
        }
    }

    //Test #: 4
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: publication_id = 100, nameOfField = "Publication ID"
    //Expected Output: Exception Message: "Publication id does not exist"

    public void testValidatePublicationId001() {
        try {
            //Call method under test
            order.validatePublicationId(100);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Publication id does not exist", e.getMessage());
        }
    }

    //Test #: 5
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: publication_id = -2, nameOfField = "Publication ID"
    //Expected Output: Exception Message: "Publication id does not exist"

    public void testValidatePublicationId002() {
        try {
            //Call method under test
            order.validatePublicationId(-2);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Publication id does not exist", e.getMessage());
        }
    }

    //Test #: 6
    //Test Objective: To check validation on a correct ID
    //Inputs: publication_id = 10, nameOfField = "Publication ID"
    //Expected Output: No Exception

    public void testValidatePublicationId003() {
        try {
            //Call method under test
            order.validatePublicationId(10);
        }
        catch (OrderExceptionHandler e) {
            fail("Exception expected");
        }
    }


    //Test #: 6
    //Test Objective: To test that frequency only accepts ints between 1 and 7
    //Inputs: int frequency = "0"
    //Expected Output:"Frequency does not exist, please enter a number between 1 and 7"
    public void testValidateFrequency001() {
        try{
            order.validateFrequency(0);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Frequency does not exist, please enter a number between 1 and 7", e.getMessage());
        }


    }

    //Test #: 7
    //Test Objective: To test that frequency only accepts ints between 1 and 7
    //Inputs: int frequency = "8"
    //Expected Output:"Frequency does not exist, please enter a number between 1 and 7"
    public void testValidateFrequency002() {
        try{
            order.validateFrequency(8);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Frequency does not exist, please enter a number between 1 and 7", e.getMessage());
        }


    }


    //Test #: 7
    //Test Objective: To test that frequency only accepts ints between 1 and 7
    //Inputs: int frequency = "f"
    //Expected Output:"Frequency does not exist, please enter a number between 1 and 7"
    public void testValidateFrequency003() {
        try{
            order.validateFrequency(5);

        }
        catch (OrderExceptionHandler e) {
            fail("Exception expected");
        }

    }

}
