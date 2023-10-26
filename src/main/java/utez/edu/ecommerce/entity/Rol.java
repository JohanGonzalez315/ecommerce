package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rol")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRol;
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Override
    public String toString() {
        return "Rol{" +
                "idRol=" + idRol +
                ", name='" + name + '\'' +
                '}';
    }
}
