package com.example.healthifyme.controller;

import com.example.healthifyme.DTO.response.FoodResponse;
import com.example.healthifyme.DTO.response.RestApiResponse;
import com.example.healthifyme.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/foods")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/")
    public ResponseEntity<RestApiResponse<Page<FoodResponse>>> getFoods(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size) {
        Page<FoodResponse> foodResponsePage = foodService.getFoods(page, size);
        RestApiResponse<Page<FoodResponse>> restApiResponse =
                RestApiResponse.success("Foods fetched successfully", foodResponsePage, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<FoodResponse>> getFood(@PathVariable("id") UUID id) {
        FoodResponse foodResponse = foodService.getFoodById(id);
        RestApiResponse<FoodResponse> restApiResponse =
                RestApiResponse.success("Food fetched successfully", foodResponse, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/")
//    public ResponseEntity<RestApiResponse<Void>> createFood(@Valid @RequestBody CreateFoodRequest createFoodRequest) {
//        foodService.createFood(createFoodRequest);
//        RestApiResponse<Void> rest = RestApiResponse.success("Food created successfully", null, HttpStatus.CREATED);
//        return rest.toResponseEntity();
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/{id}")
//    public ResponseEntity<RestApiResponse<Void>> updateFood(@PathVariable("id") UUID id,
//                                                            @Valid @RequestBody UpdateFoodRequest updateFoodRequest) {
//        foodService.updateFood(id, updateFoodRequest);
//        RestApiResponse<Void> restApiResponse =
//                RestApiResponse.success("Food updated successfully", null, HttpStatus.OK);
//        return restApiResponse.toResponseEntity();
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<RestApiResponse<Void>> deleteFood(@PathVariable("id") UUID id) {
//        foodService.deleteFood(id);
//        RestApiResponse<Void> rest = RestApiResponse.success("Food deleted successfully", null, HttpStatus.NO_CONTENT);
//        return rest.toResponseEntity();
//    }
}
