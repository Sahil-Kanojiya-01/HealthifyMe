package com.example.healthifyme.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealItemResponse {

    private String id;
    private String mealId;
    private String foodId;
    private String foodName;
    private Double quantity;
    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fat;
    private String servingUnit; // g, ml, piece
    private Double servingSize; // default serving size
}
