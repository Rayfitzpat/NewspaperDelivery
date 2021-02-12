import junit.framework.TestCase;

public class PublicationTest extends TestCase {

    Publication publication = new Publication();

    public PublicationTest() {
        Publication publication = new Publication();
    }


    //Test #: 1
    //Test Objective: To create a publication Record
    //Inputs: publication_Name = "Rays Thoughts",  publication_Frequency = Daily, publication_Cost = "2.50", publication_Stock_Level= "30",
    //Expected Output: Publication Object created with id = 0, publication_Name = "Rays Thoughts", publication_Cost = 2.50,  publication_Frequency= "Daily", publication_Stock_Level =35

    public void testPublication001() throws PublicationExceptionHandler {

        // create the Publication object

        // call constructor under test
        Publication publication = new Publication(0, "Rays Thoughts", 2.50, "Daily", 35);

        // checking object creation
        assertEquals(0, publication.getpublication_id());
        assertEquals("Rays Thoughts", publication.getpublication_name());
        assertEquals(2.50, publication.getCost());
        assertEquals("Daily", publication.getFrequency());
        assertEquals(35, publication.getStockLevel());


    }
//
    //Test #: 2
    //Test Objective: To catch an invalid publication name with less than 3 characters
    //Inputs: Publication name = "JJ",
    //Expected Output: Exception Message: "The Publication Name you entered is not the valid length"

    public void testValidateName001() throws PublicationExceptionHandler {

        //Call method
        publication.validatePublicationName("JJ");
        assertEquals("The Publication Name you entered is not the valid length", "The Publication Name you entered is not the valid length");
    }

    //Test #: 3
    //Test Objective: To catch an invalid publication name (31 symbols in a name)
    //Inputs: publication_Name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    //Expected Output: Exception Message: "The Publication Name you entered is not the valid length"
    public void testValidateName002() throws PublicationExceptionHandler {

        //Call method under test
        publication.validatePublicationName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        fail("Exception expected");
    }

    //Test #: 4
    //Test Objective: To catch an invalid publication name (empty string)
    //Inputs: publication_Name = ""
    //Expected Output: Exception Message: "The publication name you have entered contains invalid character(s). Please try again."
    public void testValidateName003() {
        try {

            //Call method under test
            publication.validatePublicationName("");
            fail("Exception expected");
        } catch (PublicationExceptionHandler e) {
            assertEquals("First name NOT specified", e.getMessage());
        }
    }

    //Test #: 5
    //Test Objective: To catch an invalid publication name (number in string)
    //Inputs: publication_Name = "The Clara 1Times"
    //Expected Output: Exception Message: "The publication name you have entered contains invalid character(s). Please try again."
    public void testValidateName004() {
        try {

            //Call method under test
            publication.validateName(null, "First name");
            fail("Exception expected");
        } catch (PublicationExceptionHandler e) {
            assertEquals("NULL value in the arguments", e.getMessage());
        }
    }

    //Test #: 5.5
    //Test Objective: To pass a valid publication name
    //Inputs: publication_Name = "The Clara Times"
    //Expected Output: PASS
    public void testValidateName005() {
        try {

            //Call method under test
            publication.validateName(null, "First name");
            fail("Exception expected");
        } catch (PublicationExceptionHandler e) {
            assertEquals("NULL value in the arguments", e.getMessage());
        }
    }

    //
    //Test #: 6
    //Test Objective: To test that frequency only accepts "Daily" and "Weekly" (invalid string)
    //Inputs: publication_Frequency = "1"
    //Expected Output:"Please only enter the words, 'Daily' or 'Weekly'"
    public void testValidateFrequency001() {
        try {

            //Call method under test
            publication.validateName("bbb23", "Last name");
            fail("Exception expected");
        } catch (PublicationExceptionHandler e) {
            assertEquals("Last name cannot include numbers", e.getMessage());
        }
    }

    //
    //Test #: 7
    //Test Objective: To test that frequency only accepts "Daily and "Weekly" (no capitilization)
    //Inputs: publication_Frequency ="daily"
    //Expected Output: Exception Message: "Publication NOT updated. Please use capitilization, thank you :)"
    public void testValidateFrequency002() {
        try {

            //Call method under test
            publication.validateName("John", "Random string");
            fail("Exception expected");
        } catch (PublicationExceptionHandler e) {
            assertEquals("nameOfField is invalid", e.getMessage());
        }
    }

    //Test #: 8
    //Test Objective: To test that frequency only accepts "Daily and "Weekly" (no capitilization)
    //Inputs: publication_Frequency = "weekly"
    //Expected Output: Exception Message: "Publication NOT updated. Please use capitilization, thank you :)"
    public void testValidateFrequency003() {
        try {

            //Call method under test
            publication.validatePublicationFrequency("weekly");
            fail("Exception expected");
        } catch (PublicationExceptionHandler e) {
            assertEquals("NULL value in the arguments", e.getMessage());
        }
    }

