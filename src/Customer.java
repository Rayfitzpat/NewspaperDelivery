import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private int customerId, address1;
    private String firstName, lastName, address2, town, eircode, phoneNumber, holidayStartDate, holidayEndDate;
    private boolean status;

    public Customer(String firstName, String lastName, int address1, String address2, String town, String eircode, String phoneNumber, String holidayStartDate, String holidayEndDate, boolean status) throws CustomerExceptionHandler {

        // customer record in the database is autoincrement
        customerId = 0;

        // validate input
        try{
            //validateAddress1(address1);
            validateName(firstName, "First name");
            validateName(lastName, "Last name");
            validateAddress(address2, "Address line 2");
            validateAddress(town, "Town");
            validateEircode(eircode);
            validatePhoneNumber(phoneNumber);
            //validateHoliday(holidayStartDate, holidayEndDate);
            //validateStatus(status);
        }
        catch (CustomerExceptionHandler e)
        {
            throw e;
        }

        // if there was no exception during validation, attributes are set to it's values
        this.address1 = address1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address2 = address2;
        this.town = town;
        this.eircode = eircode;
        this.phoneNumber = phoneNumber;
        this.holidayStartDate = holidayStartDate;
        this.holidayEndDate = holidayEndDate;
        this.status = status;
    }

    public Customer(){

    }

    // getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAddress1() {
        return address1;
    }

    public void setAddress1(int address1) {
        this.address1 = address1;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHolidayStartDate() {
        return holidayStartDate;
    }

    public void setHolidayStartDate(String holidayStartDate) {
        this.holidayStartDate = holidayStartDate;
    }

    public String getHolidayEndDate() {
        return holidayEndDate;
    }

    public void setHolidayEndDate(String holidayEndDate) {
        this.holidayEndDate = holidayEndDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Method is validating the data to be compliant
     * with VARCHAR fields from the Customer table in the database
     * @param line String line that needs to be validated
     */
    public void validateName(String line, String nameOfField) throws CustomerExceptionHandler {
        int minLength = 2;
        int maxLength = 25;

        if(line.isBlank() || line.isEmpty()){
            throw new CustomerExceptionHandler(nameOfField + " NOT specified");
        }

        else if (line.length() < minLength) {
            throw new CustomerExceptionHandler(nameOfField + " does not meet minimum length requirements");
        }
        else if (line.length() > maxLength) {
            throw new CustomerExceptionHandler(nameOfField + " exceeds maximum length requirements");
        }
        else {
            // checking if line has any numbers
            char[] charArray = line.toCharArray();
            for (char c : charArray) {
                if (Character.isDigit(c)) {
                    throw new CustomerExceptionHandler(nameOfField + " cannot include numbers");
                }
            }
        }
    }

    /**
     * Method is validating address lines input
     * @param address The string containing address (street or town names)
     * @param nameOfField Can be Either "Address line 2" or "Town". A placeholder for appropriate output.
     *                    It also help to reduce amount of code needed for validation
     * @throws CustomerExceptionHandler
     */
    public void validateAddress(String address, String nameOfField) throws CustomerExceptionHandler {
        int minLength = 2;
        int maxLength = 35;

        if(address.isBlank() || address.isEmpty()){
            throw new CustomerExceptionHandler(nameOfField + " NOT specified");
        }
        else if (address.length() < minLength) {
            throw new CustomerExceptionHandler(nameOfField + " does not meet minimum length requirements");
        }
        else if (address.length() > maxLength) {
            throw new CustomerExceptionHandler(nameOfField + " exceeds maximum length requirements");
        }
    }

    public void validateEircode(String eircode) throws CustomerExceptionHandler{
        String s = "[A-Z0-9]+";

        Pattern phonePattern = Pattern.compile("[A-Z0-9]{7}");
        Matcher matcher = phonePattern.matcher(eircode);

        if (!matcher.matches())
        {
            throw new CustomerExceptionHandler("Eircode does not correspond to the format \"A11AA11\"");
        }
    }


    public void validatePhoneNumber(String phoneNumber) throws CustomerExceptionHandler {
        // number format is 080 837 1923
        Pattern phonePattern = Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s]\\d{3}[\\s]\\d{4}$");
        Matcher matcher = phonePattern.matcher(phoneNumber);

        if (!matcher.matches())
        {
            throw new CustomerExceptionHandler("Phone number does not correspond to the format \"000 000 0000\"");
        }
    }


}
