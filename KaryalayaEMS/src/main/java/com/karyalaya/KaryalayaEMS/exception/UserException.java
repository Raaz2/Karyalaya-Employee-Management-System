package com.karyalaya.KaryalayaEMS.exception;

public class UserException extends Exception{
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String s, Exception e) {
    }
}
