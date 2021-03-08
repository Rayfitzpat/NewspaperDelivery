package com.newspaper.gui;
import com.newspaper.customer.CustomerExceptionHandler;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public boolean validateString(String name) {
        if (name.length() > 1 && name.length() < 20) {
            if (name.matches("[a-zA-z\\s]*")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean validateEntry(String DPID) {
        if (DPID.length() > 2 || DPID.length() <1) {
            return false;
        } else if (DPID.matches("[0-9\\d]*")) {
            return true;
        }return false;
    }


//    TODO Valid house number not working for mix of letters and numbers

    public boolean validateHouseNumber(String name) {

        if (name.length() >= 1 && name.length() < 5) {
            name = name.toLowerCase();
            char[] nameArray = name.toCharArray();
            for (int i = 0; i < nameArray.length; i++) {
                char ch = nameArray[i];
                if (ch >= '0' && ch <= '9') {
                    return true;
                }
            }
            return false;
        } else

            return false;
    }


    public boolean validateStringWithNumbers(String name) {
        if (name.length() > 1 && name.length() < 20) {
            return true;
        } else
            return false;
    }

    public boolean validatePublication(String name) {
        if (name.length() > 1 && name.length() < 25) {
            return true;
        } else
            return false;
    }

    public boolean validatePhoneNumber(String deliveryPhoneNumber) {
        if (deliveryPhoneNumber.matches("\\d{3}[ ]\\d{7}")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateDoB(String dateOfBirth) {
        if (dateOfBirth.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateAccess(String access) {
        if (access.equals("1") || access.equals("2")) {
            return true;
        } else return false;
    }

    public boolean validateStatus(String status) {
        if (status.equals("true") || status.equals("false")) {
            return true;
        } else return false;
    }

    public boolean validatePassword(String password) {
        if (password.length() == 4) {
            return true;
        } else return false;
    }

    public boolean validateEircode(String eircode) throws CustomerExceptionHandler {
        // check for null value
        if (eircode == null) {
            return false;


        }
        else {
            // pattern for eircode
            Pattern phonePattern = Pattern.compile("[A-Z0-9]{7}");
            Matcher matcher = phonePattern.matcher(eircode);
            return true;

            // if eircode parameter does not correspond to regex expression, throw an exception

        }

    }

    public boolean validateID(String DPID) {
        if (DPID.length() < 1 || DPID.length() >3) {
            return false;
        } else if (DPID.matches("[0-9\\d]*")) {
            return true;
        }return false;
    }

    public boolean validateStock(String DPID) {
        if (DPID.length() < 1 || DPID.length() >3) {
            return false;
        } else if (DPID.matches("[0-9\\d]*")) {
            return true;
        }return false;
    }

    public boolean validateCost(String cost)
    {
        if(cost.length()>=1 && cost.length() <=5){
            if (cost.matches("[+-]?([0-9]*[.])?[0-9]+")) {
                return true;
            }
            else return false;
        }
        else return false;
    }


    public boolean validateFrequency(String frequency)
    {
        if(frequency.equals("daily") || frequency.equals("weekly")){

                return true;
        }
        else return false;
    }

}




