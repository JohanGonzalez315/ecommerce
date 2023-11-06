package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.ecommerce.entity.WishList;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUser_IdUser(long idUser);
}
