package com.prog.consultations.exception;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String message){
        super(message);
    }
}
