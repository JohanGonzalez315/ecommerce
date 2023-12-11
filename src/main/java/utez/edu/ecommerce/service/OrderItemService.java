package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItemById(long orderItemId);
    OrderItem createOrderItem(OrderItem orderItem);
    OrderItem updateOrderItem(long orderItemId, OrderItem orderItem);
    void deleteOrderItem(long orderItemId);
    OrderItem getOrderItemWithProductsByUserId(long userId);
    BigDecimal getTotalOfOrderItems();
    long countOrderItemsByStatusEntregado();
    int countTotalOrdersByMonth(Date date);

}
