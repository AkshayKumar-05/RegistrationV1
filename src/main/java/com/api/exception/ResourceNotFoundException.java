package com.api.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg) {
        super(msg); // calls constructor of parent class
    }
}
