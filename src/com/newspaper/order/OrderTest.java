package com.newspaper.order;

import com.newspaper.db.DBconnection;
import junit.framework.TestCase;

import java.sql.SQLException;

public class OrderTest extends TestCase {

    private final Order order;

    public OrderTest(){
        order = new Order();
        DBconnection.init_db();
    }

    OrderView ov = new OrderView();

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


    public void testGetCustomerName001() {
        try {
            //Call method under test
            ov.getCustomerName(1);
            //assertEquals("Valentino Firman");
        }
        catch (Exception e) {
            fail("Exception expected");
            //assertEquals("Valentino Firman");
            assertEquals("Valentino Firman", e.getMessage());
        }
    }

    public void testGetCustomerName002() {
        try {
            //Call method under test
            ov.getCustomerName(-1);
        }
        catch (OrderExceptionHandler sqle) {
            //fail("Exception expected");
            assertEquals("Customer does not exist", sqle.getMessage());
        }
    }

    public void testGetCustomerName003() {
        try {
            //Call method under test
            ov.getCustomerName(58);
        }
        catch (OrderExceptionHandler sqle) {
            assertEquals("Customer does not exist", sqle.getMessage());
        }
    }

    public void testGetPublicationByID001() {
        try {
            //Call method under test
            ov.getPublicationByID(5);
        }
        catch (OrderExceptionHandler sqle) {
            //fail("Exception expected");
            assertEquals("The Sunday Mirror", sqle.getMessage());
        }
    }

    public void testGetPublicationByID002() {
        try {
            //Call method under test
            ov.getPublicationByID(-1);
        }
        catch (OrderExceptionHandler sqle) {
            //fail("Exception expected");
            assertEquals("Publication does not exist", sqle.getMessage());
        }
    }

    public void testGetPublicationByID003() {
        try {
            //Call method under test
            ov.getPublicationByID(20);
        }
        catch (OrderExceptionHandler sqle) {
            //fail("Exception expected");
            assertEquals("Publication does not exist", sqle.getMessage());
        }
    }

    public void testConvertFrequency001() {
        try {
            //Call method under test
            ov.convertFrequency(5);
        }
        catch (OrderExceptionHandler sqle) {
            //fail("Exception expected");
            assertEquals("Friday", sqle.getMessage());
        }
    }

    public void testConvertFrequency002() {
        try {
            //Call method under test
            ov.convertFrequency(-1);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler sqle) {

            assertEquals("Frequency does not exist", sqle.getMessage());
        }
    }

    public void testConvertFrequency003() {
        try {
            //Call method under test
            ov.convertFrequency(8);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler sqle) {

            assertEquals("Frequency does not exist", sqle.getMessage());
        }
    }

//    public void testDisplayOrderByCustomerId() {
//        try{
//            ov.displayOrderByCustomerId();
//
//        }
//        catch(){
//
//        }
//    }


    //Test #: 1
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: customer_id = 100, nameOfField = "Customer ID"
    //Expected Output: Exception Message: "Customer id does not exist"

    public void testValidateOrderCustomerId001() {
        try {
            //Call method under test
            ov.validateOrderCustomerId(100);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Customer does not have an order", e.getMessage());
        }
    }

    //Test #: 2
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: customer_id = -2, nameOfField = "Customer ID"
    //Expected Output: Exception Message: "Customer id does not exist"

    public void testValidateOrderCustomerId002() {
        try {
            //Call method under test
            ov.validateOrderCustomerId(-2);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Customer does not have an order", e.getMessage());
        }
    }

    //Test #: 3
    //Test Objective: To check validation on a correct ID
    //Inputs: customer_id = 10, nameOfField = "Customer ID"
    //Expected Output: No Exception

    public void testValidateOrderCustomerId003() {
        try {
            //Call method under test
            ov.validateOrderCustomerId(10);
        }
        catch (OrderExceptionHandler e) {
            fail("Exception expected");
        }
    }

//    public void testAddNewOrder001() {
//        try {
//
//        }
//        catch() {
//
//        }
//    }

//    public void testAddNewOrderCustomerID001() {
//        try {
//            ov.addNewOrderCustomerID();
//        }
//        catch (OrderExceptionHandler orderExceptionHandler) {
//            orderExceptionHandler.printStackTrace();
//        }
//    }


}
