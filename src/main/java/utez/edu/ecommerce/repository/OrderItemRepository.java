package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utez.edu.ecommerce.entity.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT oi FROM OrderItem oi JOIN FETCH oi.orderItemProducts oip WHERE oi.user.idUser = :userId")
    OrderItem findOrderItemByUserId(@Param("userId") long userId);
    @Query("SELECT COUNT(oi) FROM OrderItem oi WHERE oi.status = :status")
    long countByStatusEntregado(@Param("status") String status);
    @Query("SELECT COUNT(oi) FROM OrderItem oi WHERE YEAR(oi.createdAt) = YEAR(:date) AND MONTH(oi.createdAt) = MONTH(:date)")
    int countTotalOrdersByMonth(@Param("date") Date date);

}
