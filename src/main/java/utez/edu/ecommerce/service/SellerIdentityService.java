package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.SellerIdentity;

import java.util.List;
import java.util.Optional;

public interface SellerIdentityService {
    List<SellerIdentity> getAllSellers();
    SellerIdentity getSellerById(long userId);
    SellerIdentity createUser(SellerIdentity sellerIdentity);
    SellerIdentity updateUser(long sellerId, SellerIdentity sellerIdentity);
    boolean existsByUserId(long userId);
    void deleteUser(long userId);
}
