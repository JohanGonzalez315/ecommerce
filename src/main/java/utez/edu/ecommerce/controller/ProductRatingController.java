package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.ecommerce.entity.Product;
import utez.edu.ecommerce.entity.ProductRating;
import utez.edu.ecommerce.service.ProductRatingService;
import utez.edu.ecommerce.utils.Message;

import java.util.List;
@RestController
@RequestMapping("/api/product-ratings")
public class ProductRatingController {

    private final ProductRatingService productRatingService;

    @Autowired
    public ProductRatingController(ProductRatingService productRatingService) {
        this.productRatingService = productRatingService;
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Message<List<ProductRating>>> getProductRatingsByProductId(@PathVariable long productId) {
        List<ProductRating> productRatings = productRatingService.findByProductId(productId);
        Message<List<ProductRating>> response = new Message<>();

        if (productRatings.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no product ratings found for this product");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setStatus(HttpStatus.OK.value());
        response.setMessage("success");
        response.setData(productRatings);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/product-rating")
    public ResponseEntity<Message<ProductRating>> createProductRating(@RequestBody ProductRating productRating) {
        if (productRating == null || productRating.getProduct() == null || productRating.getUser() == null) {
            Message<ProductRating> response = new Message<>();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Invalid product rating or missing data");
            return ResponseEntity.badRequest().body(response);
        }

        ProductRating createdRating = productRatingService.createProductRating(productRating);
        if (createdRating == null) {
            Message<ProductRating> response = new Message<>();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error creating product rating");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        Message<ProductRating> response = new Message<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Product rating created successfully");
        response.setData(createdRating);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @DeleteMapping("/product/{productId}/{idProductRating}")
    public ResponseEntity<Void> deleteProductRating(@PathVariable long productId, @PathVariable long idProductRating) {
        productRatingService.deleteByProductIdAndIdProductRating(productId, idProductRating);
        return ResponseEntity.noContent().build();
    }
}