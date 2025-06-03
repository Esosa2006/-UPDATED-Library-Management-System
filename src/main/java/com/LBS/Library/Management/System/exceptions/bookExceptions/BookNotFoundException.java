package com.LBS.Library.Management.System.exceptions.bookExceptions;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(String message) {
    super(message);
  }
}
