package utez.edu.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.ecommerce.entity.DeliveryMan;
import utez.edu.ecommerce.entity.OrderItem;
import utez.edu.ecommerce.repository.DeliveryManRepository;
import utez.edu.ecommerce.repository.OrderItemRepository;
import utez.edu.ecommerce.service.DeliveryManService;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryManServiceImpl implements DeliveryManService {

    private final DeliveryManRepository deliveryManRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public DeliveryManServiceImpl(DeliveryManRepository deliveryManRepository, OrderItemRepository orderItemRepository) {
        this.deliveryManRepository = deliveryManRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<DeliveryMan> getAllDeliveryMen() {
        return deliveryManRepository.findAll();
    }

    @Override
    public DeliveryMan getDeliveryManById(long deliveryManId) {
        Optional<DeliveryMan> deliveryMan = deliveryManRepository.findById(deliveryManId);
        return deliveryMan.orElse(null);
    }

    @Override
    public DeliveryMan createDeliveryMan(DeliveryMan deliveryMan) {
        return deliveryManRepository.save(deliveryMan);
    }

    @Override
    public DeliveryMan updateDeliveryMan(long deliveryManId, DeliveryMan deliveryMan) {
        if (deliveryManRepository.existsById(deliveryManId)) {
            deliveryMan.setIdDeliveryMan(deliveryManId);
            return deliveryManRepository.save(deliveryMan);
        }
        return null;
    }

    @Override
    public DeliveryMan assignOrderToDeliveryMan(long deliveryManId, long orderId) {
        DeliveryMan deliveryMan = deliveryManRepository.findById(deliveryManId).orElse(null);
        OrderItem orderItem = orderItemRepository.findById(orderId).orElse(null);

        if (deliveryMan != null && orderItem != null) {
            deliveryMan.setOrderItem(orderItem);
            orderItem.setDeliveryMan(deliveryMan);
            return deliveryManRepository.save(deliveryMan);
        }

        return null;
    }

    public DeliveryMan findAvailableDeliveryMan() {
        return deliveryManRepository.findFirstByAvailable(true);
    }

    @Override
    public boolean deleteDeliveryMan(long deliveryManId) {
        if (deliveryManRepository.existsById(deliveryManId)) {
            deliveryManRepository.deleteById(deliveryManId);
            return true;
        }
        return false;
    }

}
