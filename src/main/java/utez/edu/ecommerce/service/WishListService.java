package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.WishList;
import java.util.List;

public interface WishListService {
    List<WishList> getAllWishLists();
    WishList getWishListById(long wishListId);
    WishList createWishList(WishList wishList);
    WishList updateWishList(long wishListId, WishList wishList);
    void deleteWishList(long wishListId);
    List<WishList> getWishListByUser(long userId);
}
