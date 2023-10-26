package utez.edu.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.ecommerce.entity.SellerIdentity;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.repository.SellerIdentityRepository;
import utez.edu.ecommerce.service.SellerIdentityService;

import java.util.List;
import java.util.Optional;

@Service
public class SellerIdentityServiceImpl implements SellerIdentityService {

    private final SellerIdentityRepository sellerIdentityRepository;

    @Autowired
    public SellerIdentityServiceImpl(SellerIdentityRepository sellerIdentityRepository) {
        this.sellerIdentityRepository = sellerIdentityRepository;
    }

    @Override
    public List<SellerIdentity> getAllSellers() {
        return sellerIdentityRepository.findAll();
    }

    @Override
    public SellerIdentity getSellerById(long sellerId) {
        Optional<SellerIdentity> sellerIdentity = sellerIdentityRepository.findById(sellerId);
        return sellerIdentity.orElse(null);
    }

    @Override
    public SellerIdentity createUser(SellerIdentity sellerIdentity) {
        return sellerIdentityRepository.save(sellerIdentity);
    }

    @Override
    public SellerIdentity updateUser(long sellerId, SellerIdentity sellerIdentity) {
        if (sellerIdentityRepository.existsById(sellerId)) {
            sellerIdentity.setIdSeller(sellerId);
            return sellerIdentityRepository.save(sellerIdentity);
        }
        return null;
    }

    @Override
    public boolean existsByUserId(long userId) {
        return sellerIdentityRepository.existsByUser_IdUser(userId);
    }


    @Override
    public void deleteUser(long userId) {

    }

}
