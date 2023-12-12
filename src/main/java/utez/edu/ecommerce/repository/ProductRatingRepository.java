package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.ecommerce.entity.Product;
import utez.edu.ecommerce.entity.ProductRating;

import java.util.List;

@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {
    List<ProductRating> findByProduct_idProduct(long productId);
    void deleteByProduct_IdProductAndIdProductRating(long productId, long idProductRating);

    List<ProductRating> findByProduct(Product product);

}

