package com.wa.test.watest.exceptions;

public class UserUpdateException extends RuntimeException{
    private String msg;

    public UserUpdateException(String msg){
        super();
        this.msg = msg;
    }

    public String getMsg(){
        return this.msg;
    }
}
