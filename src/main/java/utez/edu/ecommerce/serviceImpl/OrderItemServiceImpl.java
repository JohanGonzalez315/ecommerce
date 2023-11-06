package utez.edu.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.ecommerce.entity.OrderItem;
import utez.edu.ecommerce.repository.OrderItemRepository;
import utez.edu.ecommerce.service.OrderItemService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOrderItemById(long orderItemId) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
        return orderItem.orElse(null);
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(long orderItemId, OrderItem orderItem) {
        if (orderItemRepository.existsById(orderItemId)) {
            orderItem.setIdOrderItems(orderItemId);
            return orderItemRepository.save(orderItem);
        }
        return null;
    }

    @Override
    public void deleteOrderItem(long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
