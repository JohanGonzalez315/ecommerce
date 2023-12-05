package utez.edu.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.ecommerce.entity.Rol;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.repository.RolRepository;
import utez.edu.ecommerce.repository.UserRepository;
import utez.edu.ecommerce.service.RolService;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final UserRepository userRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository, UserRepository userRepository) {
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsersByRoleName(String roleName) {
        return userRepository.findByRol_Name(roleName);
    }
}
