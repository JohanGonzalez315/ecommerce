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
    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "idSeller")
    private SellerIdentity seller;
    @ElementCollection
    @Column(name = "image_link")
    private List<String> imageLinks;
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    @Column(name = "tags", nullable = false, length = 255)
    private String tags;
    @ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems;
    
}
