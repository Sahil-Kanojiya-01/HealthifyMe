package com.example.healthifyme.controller;

import com.example.healthifyme.DTO.request.CreateFoodRequest;
import com.example.healthifyme.DTO.request.UpdateFoodRequest;
import com.example.healthifyme.DTO.response.FoodResponse;
import com.example.healthifyme.DTO.response.RestApiResponse;
import com.example.healthifyme.service.FoodService;
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
@RequestMapping("/api/v1/foods")
public class FoodController{

    private final FoodService foodService;

    @GetMapping("/")
    public ResponseEntity<RestApiResponse<List<FoodResponse>>> getFoods() {
        List<FoodResponse> foodResponseList = foodService.getFoods();
        RestApiResponse<List<FoodResponse>> restApiResponse =
            RestApiResponse.success("Foods fetched successfully", foodResponseList, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<FoodResponse>> getFood(@PathVariable("id") UUID id) {
        FoodResponse foodResponse = foodService.getFoodById(id);
        RestApiResponse<FoodResponse> restApiResponse =
            RestApiResponse.success("Food fetched successfully", foodResponse, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @PostMapping("/")
    public ResponseEntity<RestApiResponse<Void>> createFood(@Valid @RequestBody CreateFoodRequest createFoodRequest) {
        foodService.createFood(createFoodRequest);
        RestApiResponse<Void> rest = RestApiResponse.success("Food created successfully", null, HttpStatus.CREATED);
        return rest.toResponseEntity();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestApiResponse<Void>> updateFood(@PathVariable("id") UUID id,
            @Valid @RequestBody UpdateFoodRequest updateFoodRequest) {
        foodService.updateFood(id, updateFoodRequest);
        RestApiResponse<Void> restApiResponse =
            RestApiResponse.success("Food updated successfully", null, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<Void>> deleteFood(@PathVariable("id") UUID id) {
        foodService.deleteFood(id);
        RestApiResponse<Void> rest = RestApiResponse.success("Food deleted successfully", null, HttpStatus.NO_CONTENT);
        return rest.toResponseEntity();
    }
}
