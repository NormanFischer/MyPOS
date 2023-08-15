package com.norman.MyPosServer;

public class InvalidItemQueryTypeException extends RuntimeException {
    public InvalidItemQueryTypeException(String msg) {
        super(msg);
    }

}
