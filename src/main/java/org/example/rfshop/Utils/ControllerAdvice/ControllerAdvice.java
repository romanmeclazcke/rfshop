package org.example.rfshop.Utils.ControllerAdvice;

import org.apache.coyote.BadRequestException;
import org.example.rfshop.User.Infrastructure.Exception.EmailAlreadyInUse;
import org.example.rfshop.Utils.Dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException e) {
        return buildErrorResponse(
                "An unexpected error occurred. Please contact support.",
                ErrorCodes.GENERIC_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> handleBadRequestException(BadRequestException e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.BAD_REQUEST_ERROR,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmailAlreadyInUse.class)
    public ResponseEntity<ErrorDto> handleEmailAlreadyInUseException(EmailAlreadyInUse e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.EMAIL_ALREADY_IN_USE,
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception ex) {
        return buildErrorResponse(
                "An unexpected error occurred. Please contact support.",
                ErrorCodes.GENERIC_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // Method to create a Error dto
    private ResponseEntity<ErrorDto> buildErrorResponse(String errorMessage, ErrorCodes errorCode, HttpStatus httpStatus) {
        return new ResponseEntity<>(ErrorDto.builder()
                .message(errorMessage)
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .build(), httpStatus); //return error dto with the http status
    }
}
