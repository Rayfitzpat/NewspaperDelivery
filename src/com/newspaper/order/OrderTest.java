package com.newspaper.order;

import com.newspaper.db.DBconnection;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.time.DateTimeException;

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
    //Inputs: customer_id = -1, nameOfField = "Customer ID"
    //Expected Output: Exception Message: "Customer id does not exist"

    public void testValidateCustomerId002() {
        try {
            //Call method under test
            order.validateCustomerId(-1);
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
    //Inputs: publication_id = -1, nameOfField = "Publication ID"
    //Expected Output: Exception Message: "Publication id does not exist"

    public void testValidatePublicationId002() {
        try {
            //Call method under test
            order.validatePublicationId(-1);
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


    //Test #: 7
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: int frequency = "-1", nameOfField = "Frequency"
    //Expected Output: Exception Message: "Frequency does not exist, please enter a number between 1 and 7"

    public void testValidateFrequency001() {
        try{
            order.validateFrequency(-1);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Frequency does not exist, please enter a number between 1 and 7", e.getMessage());
        }
    }

    //Test #: 8
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: int frequency = "8", nameOfField = "Frequency"
    //Expected Output: Exception Message: "Frequency does not exist, please enter a number between 1 and 7"
    public void testValidateFrequency002() {
        try{
            order.validateFrequency(8);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Frequency does not exist, please enter a number between 1 and 7", e.getMessage());
        }
    }

    //Test #: 9
    //Test Objective: To check validation on a correct ID
    //Inputs: int frequency = "5", nameOfField = "Frequency"
    //Expected Output: No Exception
    public void testValidateFrequency003() {
        try{
            order.validateFrequency(5);
        }
        catch (OrderExceptionHandler e) {
            fail("Exception expected");
        }
    }

    //Test #: 10
    //Test Objective: To check validation on a correct ID
    //Inputs: int customerID = "1", nameOfField = "Customer ID"
    //Expected Output: Valentino Firman
    public void testGetCustomerName001() {
        try {
            //Call method under test
            ov.getCustomerName(1);
        }
        catch (Exception e) {
            fail("Exception expected");
            assertEquals("Valentino Firman", e.getMessage());
        }
    }

    //Test #: 11
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: int customerID = "-1", nameOfField = "Customer ID"
    //Expected Output: "Customer does not exist"
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

    //Test #: 12
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: int customerID = "58", nameOfField = "Customer ID"
    //Expected Output: "Customer does not exist"
    public void testGetCustomerName003() {
        try {
            //Call method under test
            ov.getCustomerName(58);
        }
        catch (OrderExceptionHandler sqle) {
            assertEquals("Customer does not exist", sqle.getMessage());
        }
    }

    //Test #: 13
    //Test Objective: To check validation on a correct ID
    //Inputs: int publicationID = "5", nameOfField = "Publication ID"
    //Expected Output: "The Sunday Mirror"
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

    //Test #: 14
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: int publicationID = "-1", nameOfField = "Publication ID"
    //Expected Output: "Publication does not exist"
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

    //Test #: 15
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: int publicationID = "20", nameOfField = "Publication ID"
    //Expected Output: "Publication does not exist"
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

    //Test #: 16
    //Test Objective: To check validation on a correct ID
    //Inputs: int frequency = "5", nameOfField = "Frequency"
    //Expected Output: "Friday"
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

    //Test #: 17
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: int frequency = "-1", nameOfField = "Frequency"
    //Expected Output: Exception Message: "Frequency does not exist, please enter a number between 1 and 7" || "Invalid value for DayOfWeek: -1"
    public void testConvertFrequency002() {
        try {
            //Call method under test
            ov.convertFrequency(-1);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {

            assertEquals("Frequency does not exist, please enter a number between 1 and 7", e.getMessage());
        }
        catch (DateTimeException e) {

            assertEquals("Invalid value for DayOfWeek: -1", e.getMessage());
        }
    }

    //Test #: 18
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: int frequency = "8", nameOfField = "Frequency"
    //Expected Output: Exception Message: "Frequency does not exist, please enter a number between 1 and 7" || "Invalid value for DayOfWeek: 8"
    public void testConvertFrequency003() {
        try {
            //Call method under test
            ov.convertFrequency(8);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {

            assertEquals("Frequency does not exist, please enter a number between 1 and 7", e.getMessage());
        }
        catch (DateTimeException e) {

            assertEquals("Invalid value for DayOfWeek: 8", e.getMessage());
        }
    }


    //Test #: 19
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: customer_id = 100, nameOfField = "Customer ID"
    //Expected Output: Exception Message: "Customer does not have an order"

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

    //Test #: 20
    //Test Objective: To see if a number outside the range triggers the exception handler
    //Inputs: customer_id = -1, nameOfField = "Customer ID"
    //Expected Output: Exception Message: "Customer does not have an order"

    public void testValidateOrderCustomerId002() {
        try {
            //Call method under test
            ov.validateOrderCustomerId(-1);
            fail("Exception expected");
        }
        catch (OrderExceptionHandler e) {
            assertEquals("Customer does not have an order", e.getMessage());
        }
    }

    //Test #: 21
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

}
