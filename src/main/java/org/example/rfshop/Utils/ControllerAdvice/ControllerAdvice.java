package org.example.rfshop.Utils.ControllerAdvice;

import org.apache.coyote.BadRequestException;
import org.example.rfshop.Utils.Dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> runtimeExceptionHandler(RuntimeException e) {
        return buildErrorResponse(
                "An unexpected error occurred. Please contact support.",
                ErrorCodes.GENERIC_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorDto> badRequestExceptionHandler(BadRequestException e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.BAD_REQUEST_ERROR,
                HttpStatus.BAD_REQUEST
        );
    }


    private ResponseEntity<ErrorDto> buildErrorResponse(String errorMessage, Enum<ErrorCodes> errorCode, HttpStatus httpStatus) {
        return new ResponseEntity<>(ErrorDto.builder()
                .message(errorMessage)
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .build(), httpStatus); //return error dto with the http status
    }
}
