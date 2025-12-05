package com.example.healthifyme.DTO.response;

import com.example.healthifyme.entity.Meal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealResponse {

    private String id;
    private String userId;
    private String goalId;
    private LocalDate date;
    private Meal.MealType type;
    private List<MealItemResponse> items;
    private Double totalCalories;
    private Double totalProtein;
    private Double totalCarbs;
    private Double totalFat;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MealItemResponse {
        private String id;
        private String foodName;
        private Double quantity;
    }
}
