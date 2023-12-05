package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utez.edu.ecommerce.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT oi FROM OrderItem oi JOIN FETCH oi.orderItemProducts oip WHERE oi.user.idUser = :userId")
    OrderItem findOrderItemByUserId(@Param("userId") long userId);

    @Query("SELECT COUNT(oi) FROM OrderItem oi WHERE oi.status = 'Entregado'")
    long countByStatusEntregado();
}
