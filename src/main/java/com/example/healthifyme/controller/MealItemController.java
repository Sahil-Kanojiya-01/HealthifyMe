package com.example.healthifyme.controller;

//### MealItemController
//**Base Path:** `/api/v1/meal-items`
//- `GET /api/v1/meal-items/{id}` - Get meal item by ID
//- `GET /api/v1/meal-items/meal/{mealId}` - Get meal items by meal
//- `POST /api/v1/meal-items` - Add food item to meal
//- `PUT /api/v1/meal-items/{id}` - Update meal item
//- `DELETE /api/v1/meal-items/{id}` - Delete meal item

import com.example.healthifyme.DTO.request.CreateMealRequest;
import com.example.healthifyme.DTO.request.UpdateMealRequest;
import com.example.healthifyme.DTO.response.MealItemResponse;
import com.example.healthifyme.DTO.response.MealResponse;
import com.example.healthifyme.DTO.response.RestApiResponse;
import com.example.healthifyme.entity.MealItem;
import com.example.healthifyme.security.UserDetailsImpl;
import com.example.healthifyme.service.MealItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/meal-items")
public class MealItemController{

    private final MealItemService mealItemService;

    @GetMapping("/{id}/self")
    public ResponseEntity<RestApiResponse<MealItemResponse>> getOwnMealItemById(@PathVariable("id") UUID id) {
        MealItemResponse mealItemResponse = mealItemService.getOwnMealItemById(id);
        RestApiResponse<MealItemResponse> restApiResponse = RestApiResponse.success("MealItem fetched successfully", mealItemResponse, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @GetMapping("/self/date")
    public ResponseEntity<RestApiResponse<List<MealResponse>>> getOwnMealsByDate(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam LocalDate date) {
        List<MealResponse> mealResponseList = mealItemService.getOwnMealsByDate(userDetails.getId(), date);
        RestApiResponse<List<MealResponse>> restApiResponse = RestApiResponse.success("Meals fetched successfully", mealResponseList, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

//    @GetMapping("/self/dates")
//    public ResponseEntity<RestApiResponse<List<MealResponse>>> getOwnMealsByDateRange(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
//        List<MealResponse> mealResponseList = mealService.getOwnMealsByStartAndEndDate(userDetails.getId(), startDate, endDate);
//        RestApiResponse<List<MealResponse>> restApiResponse = RestApiResponse.success("Meals fetched successfully", mealResponseList, HttpStatus.OK);
//        return restApiResponse.toResponseEntity();
//    }

//    @PostMapping("/")
//    public ResponseEntity<RestApiResponse<Void>> createMeal(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody CreateMealRequest createMealRequest) {
//        mealService.createMeal(userDetails.getId(), createMealRequest);
//        RestApiResponse<Void> restApiResponse = RestApiResponse.success("Meal created successfully", null, HttpStatus.CREATED);
//        return restApiResponse.toResponseEntity();
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<RestApiResponse<Void>> updateMeal(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") UUID id, @Valid @RequestBody UpdateMealRequest updateMealRequest) {
//        mealService.updateMeal(userDetails.getId(), id, updateMealRequest);
//        RestApiResponse<Void> restApiResponse =
//                RestApiResponse.success("Meal updated successfully", null, HttpStatus.OK);
//        return restApiResponse.toResponseEntity();
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<RestApiResponse<Void>> deleteMeal(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") UUID id) {
//        mealService.deleteMeal(userDetails.getId(), id);
//        RestApiResponse<Void> rest = RestApiResponse.success("Meal deleted successfully", null, HttpStatus.NO_CONTENT);
//        return rest.toResponseEntity();
//    }

}
