package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "lastname", nullable = false, length = 255)
    private String lastname;
    @Column(name = "phone", nullable = false, length = 255)
    private String phone;
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "status", nullable = false)
    private boolean status = true;

    @PrePersist
    public void prePersist(){this.status = true;}

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", rol=" + rol +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
