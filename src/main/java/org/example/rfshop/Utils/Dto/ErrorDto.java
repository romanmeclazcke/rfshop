package org.example.rfshop.Utils.Dto;

import lombok.Builder;
import lombok.Data;
import org.example.rfshop.Utils.ControllerAdvice.ErrorCodes;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
public class ErrorDto {
    private String message;
    private ErrorCodes errorCode;
    private LocalDateTime timestamp;
}
