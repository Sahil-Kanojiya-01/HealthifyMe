package com.example.healthifyme.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestApiResponse<T> {

    private boolean success;
    private String message;
    private int status;
    private T data;
    private Instant timestamp;

    public static <T> RestApiResponse<T> success(String message, T data, HttpStatus httpStatus) {
        return RestApiResponse.<T>builder()
                .success(true)
                .message(message)
                .status(httpStatus.value())
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> RestApiResponse<T> failure(String message, HttpStatus httpStatus) {
        return RestApiResponse.<T>builder()
                .success(false)
                .message(message)
                .status(httpStatus.value())
                .timestamp(Instant.now())
                .build();
    }

    public ResponseEntity<RestApiResponse<T>> toResponseEntity() {
        return ResponseEntity.status(status).body(this);
    }
}
