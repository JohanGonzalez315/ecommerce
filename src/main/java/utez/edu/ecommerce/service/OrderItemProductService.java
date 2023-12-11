package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.OrderItemProduct;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemProductService {
    List<OrderItemProduct> getAllOrderItemProducts();
    OrderItemProduct createOrderItemProduct(OrderItemProduct orderItemProduct);
    void deleteOrderItemProduct(long orderItemProductId);
    List<OrderItemProduct> getOrderItemProductsBySellerId(long sellerId);
    int countOrderItemProductsBySellerIdentityId(long sellerId);

    long countOrdersBySellerId(long sellerId);
    BigDecimal getTotalIncomeBySellerId(long sellerId);

}
