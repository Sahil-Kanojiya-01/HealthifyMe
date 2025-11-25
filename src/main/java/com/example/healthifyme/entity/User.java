package com.example.healthifyme.entity;

import jakarta.persistence.*;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @OneToMany(mappedBy = "user")
    private List<Workout> workouts;

    @OneToMany(mappedBy = "user")
    private List<Meal> meals;
}