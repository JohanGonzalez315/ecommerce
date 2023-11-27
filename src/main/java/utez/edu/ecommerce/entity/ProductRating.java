package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_rating")
@Data
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProductRating;
    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    @Column(name = "rating", nullable = false)
    private double rating;
    @Column(name = "comment", nullable = false, length = 255)
    private String comment;
}
