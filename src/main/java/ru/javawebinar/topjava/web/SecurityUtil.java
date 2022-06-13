package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    private static int authUser = -1;

    public static int authUserId() {
        return authUser;
    }
    public static void setAuthUserId(int id) {
        authUser = id;
    }
    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}





