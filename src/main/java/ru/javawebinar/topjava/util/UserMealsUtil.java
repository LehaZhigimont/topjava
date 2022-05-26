package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(5, 0), LocalTime.of(13, 0), 2000);
        mealsTo.forEach(System.out::println);

        //System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }


    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        Map<Integer, Integer> caloriesAllDay = new HashMap<>();
        int countCalories;
        boolean boolExcess;

        for (UserMeal userMeal : meals) {
            if (!caloriesAllDay.containsKey(userMeal.getDateTime().getDayOfMonth())) {
                caloriesAllDay.put(userMeal.getDateTime().getDayOfMonth(), userMeal.getCalories());
            } else if (caloriesAllDay.containsKey(userMeal.getDateTime().getDayOfMonth())) {
                countCalories = caloriesAllDay.get(userMeal.getDateTime().getDayOfMonth()) + userMeal.getCalories();
                caloriesAllDay.put(userMeal.getDateTime().getDayOfMonth(), countCalories);
            }
        }

        for (UserMeal userMeal : meals) {
            if (TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                boolExcess = caloriesPerDay < caloriesAllDay.get(userMeal.getDateTime().getDayOfMonth());
                userMealWithExcesses.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), boolExcess));
            }
        }
        return userMealWithExcesses;
    }


    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> userMealWithExcess = new ArrayList<>();
        //TODO
        return userMealWithExcess;
    }
}