package com.newspaper.gui;
import com.newspaper.deliveryperson.DeliveryPerson;


import java.awt.*;

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



}




