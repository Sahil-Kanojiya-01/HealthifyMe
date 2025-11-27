package com.example.healthifyme.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFoodRequest{
    @NotBlank(message = "Food name cannot be blank")
    private String name;

    @NotNull(message = "Calories must be provided")
    @Positive(message = "Calories must be positive")
    private Double calories; // per 100g or per serving

    @NotNull(message = "Protein value must be provided")
    @PositiveOrZero(message = "Protein cannot be negative")
    private Double protein;

    @NotNull(message = "Carbs value must be provided")
    @PositiveOrZero(message = "Carbs cannot be negative")
    private Double carbs;

    @NotNull(message = "Fat value must be provided")
    @PositiveOrZero(message = "Fat cannot be negative")
    private Double fat;

    @NotBlank(message = "Serving unit cannot be blank")
    private String servingUnit; // g, ml, piece

    @NotNull(message = "Serving size must be provided")
    @Positive(message = "Serving size must be positive")
    private Double servingSize; // default serving size
}
