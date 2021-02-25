package com.newspaper.customer;

public class CustomerExceptionHandler  extends Exception{

    private String message;

    public CustomerExceptionHandler(String errMessage){
        message = errMessage;
    }

    public String getMessage() {
        return message;
    }
}
