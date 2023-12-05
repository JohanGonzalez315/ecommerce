package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.Rol;
import utez.edu.ecommerce.entity.User;
import java.util.List;

public interface RolService {
    List<User> getUsersByRoleName(String roleName);
}
