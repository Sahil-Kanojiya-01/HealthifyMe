package com.example.healthifyme.DTO.response.mapper;

import com.example.healthifyme.DTO.response.FoodResponse;
import com.example.healthifyme.entity.Food;

public class FoodResponseMapper{

    public static FoodResponse mapFromFood(Food food) {
        FoodResponse foodResponse = new FoodResponse();

        foodResponse.setId(food.getId().toString());
        foodResponse.setName(food.getName());
        foodResponse.setCalories(food.getCalories());
        foodResponse.setProtein(food.getProtein());
        foodResponse.setCarbs(food.getCarbs());
        foodResponse.setFat(food.getFat());
        foodResponse.setServingUnit(food.getServingUnit());
        foodResponse.setServingSize(food.getServingSize());

        return foodResponse;
    }
}
