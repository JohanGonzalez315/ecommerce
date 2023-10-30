package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOrderItems;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "order_item_product",
            joinColumns = @JoinColumn(name = "order_item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
    @Column(name = "subTotal")
    private double subTotal;

    public OrderItem() {
        this.products = new ArrayList<>();
    }
}
