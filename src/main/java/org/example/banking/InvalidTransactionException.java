package org.example.banking;

public class InvalidTransactionException extends Exception{
    public InvalidTransactionException(String message){
        super(message);
    }
}
