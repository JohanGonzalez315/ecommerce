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
    @Column(name = "status", nullable = false, length = 255)
    private String status = "Pendiente";
    @Column(name = "subTotal")
    private double subTotal;
    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemProduct> orderItemProducts = new ArrayList<>();

    @PrePersist
    public void prePersist(){this.createdAt = new Date();}
}
