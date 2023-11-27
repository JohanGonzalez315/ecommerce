package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.Product;
import utez.edu.ecommerce.entity.ProductRating;

import java.util.List;

public interface ProductRatingService {
    ProductRating createProductRating(ProductRating productRating);
    List<ProductRating> findByProductId(long productId);
    void deleteByProductIdAndIdProductRating(long productId, long idProductRating);
}
