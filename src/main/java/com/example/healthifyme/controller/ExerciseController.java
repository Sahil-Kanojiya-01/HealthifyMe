package com.example.healthifyme.controller;

import com.example.healthifyme.DTO.request.CreateExerciseRequest;
import com.example.healthifyme.DTO.request.UpdateExerciseRequest;
import com.example.healthifyme.DTO.response.ExerciseResponse;
import com.example.healthifyme.DTO.response.RestApiResponse;
import com.example.healthifyme.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/")
    public ResponseEntity<RestApiResponse<List<ExerciseResponse>>> getExercises() {
        List<ExerciseResponse> exerciseResponseList = exerciseService.getExercises();
        RestApiResponse<List<ExerciseResponse>> restApiResponse =
                RestApiResponse.success("Exercises fetched successfully", exerciseResponseList, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<ExerciseResponse>> getExercise(@PathVariable("id") UUID id) {
        ExerciseResponse exerciseResponse = exerciseService.getExerciseById(id);
        RestApiResponse<ExerciseResponse> restApiResponse =
                RestApiResponse.success("Exercise fetched successfully", exerciseResponse, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @PostMapping("/")
    public ResponseEntity<RestApiResponse<Void>> createExercise(
            @Valid @RequestBody CreateExerciseRequest createExerciseRequest) {
        exerciseService.createExercise(createExerciseRequest);
        RestApiResponse<Void> rest = RestApiResponse.success("Exercise created successfully", null, HttpStatus.CREATED);
        return rest.toResponseEntity();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestApiResponse<Void>> updateExercise(@PathVariable("id") UUID id,
                                                                @Valid @RequestBody UpdateExerciseRequest updateExerciseRequest) {
        exerciseService.updateExercise(id, updateExerciseRequest);
        RestApiResponse<Void> restApiResponse =
                RestApiResponse.success("Exercise updated successfully", null, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<Void>> deleteExercise(@PathVariable("id") UUID id) {
        exerciseService.deleteExercise(id);
        RestApiResponse<Void> rest =
                RestApiResponse.success("Exercise deleted successfully", null, HttpStatus.NO_CONTENT);
        return rest.toResponseEntity();
    }
}
