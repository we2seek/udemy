
package com.we2seek.restful_web_services.service;

import com.we2seek.restful_web_services.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * UserDAOService provides methods to manage user data.
 * It simulates a data access object (DAO) for user operations.
 */
//@Service
public class UserDAOService {
    private static final Logger log = LoggerFactory.getLogger(UserDAOService.class);

    private static Predicate<User> findUserPredicate(int id) {
        return (User user) -> user.getId() == id;
    }

    private static final List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "John Doe", LocalDate.of(1984, Month.OCTOBER, 15), null));
        users.add(new User(++usersCount, "Jane Smith", LocalDate.of(1990, Month.JANUARY, 20), null));
        log.info("Initialized user data: {}", users);
    }

    // getUserById method to retrieve a user by ID
    public User getUserById(int id) {
        return users.stream()
                .filter(findUserPredicate(id))
                .findAny()
                .orElse(null);
    }

    // getAllUsers method to retrieve all users
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // addUser method to add a new user
    public User addUser(User user) {
        users.add(user);
        log.info("Added new user: {}", user);
        return user;
    }

    // deleteUser method to delete a user by ID
    public boolean deleteUser(int id) {
        boolean removed = users.removeIf(findUserPredicate(id));
        if (removed) {
            log.info("Deleted user with ID: {}", id);
        } else {
            log.warn("User with ID: {} not found for deletion", id);
        }
        return removed;
    }

    // updateUser method to update an existing user
    // import org.springframework.util.Assert;
    // Import Assert for input validation
    public User updateUser(int id, User updatedUser) {
        Assert.notNull(updatedUser, "Updated user cannot be null");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId() == id) {
                users.set(i, updatedUser);
                log.info("Updated user with ID: {} to {}", id, updatedUser);
                return updatedUser;
            }
        }
        log.warn("User with ID: {} not found for update", id);
        return null;
    }
}   