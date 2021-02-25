package com.newspaper.order;

import com.newspaper.db.DBconnection;
import junit.framework.TestCase;

//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.fail;

public class OrderTest extends TestCase {

    private Order order;

    public OrderTest(){
        order = new Order();
        DBconnection.init_db();
    }

    //Test #: 2
    //Test Objective: To catch an invalid customer name
    //Inputs: line = "B", nameOfField = "First name"
    //Expected Output: Exception Message: "Customer Name does not meet minimum length requirements"



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

}
