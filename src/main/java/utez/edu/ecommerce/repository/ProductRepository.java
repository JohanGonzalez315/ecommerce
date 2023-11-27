package utez.edu.ecommerce.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utez.edu.ecommerce.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String categoryName);
    List<Product> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT p FROM Product p ORDER BY p.quantitySold DESC")
    List<Product> findTopProductsByQuantitySold(Pageable pageable);

    @Query("SELECT p.category.name as categoryName, SUM(p.quantitySold) as totalSold " +
            "FROM Product p " +
            "GROUP BY p.category.name " +
            "ORDER BY totalSold DESC")
    List<Map<String, Object>> getMostSoldCategories();

}
