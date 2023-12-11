package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utez.edu.ecommerce.entity.OrderItemProduct;

import java.util.List;

@Repository
public interface OrderItemProductRepository extends JpaRepository<OrderItemProduct, Long> {
    List<OrderItemProduct> findByProduct_Seller_IdSeller(long sellerId);
    @Query("SELECT COUNT(oi) FROM OrderItemProduct oi WHERE oi.product.seller.idSeller = :sellerId")
    int countBySellerIdentityId(@Param("sellerId") long sellerId);
}
