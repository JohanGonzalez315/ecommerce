package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.ecommerce.entity.DeliveryMan;
import utez.edu.ecommerce.service.DeliveryManService;
import utez.edu.ecommerce.utils.Message;

import java.util.List;

@RestController
@RequestMapping("/api/deliveryman")
public class DeliveryManController {

    private final DeliveryManService deliveryManService;

    @Autowired
    public DeliveryManController(DeliveryManService deliveryManService) {
        this.deliveryManService = deliveryManService;
    }

    @GetMapping
    public ResponseEntity<Message<List<DeliveryMan>>> getAllDeliveryMen() {
        List<DeliveryMan> deliveryMen = deliveryManService.getAllDeliveryMen();
        Message<List<DeliveryMan>> response = new Message<>();

        if (!deliveryMen.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(deliveryMen);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no delivery men found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{deliveryManId}")
    public ResponseEntity<Message<DeliveryMan>> getDeliveryManById(@PathVariable long deliveryManId) {
        DeliveryMan deliveryMan = deliveryManService.getDeliveryManById(deliveryManId);
        Message<DeliveryMan> response = new Message<>();

        if (deliveryMan != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(deliveryMan);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: delivery man not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Message<DeliveryMan>> createDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
        DeliveryMan createdDeliveryMan = deliveryManService.createDeliveryMan(deliveryMan);
        Message<DeliveryMan> response = new Message<>();

        if (createdDeliveryMan != null) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("success");
            response.setData(createdDeliveryMan);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("error: unable to create delivery man");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{deliveryManId}")
    public ResponseEntity<Message<DeliveryMan>> updateDeliveryMan(@PathVariable long deliveryManId, @RequestBody DeliveryMan deliveryMan) {
        DeliveryMan updatedDeliveryMan = deliveryManService.updateDeliveryMan(deliveryManId, deliveryMan);
        Message<DeliveryMan> response = new Message<>();

        if (updatedDeliveryMan != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(updatedDeliveryMan);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: delivery man not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{deliveryManId}")
    public ResponseEntity<Message<?>> deleteDeliveryMan(@PathVariable long deliveryManId) {
        boolean result = deliveryManService.deleteDeliveryMan(deliveryManId);
        Message<?> response = new Message<>();

        if (result) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: delivery man not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
