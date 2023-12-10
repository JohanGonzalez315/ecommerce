package utez.edu.ecommerce.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.ecommerce.entity.WishList;
import utez.edu.ecommerce.repository.WishListRepository;
import utez.edu.ecommerce.service.WishListService;
import java.util.List;
import java.util.Optional;

@Service
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;

    @Autowired
    public WishListServiceImpl(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @Override
    public List<WishList> getAllWishLists() {
        return wishListRepository.findAll();
    }

    @Override
    public WishList getWishListById(long wishListId) {
        Optional<WishList> wishList = wishListRepository.findById(wishListId);
        return wishList.orElse(null);
    }

    @Override
    public WishList createWishList(WishList wishList) {
        return wishListRepository.save(wishList);
    }

    @Override
    public WishList updateWishList(long wishListId, WishList wishList) {
        if (wishListRepository.existsById(wishListId)) {
            wishList.setIdWishList(wishListId);
            return wishListRepository.save(wishList);
        }
        return null;
    }
    @Override
    public void deleteWishList(long wishListId) {
        wishListRepository.deleteById(wishListId);
    }

    @Override
    public boolean isProductInWishList(long userId, long productId) {
        return wishListRepository.existsByUser_IdUserAndProduct_IdProduct(userId, productId);
    }

    @Override
    public WishList findByUserAndProduct(long userId, long productId) {
        return wishListRepository.findByUserAndProduct(userId, productId);
    }

    @Override
    public void deleteProductFromWishList(long userId, long productId) {

    }

    @Override
    public List<WishList> getWishListByUser(long idUser) {
        return wishListRepository.findByUser_IdUser(idUser);
    }

    @Transactional
    @Override
    public void deleteWishListById(long id) {
        wishListRepository.deleteById(id);
    }
}
