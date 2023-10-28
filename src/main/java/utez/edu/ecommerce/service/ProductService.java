package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(long productId);
    Product createProduct(Product product);
    Product updateProduct(long productId, Product product);
    void deleteProduct(long productId);
}
