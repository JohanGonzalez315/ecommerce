package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.ecommerce.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String categoryName);
    List<Product> findByNameContainingIgnoreCase(String name);
}
