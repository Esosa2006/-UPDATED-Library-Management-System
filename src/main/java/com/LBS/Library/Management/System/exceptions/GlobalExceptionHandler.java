package com.LBS.Library.Management.System.exceptions;

import com.LBS.Library.Management.System.exceptions.bookExceptions.BookAlreadyExistsException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookLimitReachedException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookNotFoundException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookSoldOutException;
import com.LBS.Library.Management.System.exceptions.librarianExceptions.LibrarianAlreadyExistsException;
import com.LBS.Library.Management.System.exceptions.userExceptions.UserAlreadyHasBookException;
import com.LBS.Library.Management.System.exceptions.userExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {GlobalRuntimeException.class})
    public ResponseEntity<Object> handleRequestException(GlobalRuntimeException e){
        Exception z = new Exception(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(z, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {BookAlreadyExistsException.class})
    public ResponseEntity<Object> handleBookAlreadyExistsHandler(BookAlreadyExistsException e){
        Exception z = new Exception(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(z, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {BookLimitReachedException.class})
    public ResponseEntity<Object> bookLimitReachedHandler(BookLimitReachedException e){
        Exception z = new Exception(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(z, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {BookNotFoundException.class})
    public ResponseEntity<Object> bookNotFoundHandler(BookNotFoundException e){
        Exception z = new Exception(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(z, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {BookSoldOutException.class})
    public ResponseEntity<Object> bookSoldOutHandler(BookSoldOutException e){
        Exception z = new Exception(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(z, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {UserAlreadyHasBookException.class})
    public ResponseEntity<Object> userAlreadyHasBookHandler(UserAlreadyHasBookException e){
        Exception z = new Exception(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(z, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> userNotFoundHandler(UserNotFoundException e){
        Exception z = new Exception(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(z, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {LibrarianAlreadyExistsException.class})
    public ResponseEntity<Object> librarianAlreadyExistsHandler(LibrarianAlreadyExistsException e){
        Exception z = new Exception(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(z, HttpStatus.BAD_REQUEST);
    }
}
