package utez.edu.ecommerce.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.entity.WishList;

import java.util.List;

@Data
public class WishListDTO<T> {
    private long idWishList;
    private User user;
    private T data;

    public WishListDTO(int idWishList, User user, T data) {
        this.idWishList = idWishList;
        this.user = user;
        this.data = data;
    }

    public WishListDTO() {
    }
}

