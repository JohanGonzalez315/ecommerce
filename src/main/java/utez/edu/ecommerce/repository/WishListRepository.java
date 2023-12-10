package utez.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utez.edu.ecommerce.entity.WishList;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUser_IdUser(long idUser);

    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM WishList w WHERE w.user.idUser = :userId AND w.product.idProduct = :productId")
    boolean existsByUser_IdUserAndProduct_IdProduct(@Param("userId") long userId, @Param("productId") long productId);
    void deleteByIdWishList(long id);
}
