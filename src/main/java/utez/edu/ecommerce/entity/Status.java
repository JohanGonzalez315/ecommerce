package utez.edu.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "status")
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStatus;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
}
