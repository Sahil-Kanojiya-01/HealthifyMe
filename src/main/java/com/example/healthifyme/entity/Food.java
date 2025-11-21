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
@Table(name = "foods")
public class Food{

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fat;
    private String servingUnit;
    private Double servingSize;
}
