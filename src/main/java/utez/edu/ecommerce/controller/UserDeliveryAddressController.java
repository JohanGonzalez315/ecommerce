package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.entity.UserDeliveryAddress;
import utez.edu.ecommerce.service.UserDeliveryAddressService;
import utez.edu.ecommerce.utils.Message;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class UserDeliveryAddressController {

    private final UserDeliveryAddressService userDeliveryAddressService;

    @Autowired
    public UserDeliveryAddressController(UserDeliveryAddressService userDeliveryAddressService) {
        this.userDeliveryAddressService = userDeliveryAddressService;
    }

    @GetMapping
    public ResponseEntity<Message<List<UserDeliveryAddress>>> getAllUserDeliveryAddresses() {
        List<UserDeliveryAddress> addresses = userDeliveryAddressService.getAllUserDeliveryAddresses();
        Message<List<UserDeliveryAddress>> response = new Message<>();

        if (addresses != null && !addresses.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(addresses);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no address found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message<UserDeliveryAddress>> getUserDeliveryAddressById(@PathVariable long id) {
        UserDeliveryAddress userDeliveryAddress = userDeliveryAddressService.getUserDeliveryAddressById(id);
        Message<UserDeliveryAddress> response = new Message<>();

        if (userDeliveryAddress != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(userDeliveryAddress);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Message<UserDeliveryAddress>> createUserDeliveryAddress(@RequestBody UserDeliveryAddress userDeliveryAddress) {
        UserDeliveryAddress createdUserDeliveryAddress = userDeliveryAddressService.createUserDeliveryAddress(userDeliveryAddress);
        Message<UserDeliveryAddress> response = new Message<>();

        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(createdUserDeliveryAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message<UserDeliveryAddress>> updateUserDeliveryAddress(@PathVariable long id, @RequestBody UserDeliveryAddress userDeliveryAddress) {
        UserDeliveryAddress updatedUserDeliveryAddress = userDeliveryAddressService.updateUserDeliveryAddress(id, userDeliveryAddress);
        Message<UserDeliveryAddress> response = new Message<>();
        if (updatedUserDeliveryAddress != null) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("success");
            response.setData(updatedUserDeliveryAddress);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDeliveryAddress(@PathVariable long id) {
        userDeliveryAddressService.deleteUserDeliveryAddress(id);
        return ResponseEntity.noContent().build();
    }
}