    //Test #: 9
    //Test Objective: To test that frequency only accepts "Daily and "Weekly" (PASS)
    //Inputs: publication_Frequency ="Daily"
    //Expected Output: PASS
    public void testValidateFrequency004() {
        try {

            //Call method under test
            publication.validateName("Martin", "First name");
        } catch (PublicationExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    //Test #: 10
    //Test Objective: To test that frequency only accepts "Daily and Weekly" (PASS)
    //Inputs: publication_Frequency = "Weekly"
    //Expected Output: PASS
    public void testValidateFrequency005() {
        try {

            //Call method under test
            publication.validateName("O'Connor", "Last name");
        } catch (PublicationExceptionHandler e) {
            fail("Exception not expected");
        }
    }


    //Test #: 11
    //Test Objective: To catch an invalid input when a number is prompted, used for publicationCost
    //Inputs: ten
    //Expected Output: "A character you entered is not a valid number, please try again using only valid numbers."
    public void testValidateNumber001() {
        try {

            //Call method under test
            publication.validateAddress("b", "Town");
            fail("Exception expected");
        } catch (PublicationExceptionHandler e) {
            assertEquals("Town does not meet minimum length requirements", e.getMessage());
        }
    }

    //Test #: 11
    //Test Objective: To catch an invalid input when a number is prompted, used for publicationCost
    //Inputs: 1.5
    //Expected Output: PASS
    public void testValidateNumber002() {
        try {

            //Call method under test
            publication.validateAddress("Moate", "Townie");
            fail("Exception expected");
        } catch (PublicationExceptionHandler e) {

            assertEquals("Townie is incorrect name of address field", e.getMessage());
        }
    }

    //Test #: 12
    //Test Objective:To catch an invalid input when a number is prompted, used for publicationCost
    //Inputs: 9.5
    //Expected Output: PASS
    public void testValidateNumber003() {
        try {

            //Call method under test
            publication.validateAddress("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Town");
            fail("Exception expected");
        } catch (PublicationExceptionHandler e) {
            assertEquals("Town exceeds maximum length requirements", e.getMessage());
        }
    }

    //Test #: 13
    //Test Objective: To test an ID entered is a valid number ( invalid character)
    //Inputs: sixteen
    //Expected Output:  "Your entry is invalid as it does not contain a whole number"
    public void testValidateId001() {
        try {

            //Call method under test
            publication.validateAddress(null, null);
            fail("Exception expected");
        } catch (PublicationExceptionHandler e) {
            assertEquals("NULL value in the arguments", e.getMessage());
        }
    }

    //Test #: 14
    //Test Objective: To test an ID entered is a valid number
    //Inputs: 1
    //Expected Output: PASS
    public void testValidateId002() {
        try {

            //Call method under test
            publication.validateAddress("Dublin", "Town");
        } catch (PublicationExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    //Test #: 15
    //Test Objective:  To test an ID entered is a valid number
    //Inputs: 9
    //Expected Output: PASS
    public void testValidateID003() {
        try {

            //Call method under test
            publication.validateAddress("Strand street", "Address line 2");
        } catch (PublicationExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    //Test #: 16
    //Test Objective: Test method "askUserYesOrNo" (invalid entry).
    //Inputs: 100
    //Expected Output:"You entered an invalid answer, please use \"yes\" or \"no\"..."
    public void testAskUserYesOrNo001() {
        try {
            publication.validateEircode("A11AA11");
        } catch (PublicationExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    //Test #: 17
    //Test Objective: Test method "askUserYesOrNo" (invalid entry).
    //Inputs: "SURE"
    //Expected Output: "You entered an invalid answer, please use \"yes\" or \"no\"..."
    public void testAskUserYesOrNo002() {
        try {
            publication.validateEircode("a11aa11");
            fail("Exception expected");
        }
        catch (PublicationExceptionHandler e) {
            assertEquals("Eircode does not correspond to the format \"A11AA11\"", e.getMessage());
        }
    }

    //Test #: 18
    //Test Objective: Test method "askUserYesOrNo" (PASS).
    //Inputs: "Yes
    //Expected Output: PASS
    public void testAskUserYesOrNo003() {
        try {
            publication.validateEircode(null);
            fail("Exception expected");
        }
        catch (PublicationExceptionHandler e) {
            assertEquals("NULL value in the argument", e.getMessage());
        }
    }


    //Test #: 19
    //Test Objective: Test method "askUserYesOrNo" (PASS).
    //Inputs: "No"
    //Expected Output: PASS
    public void testAskUserYesOrNo004() {
        try {
            publication.validatePhoneNumber("085 856 7843");
        }
        catch (PublicationExceptionHandler e) {
            fail("Exception not expected");
        }
    }
//
//
//    //Test #: 20
//    //Test Objective: To check validation of an incorrect phone number
//    //Inputs: phoneNumber = "0858567843"
//    //Expected Output: Phone number does not correspond to the format "000 000 0000"
//    public void testValidatePhoneNumber002() {
//        try {
//            publication.validatePhoneNumber("0858567843");
//            fail("Exception expected");
//        }
//        catch (PublicationExceptionHandler e) {
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
//            publication.validateEircode(null);
//            fail("Exception expected");
//        }
//        catch (PublicationExceptionHandler e) {
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
//            publication.validateAddress1(1);
//
//        }
//        catch (PublicationExceptionHandler e) {
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
//            publication.validateAddress1(1000);
//
//        }
//        catch (PublicationExceptionHandler e) {
//            fail("Exception not expected");
//        }
//    }
//
}


