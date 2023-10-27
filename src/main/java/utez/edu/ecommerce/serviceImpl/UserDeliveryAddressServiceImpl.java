package utez.edu.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.ecommerce.entity.UserDeliveryAddress;
import utez.edu.ecommerce.repository.UserDeliveryAddressRepository;
import utez.edu.ecommerce.service.UserDeliveryAddressService;

import java.util.List;
import java.util.Optional;

@Service
public class UserDeliveryAddressServiceImpl implements UserDeliveryAddressService {

    private final UserDeliveryAddressRepository userDeliveryAddressRepository;

    @Autowired
    public UserDeliveryAddressServiceImpl(UserDeliveryAddressRepository userDeliveryAddressRepository) {
        this.userDeliveryAddressRepository = userDeliveryAddressRepository;
    }

    @Override
    public List<UserDeliveryAddress> getAllUserDeliveryAddresses() {
        return userDeliveryAddressRepository.findAll();
    }

    @Override
    public UserDeliveryAddress getUserDeliveryAddressById(long id) {
        Optional<UserDeliveryAddress> userDeliveryAddress = userDeliveryAddressRepository.findById(id);
        return userDeliveryAddress.orElse(null);
    }

    @Override
    public UserDeliveryAddress createUserDeliveryAddress(UserDeliveryAddress userDeliveryAddress) {
        return userDeliveryAddressRepository.save(userDeliveryAddress);
    }

    @Override
    public UserDeliveryAddress updateUserDeliveryAddress(long idAdress, UserDeliveryAddress userDeliveryAddress) {
        if (userDeliveryAddressRepository.existsById(idAdress)) {
            userDeliveryAddress.setIdAddress(idAdress);
            return userDeliveryAddressRepository.save(userDeliveryAddress);
        }
        return null;
    }

    @Override
    public void deleteUserDeliveryAddress(long id) {
        userDeliveryAddressRepository.deleteById(id);
    }
}
