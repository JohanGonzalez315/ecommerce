package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
