package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.service.UserService;
import utez.edu.ecommerce.utils.Message;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Message<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        Message<List<User>> response = new Message<>();

        if (users != null && !users.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(users);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no users found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity<Message<User>> getUserById(@PathVariable long userId) {
        User user = userService.getUserById(userId);
        Message<User> response = new Message<>();

        if (user != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(user);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: user not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Message<User>> createUser(@RequestBody User user) {

        if (userService.existsByEmail(user.getEmail())) {
            Message<User> response = new Message<>();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("error: el correo electrónico ya está registrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        User createdUser = userService.createUser(user);
        Message<User> response = new Message<>();

        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Message<User>> loginUser(@RequestBody User loginUser) {
        User user = userService.getUserByEmail(loginUser.getEmail());
        Message<User> response = new Message<>();

        if (user != null && BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(user);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("error: usuario no autorizado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<Message<User>> updateUser(@PathVariable long userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        Message<User> response = new Message<>();

        if (updatedUser != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(updatedUser);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: user not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

   /* @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }*/
}
