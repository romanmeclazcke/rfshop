package org.example.rfshop.Utils.ControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.example.rfshop.BarberShop.Infrastructure.Exception.InvalidRole;
import org.example.rfshop.Booking.Infrastructure.Exception.BookingAlreadyReserved;
import org.example.rfshop.Cloudinary.infrastructure.Config.Exception.FailToDeleteImage;
import org.example.rfshop.Cloudinary.infrastructure.Config.Exception.FailToUploadImage;
import org.example.rfshop.Cloudinary.infrastructure.Config.Exception.PublicIdNotFound;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Exception.BarberShopAlreadyAddedException;
import org.example.rfshop.Post.Infrastructure.Exception.InvalidContent;
import org.example.rfshop.User.Infrastructure.Exception.EmailAlreadyInUse;
import org.example.rfshop.User.Infrastructure.Exception.RolNotFound;
import org.example.rfshop.Utils.Dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.GENERIC_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.ENTITY_NOT_FOUND_ERROR,
                HttpStatus.BAD_REQUEST
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
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
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
    public ResponseEntity<ErrorDto> handleGenericException(Exception e) {
        return buildErrorResponse(
                "An unexpected error occurred. Please contact support.",
                ErrorCodes.GENERIC_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(RolNotFound.class)
    public ResponseEntity<ErrorDto> handleGenericException(RolNotFound e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.ENTITY_NOT_FOUND_ERROR,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidRole.class)
    public ResponseEntity<ErrorDto> handleInvalidRoleException(InvalidRole e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.BAD_REQUEST_ERROR,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(FailToUploadImage.class)
    public ResponseEntity<ErrorDto> handleFailToUploadImage(FailToUploadImage e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(PublicIdNotFound.class)
    public ResponseEntity<ErrorDto> handlePublicIdNotFound(PublicIdNotFound e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(FailToDeleteImage.class)
    public ResponseEntity<ErrorDto> handleFailToDeleteImage( FailToDeleteImage e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(BarberShopAlreadyAddedException.class)
    public ResponseEntity<ErrorDto> handleBarberShopAlreadyAddedToFavorite( BarberShopAlreadyAddedException e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.BAD_REQUEST_ERROR,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUsernameNotFoundException( UsernameNotFoundException e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.BAD_REQUEST_ERROR,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorDto> handleJwtException( JwtException e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.INVALID_TOKEN,
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorDto> handleExpiredJwtException( ExpiredJwtException e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.TOKEN_EXPIRED,
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ErrorDto> handleMessagingException(MessagingException e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.MESSAGING_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(InvalidContent.class)
    public ResponseEntity<ErrorDto> handleMessagingException(InvalidContent e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.INVALID_CONTENT,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BookingAlreadyReserved.class)
    public ResponseEntity<ErrorDto> handleMessagingException(BookingAlreadyReserved e) {
        return buildErrorResponse(
                e.getMessage(),
                ErrorCodes.BAD_REQUEST_ERROR,
                HttpStatus.BAD_REQUEST
        );
    }

    // Method to create A Error dto
    private ResponseEntity<ErrorDto> buildErrorResponse(String errorMessage, ErrorCodes errorCode, HttpStatus httpStatus) {
        return new ResponseEntity<>(ErrorDto.builder()
                .message(errorMessage)
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .build(), httpStatus); //return error dto with the http status
    }
}
