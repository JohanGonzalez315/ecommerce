package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
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
    @Column(name = "status", nullable = false, length = 255)
    private String status;
    @Column(name = "subTotal")
    private double subTotal;
    @ManyToOne
    @JoinColumn(name = "idDeliveryMan")
    private DeliveryMan deliveryMan;
    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemProduct> orderItemProducts = new ArrayList<>();

    @PrePersist
    public void prePersist(){this.createdAt = new Date();}
    public BigDecimal calculateTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItemProduct orderItemProduct : this.orderItemProducts) {
            BigDecimal price = BigDecimal.valueOf(orderItemProduct.getProduct().getPrice());
            BigDecimal amount = BigDecimal.valueOf(orderItemProduct.getAmount());
            totalPrice = totalPrice.add(price.multiply(amount));
        }
        return totalPrice;
    }

}
