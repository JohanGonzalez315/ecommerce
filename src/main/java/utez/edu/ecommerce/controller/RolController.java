package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.ecommerce.entity.Rol;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.service.RolService;
import utez.edu.ecommerce.utils.Message;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/users")
    public ResponseEntity<Message<List<User>>> getUsersByRoleName(@RequestParam String roleName) {
        List<User> users = rolService.getUsersByRoleName(roleName);
        Message<List<User>> response = new Message<>();

        if (users != null && !users.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(users);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no users found with the given role name");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
