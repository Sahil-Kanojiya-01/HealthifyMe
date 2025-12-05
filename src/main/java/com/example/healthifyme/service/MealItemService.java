package com.example.healthifyme.service;

import com.example.healthifyme.DTO.response.MealItemResponse;
import com.example.healthifyme.DTO.response.MealResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class MealItemService{


    public MealItemResponse getOwnMealItemById(UUID id) {
        return null;
    }

    public List<MealResponse> getOwnMealsByDate(UUID id, LocalDate date) {
        return null;
    }
}
