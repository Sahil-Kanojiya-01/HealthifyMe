package com.example.healthifyme.service;

import com.example.healthifyme.DTO.request.CreateMealRequest;
import com.example.healthifyme.DTO.request.UpdateMealRequest;
import com.example.healthifyme.DTO.response.MealResponse;
import com.example.healthifyme.DTO.response.mapper.MealResponseMapper;
import com.example.healthifyme.entity.Meal;
import com.example.healthifyme.exception.EntityNotFoundException;
import com.example.healthifyme.repository.MealRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class MealService {

    private final MealRepository mealRepository;

    @Transactional(readOnly = true)
    public MealResponse getOwnMealById(UUID userId, UUID id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Meal not found with id(%s)", id)));
        return MealResponseMapper.mapFromMeal(meal);
    }

    @Transactional(readOnly = true)
    public List<MealResponse> getOwnMealsByDate(UUID id, LocalDate date) {
        List<Meal> mealList = mealRepository.findByUserIdAndDate(id, date);
        return mealList.stream().map(MealResponseMapper::mapFromMeal).toList();
    }

    @Transactional(readOnly = true)
    public List<MealResponse> getOwnMealsByStartAndEndDate(UUID id, LocalDate start, LocalDate end) {
        List<Meal> mealList = mealRepository.findByUserIdAndDateBetweenOrderByDateAsc(id, start, end);
        return mealList.stream().map(MealResponseMapper::mapFromMeal).toList();
    }

    @Transactional
    public void createMeal(UUID userId, CreateMealRequest createMealRequest) {
        Meal meal = new Meal();
        mealRepository.save(meal);
    }

    @Transactional
    public void updateMeal(UUID userId, UUID id, UpdateMealRequest updateMealRequest) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Meal not found with id(%s)", id)));
    }

    @Transactional
    public void deleteMeal(UUID userId, UUID id) {
    }
}
