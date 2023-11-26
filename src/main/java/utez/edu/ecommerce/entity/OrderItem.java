package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;
    @ManyToMany
    @JoinTable(
            name = "order_item_product",
            joinColumns = @JoinColumn(name = "order_item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
    @Column(name = "status", nullable = false, length = 255)
    private String status;
    @Column(name = "subTotal")
    private double subTotal;

    @PrePersist
    public void prePersist(){this.status = "Pendiente"; this.createdAt = new Date();}

    public OrderItem() {
        this.products = new ArrayList<>();
    }
}
