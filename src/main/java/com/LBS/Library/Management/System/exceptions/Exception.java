package com.LBS.Library.Management.System.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record Exception (String message, HttpStatus httpStatus, ZonedDateTime timestamp){

}
