package com.canvas.TechShop.exceptions;

public class PaymentException extends RuntimeException{
    public PaymentException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PaymentException(String msg) {
        super(msg);
    }
}
