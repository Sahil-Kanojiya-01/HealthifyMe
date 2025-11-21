package com.example.healthifyme.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse{

    private boolean success;
    private String message;
    private String path;
    private int status;
    private Instant timestamp;
    private Map<String, String> validationErrors;

    public static ErrorResponse of(HttpStatus status, String message, String path) {
        return ErrorResponse.builder()
                .success(false)
                .status(status.value())
                .message(message)
                .path(path)
                .timestamp(Instant.now())
                .build();
    }

    public static ErrorResponse of(HttpStatus status, String message, String path,
            List<FieldError> validationErrorsList) {
        return ErrorResponse.builder()
                .success(false)
                .status(status.value())
                .message(message)
                .path(path)
                .timestamp(Instant.now())
                .validationErrors(ErrorResponse.getValidationErrorsMap(validationErrorsList))
                .build();
    }

    public static ErrorResponse of(HttpStatus status, String message, List<FieldError> validationErrorsList) {
        return ErrorResponse.builder()
                .success(false)
                .status(status.value())
                .message(message)
                .timestamp(Instant.now())
                .validationErrors(ErrorResponse.getValidationErrorsMap(validationErrorsList))
                .build();
    }

    public ResponseEntity<ErrorResponse> toResponseEntity() {
        return ResponseEntity.status(status).body(this);
    }

    public static Map<String, String> getValidationErrorsMap(List<FieldError> validationErrorsList) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : validationErrorsList) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}
