package com.example.healthifyme.DTO.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileRequest{

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Age is required")
    @Min(value = 10, message = "Age must be at least 10")
    @Max(value = 120, message = "Age must be less than or equal to 120")
    private Integer age;

    @NotNull(message = "Weight is required")
    @DecimalMin(value = "20.0", message = "Weight must be at least 20 kg")
    @DecimalMax(value = "400.0", message = "Weight must be less than 400 kg")
    private Double weight;

    @NotNull(message = "Height is required")
    @DecimalMin(value = "50.0", message = "Height must be at least 50 cm")
    @DecimalMax(value = "300.0", message = "Height must be less than 300 cm")
    private Double height;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be MALE, FEMALE, or OTHER")
    private String gender;

    @NotBlank(message = "Activity level is required")
    @Pattern(regexp = "SEDENTARY|LIGHTLY_ACTIVE|MODERATELY_ACTIVE|VERY_ACTIVE", message = "Activity level must be one of: SEDENTARY, LIGHTLY_ACTIVE, MODERATELY_ACTIVE, VERY_ACTIVE")
    private String activityLevel;

    @NotNull(message = "Goal ID is required")
    private UUID goalId;
}
