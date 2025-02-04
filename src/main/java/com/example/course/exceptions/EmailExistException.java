package com.example.course.exceptions;

public class EmailExistException extends RuntimeException{
    public EmailExistException(String message) {
        super(message);
    }
}
