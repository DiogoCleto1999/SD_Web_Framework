package com.example.demo;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String m){
        super(m);
    }
    
}
