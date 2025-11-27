package com.example.healthifyme.DTO.response.mapper;

import com.example.healthifyme.DTO.response.ExerciseResponse;
import com.example.healthifyme.entity.Exercise;

public class ExerciseResponseMapper{

    public static ExerciseResponse mapFromExercise(Exercise exercise) {
        ExerciseResponse exerciseResponse = new ExerciseResponse();

        exerciseResponse.setId(exercise.getId().toString());
        exerciseResponse.setName(exercise.getName());
        exerciseResponse.setMuscleGroup(exercise.getMuscleGroup());
        exerciseResponse.setEquipment(exercise.getEquipment());
        exerciseResponse.setDescription(exercise.getDescription());

        return exerciseResponse;
    }

}
