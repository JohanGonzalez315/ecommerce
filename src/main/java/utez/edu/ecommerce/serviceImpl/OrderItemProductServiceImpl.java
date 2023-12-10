package utez.edu.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.ecommerce.entity.OrderItemProduct;
import utez.edu.ecommerce.repository.OrderItemProductRepository;
import utez.edu.ecommerce.service.OrderItemProductService;

import java.util.List;

@Service
public class OrderItemProductServiceImpl implements OrderItemProductService {

    private final OrderItemProductRepository orderItemProductRepository;

    @Autowired
    public OrderItemProductServiceImpl(OrderItemProductRepository orderItemProductRepository) {
        this.orderItemProductRepository = orderItemProductRepository;
    }

    @Override
    public List<OrderItemProduct> getAllOrderItemProducts() {
        return orderItemProductRepository.findAll();
    }

    @Override
    public OrderItemProduct createOrderItemProduct(OrderItemProduct orderItemProduct) {
        return orderItemProductRepository.save(orderItemProduct);
    }

    @Override
    public List<OrderItemProduct> getOrderItemProductsBySellerId(long sellerId) {
        return orderItemProductRepository.findByProduct_Seller_IdSeller(sellerId);
    }

    @Override
    public void deleteOrderItemProduct(long orderItemProductId) {
        orderItemProductRepository.deleteById(orderItemProductId);
    }
}
