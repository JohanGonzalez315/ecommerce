package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.ecommerce.entity.Rol;
import utez.edu.ecommerce.entity.User;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    List<Rol> findByName(String name);
}
