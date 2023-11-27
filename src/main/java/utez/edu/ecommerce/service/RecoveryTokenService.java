package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.RecoveryToken;
import utez.edu.ecommerce.entity.User;

public interface RecoveryTokenService {

	RecoveryToken findByUserAndToken(User user, int token);
	String createRecoveryToken(User user);
	boolean updateRecoveryToken(RecoveryToken recoveryToken);
	boolean existsByUserAndToken(User user, int token);
}
