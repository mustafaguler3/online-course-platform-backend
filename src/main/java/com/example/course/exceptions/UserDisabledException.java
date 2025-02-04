package com.example.course.exceptions;

public class UserDisabledException extends RuntimeException{
    public UserDisabledException(String message) {
        super(message);
    }
}
