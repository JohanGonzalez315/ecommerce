package utez.edu.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.ecommerce.entity.OrderItem;
import utez.edu.ecommerce.repository.OrderItemRepository;
import utez.edu.ecommerce.service.OrderItemService;

import java.math.BigDecimal;
import java.util.Date;
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
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);
        return optionalOrderItem.orElse(null);
    }

    @Override
    public OrderItem getOrderItemWithProductsByUserId(long userId) {
        return orderItemRepository.findOrderItemByUserId(userId);
    }

    @Override
    public int countTotalOrdersByMonth(Date date) {
        return orderItemRepository.countTotalOrdersByMonth(date);
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(long orderItemId, OrderItem orderItem) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);
        if (optionalOrderItem.isPresent()) {
            OrderItem existingOrderItem = optionalOrderItem.get();
            existingOrderItem.setOrderItemProducts(orderItem.getOrderItemProducts());
            existingOrderItem.setStatus(orderItem.getStatus());
            existingOrderItem.setUser(orderItem.getUser());
            existingOrderItem.setSubTotal(orderItem.getSubTotal());
            return orderItemRepository.save(existingOrderItem);
        }
        return null;
    }
    @Override
    public BigDecimal getTotalOfOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            total = total.add(orderItem.calculateTotalPrice());
        }
        return total;
    }
    @Override
    public long countOrderItemsByStatusEntregado(String status) {
        return orderItemRepository.countByStatusEntregado(status);
    }

    @Override
    public void deleteOrderItem(long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }


}
