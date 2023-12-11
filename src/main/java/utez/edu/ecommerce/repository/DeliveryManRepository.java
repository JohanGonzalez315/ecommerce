package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.ecommerce.entity.DeliveryMan;

@Repository
public interface DeliveryManRepository extends JpaRepository<DeliveryMan, Long> {
    DeliveryMan findFirstByAvailable(boolean available);
}
