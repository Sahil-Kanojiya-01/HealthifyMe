package com.example.healthifyme.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExerciseRequest {

    @NotBlank(message = "Exercise name cannot be blank")
    @Size(max = 100, message = "Exercise name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Muscle group cannot be blank")
    @Pattern(regexp = "CHEST|BACK|LEGS|ARMS|SHOULDERS|CORE|CARDIO", message = "Invalid muscle group. Allowed: CHEST, BACK, LEGS, ARMS, SHOULDERS, CORE, CARDIO")
    private String muscleGroup; // CHEST, BACK, LEGS, ARMS, SHOULDERS, CORE, CARDIO

    @NotBlank(message = "Equipment cannot be blank")
    @Pattern(regexp = "DUMBBELL|BARBELL|MACHINE|BODYWEIGHT", message = "Invalid equipment. Allowed: DUMBBELL, BARBELL, MACHINE, BODYWEIGHT")
    private String equipment; // DUMBBELL, BARBELL, MACHINE, BODYWEIGHT

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
}
