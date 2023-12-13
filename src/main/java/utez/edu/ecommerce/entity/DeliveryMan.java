package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "delivery_man")
@Data
public class DeliveryMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDeliveryMan;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    @Column(name = "license_photo", nullable = false, length = 255)
    private String licensePhoto;
    @Column(name = "available")
    private boolean available;
    @OneToOne
    @JoinColumn(name = "idOrderItems")
    private OrderItem orderItem;

}
