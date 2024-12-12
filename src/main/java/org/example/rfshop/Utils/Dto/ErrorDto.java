package org.example.rfshop.Utils.Dto;

import lombok.Builder;
import lombok.Data;
import org.example.rfshop.Utils.ControllerAdvice.ErrorCodes;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDto {
    private String message;
    private Enum<ErrorCodes> errorCode;
    private LocalDateTime timestamp;
}
