package utez.edu.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		User user = userRepository.findOneByEmail(email).orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + "no existe."));
		return new UserDetailsImp(user);
	}
}
