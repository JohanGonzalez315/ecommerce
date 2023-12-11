package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utez.edu.ecommerce.entity.OrderItemProduct;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderItemProductRepository extends JpaRepository<OrderItemProduct, Long> {
    List<OrderItemProduct> findByProduct_Seller_IdSeller(long sellerId);
    @Query("SELECT COUNT(oi) FROM OrderItemProduct oi WHERE oi.product.seller.idSeller = :sellerId")
    int countBySellerIdentityId(@Param("sellerId") long sellerId);

    @Query("SELECT COUNT(DISTINCT oip.orderItem.idOrderItems) FROM OrderItemProduct oip WHERE oip.product.seller.idSeller = :sellerId")
    long countOrdersBySellerId(@Param("sellerId") long sellerId);

    @Query("SELECT SUM(oip.amount * oip.product.price) FROM OrderItemProduct oip WHERE oip.product.seller.idSeller = :sellerId")
    BigDecimal sumTotalIncomeBySellerId(@Param("sellerId") long sellerId);
}
