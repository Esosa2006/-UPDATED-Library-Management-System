package com.LBS.Library.Management.System.exceptions.userExceptions;

public class UserAlreadyHasBookException extends RuntimeException {
    public UserAlreadyHasBookException(String message) {
        super(message);
    }
}
