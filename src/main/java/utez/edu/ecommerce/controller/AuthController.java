package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import utez.edu.ecommerce.dto.AuthResponseDTO;
import utez.edu.ecommerce.dto.SellerDataDTO;
import utez.edu.ecommerce.entity.RecoveryToken;
import utez.edu.ecommerce.entity.SellerIdentity;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.repository.UserRepository;
import utez.edu.ecommerce.security.JWTGenerator;
import utez.edu.ecommerce.serviceImpl.AWSS3ServiceImp;
import utez.edu.ecommerce.serviceImpl.EmailService;
import utez.edu.ecommerce.serviceImpl.RecoveryTokenServiceImp;
import utez.edu.ecommerce.serviceImpl.SellerIdentityServiceImpl;
import utez.edu.ecommerce.serviceImpl.UserServiceImpl;
import utez.edu.ecommerce.utils.Message;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserServiceImpl userService;
    private UserRepository userRepository;
    //private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    private RecoveryTokenServiceImp recoveryTokenServiceImp;
    private AWSS3ServiceImp awss3ServiceImp;
    private SellerIdentityServiceImpl sellerIdentityServiceImpl;
    private EmailService emailService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          /*RoleRepository roleRepository,*/ PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, UserServiceImpl userService, RecoveryTokenServiceImp recoveryTokenServiceImp, AWSS3ServiceImp awss3ServiceImp, SellerIdentityServiceImpl sellerIdentityServiceImpl, EmailService emailService ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        //this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
        this.recoveryTokenServiceImp = recoveryTokenServiceImp;
        this.awss3ServiceImp = awss3ServiceImp;
        this.sellerIdentityServiceImpl = sellerIdentityServiceImpl;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userService.getUserByEmail(loginUser.getEmail());
        if (user != null) {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginUser.getEmail(),
                                loginUser.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtGenerator.generateToken(authentication);
                return new ResponseEntity<>(new AuthResponseDTO(token, user, HttpStatus.OK.value()), HttpStatus.OK);
            } catch (AuthenticationException e) {
                // Las credenciales son inválidas
                Message<User> response = new Message<>();
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setMessage("error: credenciales inválidas");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } else {
            // Usuario no encontrado
            Message<User> response = new Message<>();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: usuario no encontrado");
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
    
    @PostMapping("/registerSeller")
    public ResponseEntity<Message<?>> registerSeller(@RequestPart SellerDataDTO seller, @RequestPart("ine") MultipartFile ine) {
        if (userRepository.existsByEmail(seller.getSeller().getEmail())) {
        	
            Message<User> response = new Message<>();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("error: el correo electrónico ya está registrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }else if(ine.isEmpty()) {
        	Message<User> response = new Message<>();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("error: agrega una foto de tu INE");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        seller.getSeller().setPassword(passwordEncoder.encode((seller.getSeller().getPassword())));
        seller.getSeller().setStatus(false);
        User newUser = userRepository.save(seller.getSeller());
        String key = awss3ServiceImp.uploadFile(ine); 
        String urlIne = awss3ServiceImp.getObjectUrl(key);
        sellerIdentityServiceImpl.createUser(new SellerIdentity(0,newUser,seller.getRfc(),urlIne,seller.getShopType(),0.0,true));
        emailService.sendMail(newUser.getEmail(),"Solicitud de venta SaleHub","Tus datos se encuentran en revisión, te mandaremos un correo con el resultado de tu solicitud en las proximas 24 horas...");
        Message<SellerDataDTO> response = new Message<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Se ha enviado tu solicitud de vendedor. Revisa tu correo electrónico");
        response.setData(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    
    @PostMapping("/sendMailToken")
    public ResponseEntity<?> sendMailToken(@RequestBody User email){
        User user = userService.getUserByEmail(email.getEmail());
        if (user != null) {
        	String message = recoveryTokenServiceImp.createRecoveryToken(user);
        	if(message.equals("OK")) {
        		Message<User> response = new Message<>();
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Se ha enviado un token de recuperación a "+email.getEmail());
                return ResponseEntity.status(HttpStatus.OK).body(response);
        	}else {
        		Message<User> response = new Message<>();
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Error: "+ message);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        	}
        } else {
            Message<User> response = new Message<>();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Error: Correo no registrado ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @PostMapping("/changePassword/{token}")
    public ResponseEntity<?> changePassword(@RequestBody User newPassword,@PathVariable int token){
    	User user = userService.getUserByEmail(newPassword.getEmail());
        if (user != null) {
        	RecoveryToken storedToken =  recoveryTokenServiceImp.findByUserAndToken(user, token);
        	if(recoveryTokenServiceImp.existsByUserAndToken(user, token) && storedToken.isStatus()) {
        		user.setPassword(passwordEncoder.encode((newPassword.getPassword())));
        		userService.updateUser(user.getIdUser(), user);
	        	recoveryTokenServiceImp.updateRecoveryToken(storedToken);
	        	Message<User> response = new Message<>();
	            response.setStatus(HttpStatus.OK.value());
	            response.setMessage("Contraseña actualizada!");
	            return ResponseEntity.status(HttpStatus.OK).body(response);
        	}else {
        		Message<User> response = new Message<>();
	            response.setStatus(HttpStatus.UNAUTHORIZED.value());
	            response.setMessage("El token es incorrecto...");
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        	}
        } else {
            Message<User> response = new Message<>();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Error: Correo no registrado ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    
    
    
}