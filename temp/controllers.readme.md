# Controllers Documentation

This document provides a comprehensive overview of all REST controllers and their endpoints in the HealthifyMe application.

## Future Controllers

Based on the entity analysis, the following controllers are expected to be implemented:

### GoalController
**Base Path:** `/api/v1/goals`
- `GET /api/v1/goals/{id}` - Get goal by ID
- `GET /api/v1/goals/profile/{profileId}` - Get goals by profile
- `POST /api/v1/goals` - Create new goal
- `PUT /api/v1/goals/{id}` - Update goal
- `DELETE /api/v1/goals/{id}` - Delete goal
- `PATCH /api/v1/goals/{id}/progress` - Update goal progress

### WorkoutController
**Base Path:** `/api/v1/workouts`
- `GET /api/v1/workouts/{id}` - Get workout by ID
- `GET /api/v1/workouts/user/{userId}` - Get workouts by user
- `POST /api/v1/workouts` - Create new workout
- `PUT /api/v1/workouts/{id}` - Update workout
- `DELETE /api/v1/workouts/{id}` - Delete workout
- `PATCH /api/v1/workouts/{id}/complete` - Mark workout as complete
- `GET /api/v1/workouts/{id}/duration` - Get workout duration

### ActivityLogController
**Base Path:** `/api/v1/activity-logs`
- `GET /api/v1/activity-logs/{id}` - Get activity log by ID
- `GET /api/v1/activity-logs/workout/{workoutId}` - Get activity logs by workout
- `POST /api/v1/activity-logs` - Create new activity log
- `PUT /api/v1/activity-logs/{id}` - Update activity log
- `DELETE /api/v1/activity-logs/{id}` - Delete activity log

### ExerciseController
**Base Path:** `/api/v1/exercises`
- `GET /api/v1/exercises` - Get all exercises
- `GET /api/v1/exercises/{id}` - Get exercise by ID
- `GET /api/v1/exercises/muscle-group/{muscleGroup}` - Filter by muscle group
- `GET /api/v1/exercises/equipment/{equipment}` - Filter by equipment
- `POST /api/v1/exercises` - Create new exercise
- `PUT /api/v1/exercises/{id}` - Update exercise
- `DELETE /api/v1/exercises/{id}` - Delete exercise

### MealController
**Base Path:** `/api/v1/meals`
- `GET /api/v1/meals/{id}` - Get meal by ID
- `GET /api/v1/meals/user/{userId}` - Get meals by user
- `GET /api/v1/meals/user/{userId}/date/{date}` - Get meals by user and date
- `GET /api/v1/meals/type/{type}` - Get meals by type
- `POST /api/v1/meals` - Create new meal
- `PUT /api/v1/meals/{id}` - Update meal
- `DELETE /api/v1/meals/{id}` - Delete meal
- `GET /api/v1/meals/{id}/calories` - Get total calories for meal

### MealItemController
**Base Path:** `/api/v1/meal-items`
- `GET /api/v1/meal-items/{id}` - Get meal item by ID
- `GET /api/v1/meal-items/meal/{mealId}` - Get meal items by meal
- `POST /api/v1/meal-items` - Add food item to meal
- `PUT /api/v1/meal-items/{id}` - Update meal item
- `DELETE /api/v1/meal-items/{id}` - Delete meal item

### FoodController
**Base Path:** `/api/v1/foods`
- `GET /api/v1/foods` - Get all foods
- `GET /api/v1/foods/{id}` - Get food by ID
- `GET /api/v1/foods/search?name={name}` - Search foods by name
- `POST /api/v1/foods` - Create new food
- `PUT /api/v1/foods/{id}` - Update food
- `DELETE /api/v1/foods/{id}` - Delete food