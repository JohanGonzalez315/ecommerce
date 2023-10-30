package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.ecommerce.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Puedes agregar consultas personalizadas si las necesitas
}
