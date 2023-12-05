package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.ecommerce.entity.SellerIdentity;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.service.SellerIdentityService;
import utez.edu.ecommerce.service.UserService;
import utez.edu.ecommerce.serviceImpl.EmailService;
import utez.edu.ecommerce.utils.Message;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerIdentityController {


    private final SellerIdentityService sellerIdentityService;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public SellerIdentityController(SellerIdentityService sellerIdentityService, UserService userService, EmailService emailService) {
        this.sellerIdentityService = sellerIdentityService;
        this.userService = userService;
        this.emailService = emailService;
    }


    @GetMapping
    public ResponseEntity<Message<List<SellerIdentity>>> getAllSellers() {
        Message<List<SellerIdentity>> response = new Message<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("success");
        response.setData(sellerIdentityService.getAllSellers());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<Message<SellerIdentity>> getSellerById(@PathVariable long sellerId) {
        SellerIdentity seller = sellerIdentityService.getSellerById(sellerId);
        if (seller != null) {
            Message<SellerIdentity> response = new Message<>();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(seller);
            return ResponseEntity.ok(response);
        } else {
            Message<SellerIdentity> response = new Message<>();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: seller not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Message<SellerIdentity>> createSeller(@RequestBody SellerIdentity sellerIdentity) {
        long userId = sellerIdentity.getUser().getIdUser();
        Message<SellerIdentity> response = new Message<>();

        if (!userService.userExistsById(userId)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: No se encontró un usuario con este userId.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if (sellerIdentityService.existsByUserId(userId)) {
            response.setStatus(HttpStatus.CONFLICT.value());
            response.setMessage("error: Ya existe un vendedor asociado a este usuario.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        SellerIdentity createdSeller = sellerIdentityService.createUser(sellerIdentity);
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(createdSeller);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @PutMapping("/{sellerId}")
    public ResponseEntity<Message<SellerIdentity>> updateSeller(@PathVariable long sellerId, @RequestBody SellerIdentity sellerIdentity) {
        SellerIdentity updatedSeller = sellerIdentityService.updateUser(sellerId, sellerIdentity);
        Message<SellerIdentity> response = new Message<>();

        if (updatedSeller != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Success");
            response.setData(updatedSeller);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Seller not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    
    @PutMapping("applicationResponse/{sellerId}")
    public ResponseEntity<Message<?>> applicationResponse(@PathVariable long sellerId, @RequestParam boolean status) {
    	SellerIdentity seller = sellerIdentityService.getSellerById(sellerId);
    	seller.setStatus(status);
        SellerIdentity updatedSeller = sellerIdentityService.updateUser(sellerId, seller);
        User user = userService.getUserById(seller.getUser().getIdUser());
        user.setStatus(status);
        userService.updateUser(user.getIdUser(), user);
    	if(status) {
    		emailService.sendMail(seller.getUser().getEmail(),"¡Bienvenido a SaleHub!", "¡" +seller.getUser().getName() +" "+seller.getUser().getLastname() +" has sido aceptado para formar parte de nuestros vendedores! \n\n Inicia sesión en tu cuenta con tu correo y contraseña previamente registrados");
    	}else {
    		emailService.sendMail(seller.getUser().getEmail(),"¡Solicitud Rechazada!", seller.getUser().getName() +" "+seller.getUser().getLastname() +" Lamentamos informarte que el equipo de SaleHub ha determinado que no eres candidato a ser vendedor en nuestra plataforma... \n\n Contáctanos en: notification.salehub@gmail.com");
    	}
    	
        Message<SellerIdentity> response = new Message<>();

        if (updatedSeller != null) {
            response.setStatus(HttpStatus.OK.value());
            if(status) {
            	response.setMessage("Solicitud aceptada!");
            }else {
            	response.setMessage("Solicitud rechazada!");
            }
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Seller not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @DeleteMapping("/{sellerId}")
    public ResponseEntity<Void> deleteSeller(@PathVariable long sellerId) {
        sellerIdentityService.deleteUser(sellerId);
        return ResponseEntity.noContent().build();
    }
}
