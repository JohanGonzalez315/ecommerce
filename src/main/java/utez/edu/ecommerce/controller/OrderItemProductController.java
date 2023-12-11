package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.ecommerce.dto.OrderItemDTO;
import utez.edu.ecommerce.service.OrderItemProductService;

@RestController
@RequestMapping("/api/orderitemproducts")
public class OrderItemProductController {

    private final OrderItemProductService orderItemProductService;

    @Autowired
    public OrderItemProductController(OrderItemProductService orderItemProductService) {
        this.orderItemProductService = orderItemProductService;
    }

    @GetMapping("/count/selleridentity/{sellerId}")
    public ResponseEntity<OrderItemDTO> countOrderItemProductsBySellerIdentityId(@PathVariable long sellerId) {
        int count = orderItemProductService.countOrderItemProductsBySellerIdentityId(sellerId);
        OrderItemDTO response = new OrderItemDTO();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(count);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
