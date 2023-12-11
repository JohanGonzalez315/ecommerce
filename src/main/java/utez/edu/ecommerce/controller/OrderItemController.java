package utez.edu.ecommerce.controller;

import com.amazonaws.services.lexmodelsv2.model.MessageGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.ecommerce.dto.OrderItemDTO;
import utez.edu.ecommerce.entity.OrderItem;
import utez.edu.ecommerce.entity.OrderItemProduct;
import utez.edu.ecommerce.service.OrderItemProductService;
import utez.edu.ecommerce.service.OrderItemService;
import utez.edu.ecommerce.utils.Message;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderItemProductService orderItemProductService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService, OrderItemProductService orderItemProductService) {
        this.orderItemService = orderItemService;
        this.orderItemProductService = orderItemProductService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable long orderItemId) {
        OrderItem orderItem = orderItemService.getOrderItemById(orderItemId);
        if (orderItem != null) {
            return ResponseEntity.ok(orderItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Message<OrderItem>> getOrderItemWithProductsByUserId(@PathVariable long userId) {
        OrderItem orderItem = orderItemService.getOrderItemWithProductsByUserId(userId);
        Message<OrderItem> response = new Message<>();
        if (orderItem != null) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("success");
            response.setData(orderItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("error: no order item for this user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/order-history/{sellerId}")
    public ResponseEntity<Message<List<OrderItemProduct>>> getSellerOrderHistory(@PathVariable long sellerId) {
        List<OrderItemProduct> sellerOrderHistory = orderItemProductService.getOrderItemProductsBySellerId(sellerId);
        Message<List<OrderItemProduct>> response = new Message();
        if(sellerOrderHistory.isEmpty() || sellerOrderHistory == null){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no order items for this seller");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }else {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("success");
            response.setData(sellerOrderHistory);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

    @GetMapping("/count-orders-by-month")
    public ResponseEntity<OrderItemDTO> countOrdersByMonth(@RequestParam int year, @RequestParam int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        Date targetDate = calendar.getTime();
        int totalOrders = orderItemService.countTotalOrdersByMonth(targetDate);
        OrderItemDTO response = new OrderItemDTO();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(totalOrders);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/total")
    public ResponseEntity<Message<BigDecimal>> getTotalOfOrderItems() {
        BigDecimal total = orderItemService.getTotalOfOrderItems();
        Message<BigDecimal> response = new Message<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(total);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/count-delivered")
    public ResponseEntity<Message<Long>> countDeliveredOrderItems() {
        long count = orderItemService.countOrderItemsByStatusEntregado();
        Message<Long> response = new Message<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(count);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping
    public ResponseEntity<Message<OrderItem>> createOrderItemWithProducts(@RequestBody OrderItem orderItem) {
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItem);
        Message<OrderItem> response = new Message<>();
        if (createdOrderItem != null) {
            List<OrderItemProduct> orderItemProducts = orderItem.getOrderItemProducts();
            if (orderItemProducts != null && !orderItemProducts.isEmpty()) {
                for (OrderItemProduct orderItemProduct : orderItemProducts) {
                    orderItemProduct.setUser(orderItem.getUser());
                    orderItemProduct.setOrderItem(createdOrderItem);
                    orderItemProductService.createOrderItemProduct(orderItemProduct);
                }
            }
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("success");
            response.setData(createdOrderItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("error: bad request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PutMapping("/{orderItemId}")
    public ResponseEntity<Message<OrderItem>> updateOrderItem(@PathVariable long orderItemId, @RequestBody OrderItem orderItem) {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItem);
        Message<OrderItem> response = new Message<>();
        if (updatedOrderItem != null) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("success");
            response.setData(updatedOrderItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("order item not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/products/{orderItemProductId}")
    public ResponseEntity<Message<?>> deleteOrderItemProduct(@PathVariable long orderItemProductId) {
        orderItemProductService.deleteOrderItemProduct(orderItemProductId);
        Message<OrderItem> response = new Message<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Message<?>> deleteOrderItem(@PathVariable long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        Message<OrderItem> response = new Message<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
