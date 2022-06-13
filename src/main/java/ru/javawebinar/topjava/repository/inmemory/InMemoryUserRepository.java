package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<Integer, User> repository = new ConcurrentHashMap<>();

    {
        this.save(new User(null, "Admin", "admin@admin.by", "admin", 20000, true, Arrays.asList(Role.ADMIN, Role.USER)));
        this.save(new User(null, "User", "User@user.by", "user", 20000, false, Arrays.asList(Role.USER)));
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldMeal) -> user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id)!= null ? repository.get(id) : null;
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> sortedUsersList = new ArrayList<>(repository.values());
        sortedUsersList.sort(Comparator.comparing(User::getName).thenComparing(User::getEmail));
        return sortedUsersList;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream()
                .filter(user -> user.getEmail().contains(email)).findFirst()
                .orElse(null);
    }
}
