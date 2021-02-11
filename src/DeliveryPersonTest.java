//import junit.framework.TestCase;
//
//public class DeliveryPersonTest extends TestCase {
//    DeliveryPersonView dpv = new DeliveryPersonView();
//    DeliveryPerson dp = new DeliveryPerson();
//
//    //Test #: 1
//    //Test Objective: To create a Customer Record
//    //Inputs: id= 5 firstName = "Pam", lastName = "Beezley",  address1 = 87, address2 = "Willow Park", town = "Athlone",
//    // deliveryPhoneNumber ="087 8888888", dateOfBirth = "1997-11-12", accessLevel = "2", deliveryStatus = "true",
//    // userName = "PamAndJim", password = "1111"
//    //Expected Output: Customer Object created with id = 5, firstName = "Pam", lastName = "Beezley",
//    // address1 = 87, address2 = "Willow Park", town = "Athlone", deliveryPhoneNumber ="087 8888888", dateOfBirth = "1997-11-12",
//    // accessLevel = "2", deliveryStatus = "true", userName = "PamAndJim", password = "1111"
//
//    public void testDeliveryPerson001() {
//        try {
//            // create the Customer object
//            // call constructor under test
//            DeliveryPerson deliveryPerson = new DeliveryPerson(5, "Pam", "Beezley", "87", "Willow Park", "Athlone", "087 8888888", "1997-11-12", "2", "true", "PamAndJim", "1111");
//
//            // checking object creation
//            assertEquals(5, deliveryPerson.getDeliveryPersonId());
//            assertEquals("Pam", deliveryPerson.getFirstName());
//            assertEquals("Beezley", deliveryPerson.getLastName());
//            assertEquals("87", deliveryPerson.getAddress1());
//            assertEquals("Willow Park", deliveryPerson.getAddress2());
//            assertEquals("Athlone", deliveryPerson.getTown());
//            assertEquals("087 8888888", deliveryPerson.getDeliveryPhoneNumber());
//            assertEquals("1997-11-12", deliveryPerson.getDateOfBirth());
//            assertEquals("2", deliveryPerson.getAccessLevel());
//            assertEquals("true", deliveryPerson.getDeliveryStatus());
//            assertEquals("PamAndJim", deliveryPerson.getUserName());
//            assertEquals("1111", deliveryPerson.getPassword());
//        } catch (Exception e) {
//            fail("Exception not expected");
//        }
//
//    }
//    //Test #: 2
//    //Test Objective: To catch an invalid customer name
//    //Inputs: line = "B", nameOfField = "First name"
//    //Expected Output: Exception Message: "Customer Name does not meet minimum length requirements"
//
//    public void testValidateName001() throws DeliveryPersonExceptionHandler {
//
//        //Call method under test
//        dp.validateString("B");
//        fail("Exception expected");
//    }
//
//    //Test #: 3
//    //Test Objective: To catch an invalid customer name (more than 25 symbols in a name)
//    //Inputs: line = "aaaaaaaaaaaaaaaaaaaaaaaaaa"
//    //Expected Output: Exception Message: "First name exceeds maximum length requirements"
//    public void testValidateName002() throws DeliveryPersonExceptionHandler {
//
//        //Call method under test
//        dp.validateString("aaaaaaaaaaaaaaaaaaaaaaaaaa");
//        fail("Exception expected");
//    }
//
//    //Test #: 4
//    //Test Objective: To catch an invalid customer name (empty string)
//    //Inputs: line = ""
//    //Expected Output: Exception Message: "First name  NOT specified"
//    public void testValidateName003() throws DeliveryPersonExceptionHandler {
//
//        //Call method under test
//        dp.validateString("");
//        fail("Exception expected");
//    }
//
//    //Test #: 5
//    //Test Objective: To catch an invalid customer name (null string)
//    //Inputs: line = null, nameOfField = "First name"
//    //Expected Output: Exception Message: "NULL value in the arguments"
//    public void testValidateName004() throws DeliveryPersonExceptionHandler {
//
//        //Call method under test
//        dp.validateString(null);
//        fail("Exception expected");
//    }
//
//    //Test #: 6
//    //Test Objective: To catch an invalid customer name (containing digits)
//    //Inputs: line = "bbb23", nameOfField = "Last name"
//    //Expected Output: Exception Message: "Last name cannot include numbers"
//    public void testValidateName005() throws DeliveryPersonExceptionHandler {
//        try {
//
//            //Call method under test
//            dp.validateString("bbb23");
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
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
//            dp.validateName("John", "Random string");
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
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
//            dp.validateName("Mary", null);
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
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
//            dp.validateName("Martin", "First name");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
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
//            dp.validateName("O'Connor", "Last name");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//
//
//
//    //Test #: 11
//    //Test Objective: To catch an invalid address exception
//    //Inputs: address = "b", nameOFField = "Town"
//    //Expected Output: Town does not meet minimum length requirements
//    public void testValidateAddress001() throws DeliveryPersonExceptionHandler{
//        try {
//
//            //Call method under test
//            dp.validateHouseNumber("b");
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            assertEquals("INVALID", e.getMessage());
//        }
//    }
//
//    //Test #: 11
//    //Test Objective: To catch an invalid name of field exception
//    //Inputs: address = "Moate", nameOFField = "Townie"
//    //Expected Output: Townie is incorrect name of address field
//    public void testValidateAddress002() {
//        try {
//
//            //Call method under test
//            dp.validateAddress("Moate", "Townie");
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//
//            assertEquals("Townie is incorrect name of address field", e.getMessage());
//        }
//    }
//
//    //Test #: 12
//    //Test Objective: To catch an invalid address exception (address line too long)
//    //Inputs: address = "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", nameOFField = "Town"
//    //Expected Output: Townie is incorrect name of address field
//    public void testValidateAddress003() {
//        try {
//
//            //Call method under test
//            dp.validateAddress("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Town");
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            assertEquals("Town exceeds maximum length requirements", e.getMessage());
//        }
//    }
//
//    //Test #: 13
//    //Test Objective: To catch a null exception
//    //Inputs: address = "Dublin", nameOFField = null
//    //Expected Output: NULL value in the arguments
//    public void testValidateAddress005() {
//        try {
//
//            //Call method under test
//            dp.validateAddress(null, null);
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            assertEquals("NULL value in the arguments", e.getMessage());
//        }
//    }
//
//    //Test #: 14
//    //Test Objective: To check validation of a correct data
//    //Inputs: address = "Dublin", nameOFField = "Town"
//    //Expected Output: no exception
//    public void testValidateAddress006() {
//        try {
//
//            //Call method under test
//            dp.validateAddress("Dublin", "Town");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//
//    //Test #: 15
//    //Test Objective: To check validation of a correct data
//    //Inputs: address = "Strand street", nameOFField = "Address line 2"
//    //Expected Output: no exception
//    public void testValidateAddress007() {
//        try {
//
//            //Call method under test
//            dp.validateAddress("Strand street", "Address line 2");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//
//    //Test #: 16
//    //Test Objective: To check validation of a correct eircode
//    //Inputs: eircode="A11AA11"
//    //Expected Output: no exception
//    public void testValidateEircode001() {
//        try {
//            dp.validateEircode("A11AA11");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//
//    //Test #: 17
//    //Test Objective: To check validation of an incorrect eircode
//    //Inputs: eircode="a11aa11"
//    //Expected Output: Eircode does not correspond to the format "A11AA11"
//    public void testValidateEircode002() {
//        try {
//            dp.validateEircode("a11aa11");
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            assertEquals("Eircode does not correspond to the format \"A11AA11\"", e.getMessage());
//        }
//    }
//
//    //Test #: 18
//    //Test Objective: To check validation of an incorrect eircode
//    //Inputs: eircode= null
//    //Expected Output: NULL value in the argument
//    public void testValidateEircode003() {
//        try {
//            dp.validateEircode(null);
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            assertEquals("NULL value in the argument", e.getMessage());
//        }
//    }
//
//
//    //Test #: 19
//    //Test Objective: To check validation of a correct phone number
//    //Inputs: phoneNumber = "085 856 7843"
//    //Expected Output: No exception
//    public void testValidatePhoneNumber001() {
//        try {
//            dp.validatePhoneNumber("085 856 7843");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//
//
//    //Test #: 20
//    //Test Objective: To check validation of an incorrect phone number
//    //Inputs: phoneNumber = "0858567843"
//    //Expected Output: Phone number does not correspond to the format "000 000 0000"
//    public void testValidatePhoneNumber002() {
//        try {
//            dp.validatePhoneNumber("0858567843");
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            assertEquals("Phone number does not correspond to the format \"000 000 0000\"", e.getMessage());
//        }
//    }
//
//    //Test #: 21
//    //Test Objective: To check validation of an incorrect eircode
//    //Inputs: eircode = null
//    //Expected Output: NULL value in the argument
//    public void testValidatePhoneNumber003() {
//        try {
//            dp.validateEircode(null);
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            assertEquals("NULL value in the argument", e.getMessage());
//        }
//    }
//
//    //Test #: 22
//    //Test Objective: To check validation of a correct address1 (number of house or apartment)
//    //Inputs: phoneNumber = 1
//    //Expected Output: no exception
//    public void testValidateAddress1001() {
//        try {
//            dp.validateAddress1(1);
//
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//
//    //Test #: 23
//    //Test Objective: To check validation of a correct address1 (number of house or apartment)
//    //Inputs: phoneNumber = 1000
//    //Expected Output: no exception
//    public void testValidateAddress1002() {
//        try {
//            dp.validateAddress1(1000);
//
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//
//    //Test #: 24
//    //Test Objective: To check validation of an incorrect address1 (number of house or apartment)
//    //Inputs: phoneNumber = -1
//    //Expected Output: Address line 1 cannot be a negative number
//    public void testValidateAddress1003() {
//        try {
//            dp.validateAddress1(-1);
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            assertEquals("Address line 1 cannot be a negative number", e.getMessage());
//        }
//    }
//
//    //Test #: 25
//    //Test Objective: To check validation of an incorrect address1 (number of house or apartment)
//    //Inputs: phoneNumber = 1001
//    //Expected Output: Address line 1 cannot be greater than 1000
//    public void testValidateAddress1004() {
//        try {
//            dp.validateAddress1(1001);
//            fail("Exception expected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            assertEquals("Address line 1 cannot be greater than 1000", e.getMessage());
//        }
//    }
//
//
//    //Test #: 26
//    //Test Objective: To check validation of a correct date
//    //Inputs: date = "2020-11-10"
//    //Expected Output: no exception
//    public void testValidateDate001() {
//        try {
//            dp.validateDate("2020-11-10");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//
//    //Test #: 27
//    //Test Objective: To check validation of an incorrect date
//    //Inputs: date = "10-11-2020"
//    //Expected Output: Date format is incorrect
//    public void testValidateDate002() {
//        try {
//            dp.validateDate("10 11 2020");
//            fail("Exception excpected");
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            assertEquals("Date format is incorrect", e.getMessage());
//        }
//    }
//
//    //Test #: 28
//    //Test Objective: To check validation of an correct date value of null
//    //Inputs: date = null
//    //Expected Output: NULL value in the argument
//    public void testValidateDate003() {
//        try {
//            dp.validateDate(null);
//
//        }
//        catch (DeliveryPersonExceptionHandler e) {
//            fail("Exception not excpected");
//        }
//    }
//
//
//}