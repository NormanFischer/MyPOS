package com.norman.MyPosServer.Exceptions;

public class InvalidItemQueryTypeException extends RuntimeException {
    public InvalidItemQueryTypeException(String msg) {
        super(msg);
    }

}
