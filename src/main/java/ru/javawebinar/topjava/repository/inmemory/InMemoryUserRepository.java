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
    private final AtomicInteger counter = new AtomicInteger(1);
    private final Map<Integer, User> repository = new ConcurrentHashMap<>();
    static Set<Role> roles = new HashSet<>();

    {
        roles.add(Role.ADMIN);
        repository.put(0, new User(0, "Admin", "Admin@admin.by", "Admin", 20000, true, roles));
        roles.add(Role.USER);
        repository.put(1, new User(1, "User", "User@user.by", "User", 20000, false, roles));
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
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> sortedUsersList = new ArrayList<>(repository.values());
        sortedUsersList.sort(Comparator.comparing(User::getName).thenComparing(User::getEmail));
        return repository.isEmpty() ? null : sortedUsersList;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        System.out.println("InMemoruUserRepo - getByEmail");
        System.out.println(repository.values().stream().filter(user -> user.getEmail().contains(email)).findFirst().orElse(null));
        return repository.values().stream().filter(user -> user.getEmail().contains(email)).findFirst().orElse(null);
    }
}
