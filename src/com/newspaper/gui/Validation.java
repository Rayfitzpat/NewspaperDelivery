package com.newspaper.gui;
import com.newspaper.customer.CustomerExceptionHandler;
import com.newspaper.db.DBconnection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public boolean validateCustomer(String name) {
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

    public boolean validateCustomer35(String name) {
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

    public boolean validateDate(String date) {
        if (date.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateEndDate(String date) {
        if (date.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
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

    public boolean validateEircode(String eircode) {
        // check for null value

        if (eircode.length() != 7) {
            return false;
        } else if (eircode.matches("[A-Z0-9]{7}")) {
            return true;
        }return false;
    }


    public boolean validateID(String DPID) {
        if (DPID.length() < 1 || DPID.length() >3) {
            return false;
        } else if (DPID.matches("[0-9\\d]*")) {
            return true;
        }return false;
    }


    public boolean validateDeliveryID(String DPID) {
        if (DPID.length() < 1 || DPID.length() >3) {
            return false;
        } else if (DPID.matches("[0-9\\d]*")) {
            String query = "select count(*) as total from delivery_area where delivery_area_id = " + DPID;
            ResultSet rs;
            int count = 0;
            try {
                rs = DBconnection.stmt.executeQuery(query);
                while(rs.next()) {
                    count = rs.getInt("total");
                }
                if(count == 0)
                {
                    System.out.println("fghfhgfghfghfghjfjghfghjfgjh");
                    return false;
                }
                else return true;
            } catch(SQLException sqle) {
                System.out.println(sqle.getMessage());
                System.out.println(query);
                return false;
            }
        }return false;
    }

    public boolean validateDPIDIsNum(String dpid) {
        if (dpid.length() <1  || dpid.length() >3) {
            return false;
        } else if (dpid.matches("[0-9\\d]*")) {
            return true;
        }return false;
    }

    public boolean validateDPID(String dpid) throws SQLException {

        int count1;
        String strg;

        if (dpid.length() == 0) {
            return false;
        } else if (validateEntry(dpid)) {
            System.out.println("ghfdfgfgdfgdfgdfgdfgdfg");
            strg = "select count(*) as total from delivery_Person where delivery_person_id = " + dpid;
            ResultSet rs = DBconnection.stmt.executeQuery(strg);
            count1 = 0;
            while (rs.next()) {
                count1= rs.getInt("total");
            }
            if (count1 > 0) {
                System.out.println("************************************");
                return true;

            } else {
                System.out.println("$%£$%£$%£$%£$%^£$%£^$%£^%$£%$");
                return false;
            }
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




