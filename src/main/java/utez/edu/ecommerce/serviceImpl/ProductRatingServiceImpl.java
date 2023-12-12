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
    private final ProductService productService;

    @Autowired
    public ProductRatingServiceImpl(ProductRatingRepository productRatingRepository, ProductService productService) {
        this.productRatingRepository = productRatingRepository;
        this.productService = productService;
    }

    @Override
    public ProductRating createProductRating(ProductRating productRating) {
        ProductRating savedProductRating = productRatingRepository.save(productRating);
        updateProductRating(savedProductRating.getProduct());
        return savedProductRating;
    }

    @Override
    public List<ProductRating> findByProductId(long productId) {
        return productRatingRepository.findByProduct_idProduct(productId);
    }

    @Override
    public void deleteByProductIdAndIdProductRating(long productId, long idProductRating) {
        productRatingRepository.deleteByProduct_IdProductAndIdProductRating(productId, idProductRating);
    }

    private void updateProductRating(Product product) {
        List<ProductRating> productRatings = productRatingRepository.findByProduct(product);
        Product product1 = productService.getProductById(product.getIdProduct());
        product.updateRating(productRatings);
        product.setName(product1.getName());
        product.setPrice(product1.getPrice());
        product.setQuantityAvailable(product1.getQuantityAvailable());
        product.setQuantitySold(product1.getQuantitySold());
        product.setCategory(product1.getCategory());
        product.setSeller(product1.getSeller());
        product.setImageLinks(product1.getImageLinks());
        product.setDescription(product1.getDescription());
        product.setTags(product1.getTags());
        System.out.println("El nuevo producto: " + product);
        productService.updateProduct(product.getIdProduct(), product);
    }
}