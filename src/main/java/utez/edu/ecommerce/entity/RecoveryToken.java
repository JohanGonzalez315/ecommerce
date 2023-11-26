package utez.edu.ecommerce.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "recovery_token")
@Data
public class RecoveryToken {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRecoveryToken;
	
	@ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @Column(name = "token", nullable = false )
    private int token;
    
    @Column(name = "status", nullable = false)
    private boolean status = true;

    @PrePersist
    public void prePersist(){this.status = true;}
}