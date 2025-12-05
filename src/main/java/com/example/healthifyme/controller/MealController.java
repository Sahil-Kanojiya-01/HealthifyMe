package com.example.healthifyme.controller;

//### MealController
//**Base Path:** `/api/v1/meals`
//- `GET /api/v1/meals/{id}` - Get meal by ID
//- `GET /api/v1/meals/user/{userId}` - Get meals by user
//- `GET /api/v1/meals/user/{userId}/date/{date}` - Get meals by user and date
//- `GET /api/v1/meals/type/{type}` - Get meals by type
//- `POST /api/v1/meals` - Create new meal
//- `PUT /api/v1/meals/{id}` - Update meal
//- `DELETE /api/v1/meals/{id}` - Delete meal
//- `GET /api/v1/meals/{id}/calories` - Get total calories for meal

import com.example.healthifyme.DTO.request.CreateMealRequest;
import com.example.healthifyme.DTO.request.UpdateMealRequest;
import com.example.healthifyme.DTO.response.MealResponse;
import com.example.healthifyme.DTO.response.RestApiResponse;
import com.example.healthifyme.security.UserDetailsImpl;
import com.example.healthifyme.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/meals")
public class MealController {

    private final MealService mealService;

    @GetMapping("/{id}/self")
    public ResponseEntity<RestApiResponse<MealResponse>> getOwnMealById(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable("id") UUID id) {
        MealResponse mealResponse = mealService.getOwnMealById(userDetails.getId(),id);
        RestApiResponse<MealResponse> restApiResponse = RestApiResponse.success("Meal fetched successfully", mealResponse, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @GetMapping("/self/date")
    public ResponseEntity<RestApiResponse<List<MealResponse>>> getOwnMealsByDate(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam LocalDate date) {
        List<MealResponse> mealResponseList = mealService.getOwnMealsByDate(userDetails.getId(), date);
        RestApiResponse<List<MealResponse>> restApiResponse = RestApiResponse.success("Meals fetched successfully", mealResponseList, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }


    @GetMapping("/self/dates")
    public ResponseEntity<RestApiResponse<List<MealResponse>>> getOwnMealsByDateRange(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<MealResponse> mealResponseList = mealService.getOwnMealsByStartAndEndDate(userDetails.getId(), startDate, endDate);
        RestApiResponse<List<MealResponse>> restApiResponse = RestApiResponse.success("Meals fetched successfully", mealResponseList, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @PostMapping("/")
    public ResponseEntity<RestApiResponse<Void>> createMeal(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody CreateMealRequest createMealRequest) {
        mealService.createMeal(userDetails.getId(), createMealRequest);
        RestApiResponse<Void> restApiResponse = RestApiResponse.success("Meal created successfully", null, HttpStatus.CREATED);
        return restApiResponse.toResponseEntity();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestApiResponse<Void>> updateMeal(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") UUID id, @Valid @RequestBody UpdateMealRequest updateMealRequest) {
        mealService.updateMeal(userDetails.getId(), id, updateMealRequest);
        RestApiResponse<Void> restApiResponse =
                RestApiResponse.success("Meal updated successfully", null, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<Void>> deleteMeal(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") UUID id) {
        mealService.deleteMeal(userDetails.getId(), id);
        RestApiResponse<Void> rest = RestApiResponse.success("Meal deleted successfully", null, HttpStatus.NO_CONTENT);
        return rest.toResponseEntity();
    }
}
