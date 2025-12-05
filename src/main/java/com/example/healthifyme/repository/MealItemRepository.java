package com.example.healthifyme.repository;

import com.example.healthifyme.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MealItemRepository extends JpaRepository<Meal, UUID> {
}
