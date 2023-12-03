package utez.edu.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_item_product")
@Data
public class OrderItemProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOrderItemProduct;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @Column(name = "amount", nullable = false, length = 255)
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idOrderItems")
    @JsonIgnore
    private OrderItem orderItem;
}
