package com.LBS.Library.Management.System.exceptions.bookExceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
