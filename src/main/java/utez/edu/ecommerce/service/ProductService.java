package utez.edu.ecommerce.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utez.edu.ecommerce.entity.Product;
import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(long productId);
    Product createProduct(Product product);
    Product updateProduct(long productId, Product product);
    List<Product> getProductsBySellerId(long sellerId);
    void deleteProduct(long productId);
    @Query("SELECT p FROM Product p WHERE p.category.name = :categoryName")
    List<Product> getProductsByCategory(@Param("categoryName") String categoryName);

    List<Product> getProductsByName(String name);

    List<Product> getTopSellingProducts(int limit);

    List<Map<String, Object>> getMostSoldCategories();
}
