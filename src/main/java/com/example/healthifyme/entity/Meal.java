package com.example.healthifyme.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private MealType type; // BREAKFAST, LUNCH, SNACK, DINNER

    @OneToMany(mappedBy = "meal")
    private List<MealItem> items = new ArrayList<>();

    public enum MealType {
        BREAKFAST, LUNCH, SNACK, DINNER
    }

    public Double getTotalCalories() {
        return items.stream().map(MealItem::getFood).mapToDouble(Food::getCalories).sum();
    }
}
