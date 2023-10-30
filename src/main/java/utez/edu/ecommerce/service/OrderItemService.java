package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItemById(long orderItemId);
    OrderItem createOrderItem(OrderItem orderItem);
    OrderItem updateOrderItem(long orderItemId, OrderItem orderItem);
    void deleteOrderItem(long orderItemId);
}
