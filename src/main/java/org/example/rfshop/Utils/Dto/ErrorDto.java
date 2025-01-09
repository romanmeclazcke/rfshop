package org.example.rfshop.Utils.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.example.rfshop.Utils.ControllerAdvice.ErrorCodes;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
public class ErrorDto {
    private String message;
    private ErrorCodes errorCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}
