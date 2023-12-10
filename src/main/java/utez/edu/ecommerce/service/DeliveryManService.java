package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.DeliveryMan;
import utez.edu.ecommerce.utils.Message;

import java.util.List;

public interface DeliveryManService {
    List<DeliveryMan> getAllDeliveryMen();
    DeliveryMan getDeliveryManById(long deliveryManId);
    DeliveryMan createDeliveryMan(DeliveryMan deliveryMan);
    DeliveryMan updateDeliveryMan(long deliveryManId, DeliveryMan deliveryMan);
    boolean deleteDeliveryMan(long deliveryManId);
}
