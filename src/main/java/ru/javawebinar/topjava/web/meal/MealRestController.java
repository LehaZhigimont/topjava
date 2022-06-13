package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private MealService service;

    public MealRestController(MealService mealService){
        this.service = mealService;
    }

    public Meal save(Meal meal) {
        log.info("save {}", meal);
        return service.save(meal);
    }

    public boolean delete(int id) {
        log.info("delete {}", id);
        if (service.get(id).getUserId().equals(authUserId())) {
            return service.delete(id);
        }
        return false;
    }

    public Meal get(int id) {
        log.info("get {}", id);
        if (service.get(id).getUserId().equals(authUserId()))
            return service.get(id);
        return null;
    }

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll().stream()
                .filter(meal -> meal.getUserId().equals(authUserId()))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}