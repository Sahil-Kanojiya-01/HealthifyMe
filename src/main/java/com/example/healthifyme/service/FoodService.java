package com.example.healthifyme.service;

import com.example.healthifyme.DTO.request.CreateFoodRequest;
import com.example.healthifyme.DTO.request.UpdateFoodRequest;
import com.example.healthifyme.DTO.response.FoodResponse;
import com.example.healthifyme.DTO.response.mapper.FoodResponseMapper;
import com.example.healthifyme.entity.Food;
import com.example.healthifyme.exception.EntityNotFoundException;
import com.example.healthifyme.repository.FoodRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FoodService{

    private final FoodRepository foodRepository;

    @Transactional(readOnly = true)
    public List<FoodResponse> getFoods() {
        List<Food> foodList = foodRepository.findAll();
        return foodList.stream().map(FoodResponseMapper::mapFromFood).toList();
    }

    @Transactional(readOnly = true)
    public FoodResponse getFoodById(UUID id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(String.format("Food not found with id(%s)", id)));
        return FoodResponseMapper.mapFromFood(food);
    }

    @Transactional
    public void createFood(@Valid CreateFoodRequest createFoodRequest) {
        Food food = new Food();

        food.setName(createFoodRequest.getName());
        food.setCalories(createFoodRequest.getCalories());
        food.setProtein(createFoodRequest.getProtein());
        food.setCarbs(createFoodRequest.getCarbs());
        food.setFat(createFoodRequest.getFat());
        food.setServingUnit(createFoodRequest.getServingUnit());
        food.setServingSize(createFoodRequest.getServingSize());

        foodRepository.save(food);
    }

    @Transactional
    public void updateFood(UUID id, @Valid UpdateFoodRequest updateFoodRequest) {
        Optional<Food> optionalFood = foodRepository.findById(id);

        if (optionalFood.isPresent()) {
            Food food = optionalFood.get();

            food.setName(updateFoodRequest.getName());
            food.setCalories(updateFoodRequest.getCalories());
            food.setProtein(updateFoodRequest.getProtein());
            food.setCarbs(updateFoodRequest.getCarbs());
            food.setFat(updateFoodRequest.getFat());
            food.setServingUnit(updateFoodRequest.getServingUnit());
            food.setServingSize(updateFoodRequest.getServingSize());
        }
    }

    @Transactional
    public void deleteFood(UUID id) {
        foodRepository.deleteById(id);
    }
}
