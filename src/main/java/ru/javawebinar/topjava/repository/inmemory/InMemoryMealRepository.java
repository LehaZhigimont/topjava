package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            repository.put(meal.getUserId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }


    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll(int id) {
        log.info("getAll");
        List<Meal> mealsByUser = new ArrayList<>(repository.values());
        mealsByUser.stream().filter(m -> m.getUserId().equals(id)).collect(Collectors.toList());
        for (Meal m : mealsByUser) {
            if (m.getUserId().equals(id)) {
                System.out.println(m);
            }
        }
        mealsByUser.stream().filter(meal -> meal.getUserId().equals(id)).map(this::createTo).collect(Collectors.toList());
        System.out.println(mealsByUser);
        return mealsByUser;
    }

    private Meal createTo(Meal meal) {
        return new Meal(meal.getId(), meal.getUserId(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
    }
}

