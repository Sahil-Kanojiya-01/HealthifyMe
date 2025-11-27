package com.example.healthifyme.service;

import com.example.healthifyme.DTO.request.CreateExerciseRequest;
import com.example.healthifyme.DTO.request.UpdateExerciseRequest;
import com.example.healthifyme.DTO.response.ExerciseResponse;
import com.example.healthifyme.DTO.response.mapper.ExerciseResponseMapper;
import com.example.healthifyme.entity.Exercise;
import com.example.healthifyme.exception.EntityNotFoundException;
import com.example.healthifyme.repository.ExerciseRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExerciseService{

    private final ExerciseRepository exerciseRepository;

    public List<ExerciseResponse> getExercises() {
        List<Exercise> exerciseList = exerciseRepository.findAll();
        return exerciseList.stream().map(ExerciseResponseMapper::mapFromExercise).toList();
    }

    public ExerciseResponse getExerciseById(UUID id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(String.format("Exercise not found with id(%s)", id)));
        return ExerciseResponseMapper.mapFromExercise(exercise);
    }

    public void createExercise(@Valid CreateExerciseRequest createExerciseRequest) {
        Exercise exercise = new Exercise();

        exercise.setName(createExerciseRequest.getName());
        exercise.setMuscleGroup(createExerciseRequest.getMuscleGroup());
        exercise.setEquipment(createExerciseRequest.getEquipment());
        exercise.setDescription(createExerciseRequest.getDescription());

        exerciseRepository.save(exercise);
    }

    public void updateExercise(UUID id, @Valid UpdateExerciseRequest updateExerciseRequest) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);

        if (optionalExercise.isPresent()) {
            Exercise exercise = optionalExercise.get();

            exercise.setName(updateExerciseRequest.getName());
            exercise.setMuscleGroup(updateExerciseRequest.getMuscleGroup());
            exercise.setEquipment(updateExerciseRequest.getEquipment());
            exercise.setDescription(updateExerciseRequest.getDescription());
        }
    }

    public void deleteExercise(UUID id) {
        exerciseRepository.deleteById(id);
    }
}
