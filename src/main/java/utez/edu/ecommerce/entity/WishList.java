package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import utez.edu.ecommerce.entity.Product;
import utez.edu.ecommerce.entity.SellerIdentity;
import utez.edu.ecommerce.entity.User;

@Entity
@Table(name = "wishlist")
@Data
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idWishList;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;
}
