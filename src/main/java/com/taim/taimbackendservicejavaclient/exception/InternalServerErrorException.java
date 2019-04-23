package com.taim.taimbackendservicejavaclient.exception;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String msg) {
        super(msg);
    }

    public InternalServerErrorException(String msg, Exception ex) {
        super(msg, ex);
    }
}
