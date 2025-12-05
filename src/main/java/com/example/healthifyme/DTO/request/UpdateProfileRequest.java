package com.example.healthifyme.DTO.request;

import com.example.healthifyme.entity.Profile;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest{
    @Min(value = 10, message = "Age must be at least 10")
    @Max(value = 120, message = "Age must be less than or equal to 120")
    private Integer age;

    @DecimalMin(value = "20.0", message = "Weight must be at least 20 kg")
    @DecimalMax(value = "400.0", message = "Weight must be less than 400 kg")
    private Double weight;

    @DecimalMin(value = "50.0", message = "Height must be at least 50 cm")
    @DecimalMax(value = "300.0", message = "Height must be less than 300 cm")
    private Double height;

    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be MALE, FEMALE, or OTHER")
    private Profile.Gender gender;

    @Pattern(regexp = "SEDENTARY|LIGHTLY_ACTIVE|MODERATELY_ACTIVE|VERY_ACTIVE", message = "Activity level must be one of: SEDENTARY, LIGHTLY_ACTIVE, MODERATELY_ACTIVE, VERY_ACTIVE")
    private Profile.ActivityLevel activityLevel;

    private UUID goalId; // Optional goal update
}
