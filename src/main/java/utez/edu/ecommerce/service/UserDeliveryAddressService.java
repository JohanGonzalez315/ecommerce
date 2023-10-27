package utez.edu.ecommerce.service;

import utez.edu.ecommerce.entity.UserDeliveryAddress;

import java.util.List;

public interface UserDeliveryAddressService {
    List<UserDeliveryAddress> getAllUserDeliveryAddresses();

    UserDeliveryAddress getUserDeliveryAddressById(long id);

    UserDeliveryAddress createUserDeliveryAddress(UserDeliveryAddress userDeliveryAddress);

    UserDeliveryAddress updateUserDeliveryAddress(long id, UserDeliveryAddress userDeliveryAddress);

    void deleteUserDeliveryAddress(long id);
}
