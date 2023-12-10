package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.OrderItemProduct;
import java.util.List;

public interface OrderItemProductService {
    List<OrderItemProduct> getAllOrderItemProducts();
    OrderItemProduct createOrderItemProduct(OrderItemProduct orderItemProduct);
    void deleteOrderItemProduct(long orderItemProductId);
    List<OrderItemProduct> getOrderItemProductsBySellerId(long sellerId);
}
