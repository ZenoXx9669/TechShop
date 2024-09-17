package com.canvas.TechShop.exceptions;

public class UnknownCardTypeException extends PaymentException{
    public UnknownCardTypeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnknownCardTypeException(String msg) {
        super(msg);
    }
}
