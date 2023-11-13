package utez.edu.ecommerce.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utez.edu.ecommerce.dto.AuthResponseDTO;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.repository.UserRepository;
import utez.edu.ecommerce.security.JWTGenerator;
import utez.edu.ecommerce.service.UserService;
import utez.edu.ecommerce.utils.Message;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private final UserService userService;
    private UserRepository userRepository;
    //private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          /*RoleRepository roleRepository,*/ PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        //this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser){
        User user = userService.getUserByEmail(loginUser.getEmail());
        if (user != null) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getEmail(),
                            loginUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDTO(token, user), HttpStatus.OK);
        } else {
            Message<User> response = new Message<>();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: user not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }


    @PostMapping("/register")
    public ResponseEntity<Message<User>> register(@RequestBody User registerUser) {
        if (userRepository.existsByEmail(registerUser.getEmail())) {
            Message<User> response = new Message<>();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("error: el correo electrónico ya está registrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        registerUser.setPassword(passwordEncoder.encode((registerUser.getPassword())));
        userRepository.save(registerUser);

        Message<User> response = new Message<>();

        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(registerUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}