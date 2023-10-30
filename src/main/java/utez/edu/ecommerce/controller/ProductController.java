package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.ecommerce.entity.Product;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.service.ProductService;
import utez.edu.ecommerce.utils.Message;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Message<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        Message<List<Product>> response = new Message<>();

        if (products != null && !products.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(products);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no products found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Message<Product>> getProductById(@PathVariable long productId) {
        Product product = productService.getProductById(productId);
        Message<Product> response = new Message<>();

        if (product != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(product);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Message<Product>> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        Message<Product> response = new Message<>();

        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Message<Product>> updateProduct(@PathVariable long productId, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(productId, product);
        Message<Product> response = new Message<>();

        if (updatedProduct != null) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("success");
            response.setData(updatedProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("error: usuario no autorizado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}