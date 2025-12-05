package com.example.healthifyme.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResponse {

    private String id;

    private String name;

    private String muscleGroup; // CHEST, BACK, LEGS, ARMS, SHOULDERS, CORE, CARDIO

    private String equipment; // DUMBBELL, BARBELL, MACHINE, BODYWEIGHT

    private String description;
}
