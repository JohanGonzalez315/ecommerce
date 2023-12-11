package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.User;
import java.util.List;

public interface UserService {

    boolean existsByEmail(String email);
    List<User> getAllUsers();
    User getUserById(long userId);
    User createUser(User user);

    User getUserByEmail(String email);
    User updateUser(long userId, User user);
    User findByEmail(String email);
    boolean userExistsById(long userId);
    User authenticateUser(String email, String password);
    void deleteUser(long userId);
    long countUsersByRoleId(long roleId);

}
