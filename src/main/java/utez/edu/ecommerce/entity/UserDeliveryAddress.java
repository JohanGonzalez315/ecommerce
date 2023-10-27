package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_delivery_address")
@Data
public class UserDeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAddress;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    @Column(name = "address", nullable = false, length = 255)
    private String address;
    @Column(name = "cp", nullable = false, length = 255)
    private String cp;
    @Column(name = "city", nullable = false, length = 255)
    private String city;
    @Column(name = "country", nullable = false, length = 255)
    private String country;
}
