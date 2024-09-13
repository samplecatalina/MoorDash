package com.moordash.exception;

/**
 * Exception for failed login
 */
public class LoginFailedException extends BaseException{
    public LoginFailedException(String msg){
        super(msg);
    }
}
