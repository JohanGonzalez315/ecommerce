package utez.edu.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.ecommerce.entity.DeliveryMan;
import utez.edu.ecommerce.repository.DeliveryManRepository;
import utez.edu.ecommerce.service.DeliveryManService;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryManServiceImpl implements DeliveryManService {

    private final DeliveryManRepository deliveryManRepository;

    @Autowired
    public DeliveryManServiceImpl(DeliveryManRepository deliveryManRepository) {
        this.deliveryManRepository = deliveryManRepository;
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
    public boolean deleteDeliveryMan(long deliveryManId) {
        if (deliveryManRepository.existsById(deliveryManId)) {
            deliveryManRepository.deleteById(deliveryManId);
            return true;
        }
        return false;
    }

}
