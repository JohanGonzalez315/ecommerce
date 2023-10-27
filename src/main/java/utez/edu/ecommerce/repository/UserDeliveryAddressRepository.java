package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.ecommerce.entity.UserDeliveryAddress;

public interface UserDeliveryAddressRepository extends JpaRepository<UserDeliveryAddress, Long> {
    // Puedes añadir consultas personalizadas si es necesario
}
