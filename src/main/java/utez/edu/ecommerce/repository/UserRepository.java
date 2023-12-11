package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utez.edu.ecommerce.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    Optional<User> findOneByEmail(String email);

    List<User> findByRol_Name(String roleName);

    @Query("SELECT COUNT(u) FROM User u WHERE u.rol.idRol = :roleId")
    Long countUsersByRoleId(@Param("roleId") long roleId);

}
