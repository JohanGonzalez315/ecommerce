package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOrder;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    @OneToOne
    @JoinColumn(name = "idStatus")
    private Status status;
    @OneToOne
    @JoinColumn(name = "idAddress")
    private UserDeliveryAddress userDeliveryAddress;
    @Column(name = "total")
    private int total;
}
