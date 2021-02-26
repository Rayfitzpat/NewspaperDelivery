package com.newspaper.gui;

public class Validation {

    public boolean validateString (String name) {
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

    public boolean validateEntry (String id){
        if(id.length() < 1 || id.length() >2 || id.equals(null)) {
            return false;
        }
        else if (id.matches("[0-9\\d]*")){
            return true;
        }
        else {
            return false;
        }
    }


//    TODO Valid house number not working for mix of letters and numbers

    public boolean validateHouseNumber (String name){

        if(name.length()>=1 && name.length()<5) {
            name = name.toLowerCase();
            char[] nameArray = name.toCharArray();
            for (int i = 0; i < nameArray.length; i++) {
                char ch = nameArray[i];
                if (ch >= '0' && ch <= '9') {
                    return true;
                }
            }
            return false;
        }
        else

        return false;
    }


    public boolean validateStringWithNumbers (String name){
        if(name.length()>1 && name.length()<20) {
            return true;
        }
        else
            return false;
    }

}
