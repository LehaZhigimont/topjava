package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    private static int authUser = -1;

    public static int authUserId() {
        return getAuthUser() ;
    }

    public static int getAuthUser() {
        return authUser;
    }

    public static void loginUser(int id) {
        authUser = id;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}





