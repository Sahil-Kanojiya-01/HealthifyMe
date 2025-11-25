package com.example.healthifyme.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meal_items")
public class MealItem{

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    private Double quantity; // Multiplier of serving size or grams

    public Double getCalories() {
        if (food != null && quantity != null) {
            return food.getCalories() * quantity;
        }
        return 0.0;
    }
}
