package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean existsByEmail(String email);
    List<User> getAllUsers();
    User getUserById(long userId);
    User createUser(User user);
    User updateUser(long userId, User user);
    Optional<User> findByEmail(String email);
    boolean userExistsById(long userId);
    User authenticateUser(String email, String password);
    void deleteUser(long userId);
}
