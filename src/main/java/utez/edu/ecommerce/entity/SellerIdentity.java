package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seller_identity")
@Data
public class SellerIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSeller;
    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;
    @Column(name = "rfc", nullable = false, length = 255)
    private String rfc;
    @Column(name = "ineLink", nullable = false, length = 255)
    private String ineLink;
    @Column(name = "shopType", nullable = false, length = 255)
    private String shopType;
    @Column(name = "rating", nullable = false, length = 255)
    private double rating;
    @Column(name = "status", nullable = false)
    private boolean status = true;

    @PrePersist
    public void prePersist(){this.status = true; this.rating=0;}

    @Override
    public String toString() {
        return "SellerIdentity{" +
                "idUser=" + idSeller +
                ", user=" + user +
                ", rfc='" + rfc + '\'' +
                ", ineLink='" + ineLink + '\'' +
                ", shopType='" + shopType + '\'' +
                ", status=" + status +
                '}';
    }
}
