package com.example.healthifyme.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodResponse{
    private String id;
    private String name;
    private Double calories; // per 100g or per serving
    private Double protein;
    private Double carbs;
    private Double fat;
    private String servingUnit; // g, ml, piece
    private Double servingSize; // default serving size
}
