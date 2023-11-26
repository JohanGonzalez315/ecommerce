package utez.edu.ecommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import utez.edu.ecommerce.entity.RecoveryToken;
import utez.edu.ecommerce.entity.User;

public interface RecoveryTokenRepository extends JpaRepository<RecoveryToken, Long>{

	RecoveryToken findByUserAndToken(User user, int token);
	
	boolean existsByUserAndToken(User user, int token);
	
}
