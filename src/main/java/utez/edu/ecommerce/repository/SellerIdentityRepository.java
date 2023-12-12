package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.ecommerce.entity.SellerIdentity;


@Repository
public interface SellerIdentityRepository extends JpaRepository<SellerIdentity, Long> {
    boolean existsByUser_IdUser(long userId);
    SellerIdentity findByUser_IdUser(long userId);
}
