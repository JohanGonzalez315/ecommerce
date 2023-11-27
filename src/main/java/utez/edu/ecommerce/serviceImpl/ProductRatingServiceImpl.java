package utez.edu.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.ecommerce.entity.Product;
import utez.edu.ecommerce.entity.ProductRating;
import utez.edu.ecommerce.repository.ProductRatingRepository;
import utez.edu.ecommerce.service.ProductRatingService;
import utez.edu.ecommerce.service.ProductService;

import java.util.List;
@Service
public class ProductRatingServiceImpl implements ProductRatingService {

    private final ProductRatingRepository productRatingRepository;

    @Autowired
    public ProductRatingServiceImpl(ProductRatingRepository productRatingRepository) {
        this.productRatingRepository = productRatingRepository;
    }

    @Override
    public ProductRating createProductRating(ProductRating productRating) {
       return this.productRatingRepository.save(productRating);
    }

    @Override
    public List<ProductRating> findByProductId(long productId) {
        return productRatingRepository.findByProduct_idProduct(productId);
    }

    @Override
    public void deleteByProductIdAndIdProductRating(long productId, long idProductRating) {
        productRatingRepository.deleteByProduct_IdProductAndIdProductRating(productId, idProductRating);
    }
}