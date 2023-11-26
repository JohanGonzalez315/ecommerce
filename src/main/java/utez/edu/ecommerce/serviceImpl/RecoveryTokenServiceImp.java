package utez.edu.ecommerce.serviceImpl;

import java.util.Random;

import org.springframework.stereotype.Service;

import utez.edu.ecommerce.entity.RecoveryToken;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.repository.RecoveryTokenRepository;
import utez.edu.ecommerce.service.RecoveryTokenService;

@Service
public class RecoveryTokenServiceImp implements RecoveryTokenService{
	
	private final RecoveryTokenRepository recoveryTokenRepository;
	private final EmailService emailService;
	
	public RecoveryTokenServiceImp(RecoveryTokenRepository recoveryTokenRepository, EmailService emailService) {
		this.recoveryTokenRepository = recoveryTokenRepository;
		this.emailService = emailService;
	}

	@Override
	public RecoveryToken findByUserAndToken(User user, int token) {
		
		return recoveryTokenRepository.findByUserAndToken(user, token);
	}

	@Override
	public String createRecoveryToken(User user) {
		RecoveryToken recoveryToken = new RecoveryToken();
		Random random = new Random();
        int numeroAleatorio = random.nextInt(900000) + 100000;
        recoveryToken.setToken(numeroAleatorio);
        recoveryToken.setUser(user);
        recoveryTokenRepository.save(recoveryToken);
        return emailService.sendMail(user.getEmail(),"Solicitud de recuperación de contraseña","Tu código de verificación es el siguiente:\n\n                           "+numeroAleatorio+"\n\n Ingresalo para recuperar tu contraseña");
	}

	@Override
	public boolean updateRecoveryToken(RecoveryToken recoveryToken) {
			recoveryToken.setStatus(false);
			recoveryTokenRepository.save(recoveryToken);
			return true;
	}

	@Override
	public boolean existsByUserAndToken(User user, int token) {
		return recoveryTokenRepository.existsByUserAndToken(user, token);
	}
	

}
