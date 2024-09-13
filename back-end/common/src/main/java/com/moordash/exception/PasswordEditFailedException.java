package com.moordash.exception;

/**
 * Exception for failed password change
 */
public class PasswordEditFailedException extends BaseException{

    public PasswordEditFailedException(String msg){
        super(msg);
    }

}
