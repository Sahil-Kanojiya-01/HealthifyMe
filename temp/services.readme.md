# Services Documentation

This document provides a comprehensive overview of all services in the HealthifyMe application and their methods.

---

## AuthService

**Package:** `com.example.healthifyme.service`

**Description:** Handles user authentication and registration operations.

**Dependencies:**
- `UserRepository` - Repository for user data access
- `PasswordEncoder` - For password encryption

### Methods

#### `register(String email, String password): void`
- **Type:** `public void`
- **Parameters:**
  - `email` (String) - User's email address
  - `password` (String) - User's plain text password
- **Returns:** `void`
- **Throws:** `UserAlreadyExistsException` - If user with the email already exists
- **Description:** Registers a new user in the system. Validates that the email doesn't already exist, encrypts the password, and saves the user to the database.
- **Logging:** 
  - INFO: "Attempting to register user with email: {email}"
  - INFO: "User registered successfully with email: {email}"

---

## Future Services

Based on the entity analysis, the following services are expected to be implemented:

### ProfileService
- Manage user profiles
- Calculate BMI
- Update profile information (age, weight, height, gender, activity level)
- Link profiles to goals

### GoalService
- Create and manage user goals
- Track goal progress (target value vs current value)
- Update goal status
- Link goals to workouts and meals

### WorkoutService
- Create and manage workouts
- Track workout duration
- Link workouts to activity logs
- Associate workouts with user goals

### ActivityLogService
- Log exercise activities within workouts
- Track sets, reps, weight, distance, and duration
- Link activities to exercises

### ExerciseService
- Manage exercise catalog
- CRUD operations for exercises
- Filter by muscle group and equipment type

### MealService
- Create and manage meals
- Track meals by type (BREAKFAST, LUNCH, SNACK, DINNER)
- Calculate total calories for meals
- Link meals to meal items

### MealItemService
- Add food items to meals
- Track quantity of food items
- Calculate calories for individual meal items

### FoodService
- Manage food catalog
- CRUD operations for foods
- Track nutritional information (calories, protein, carbs, fat)