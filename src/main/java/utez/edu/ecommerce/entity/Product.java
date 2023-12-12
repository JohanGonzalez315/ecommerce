package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduct;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "quantityAvailable", nullable = false)
    private int quantityAvailable;
    @Column(name = "quantitySold", nullable = false)
    private int quantitySold;
    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "idSeller")
    private SellerIdentity seller;
    @ElementCollection
    @CollectionTable(name = "product_image_links", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_link")
    private List<ProductImage> imageLinks;
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    @Column(name = "tags", nullable = false, length = 255)
    private String tags;
    @Column(name = "rating", nullable = false)
    private double rating;

    @PrePersist
    public void prePersist(){this.quantitySold=0;}

    public void updateRating(List<ProductRating> productRatings) {
        if (productRatings != null && !productRatings.isEmpty()) {
            double totalRating = productRatings.stream().mapToDouble(ProductRating::getRating).sum();
            this.setRating(totalRating / productRatings.size());
        } else {
            this.setRating(0.0);
        }
    }
}
