package com.canvas.TechShop.exceptions;

public class RoleException extends RuntimeException{
    public RoleException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RoleException(String msg) {
        super(msg);
    }
}
