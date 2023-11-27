package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.ecommerce.entity.Product;
import utez.edu.ecommerce.entity.User;
import utez.edu.ecommerce.entity.WishList;
import utez.edu.ecommerce.service.WishListService;
import utez.edu.ecommerce.utils.Message;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wishlists")
public class WishListController {

    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping
    public ResponseEntity<Message<List<WishList>>> getAllWishLists() {
        List<WishList> wishLists = wishListService.getAllWishLists();
        Message<List<WishList>> response = new Message<>();

        if (wishLists != null && !wishLists.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(wishLists);
            return ResponseEntity.ok(response);
        }else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no wishlists found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @GetMapping("/{wishListId}")
    public ResponseEntity<Message<WishList>> getWishListById(@PathVariable long wishListId) {
        WishList wishList = wishListService.getWishListById(wishListId);
        Message<WishList> response = new Message<>();
        if (wishList != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(wishList);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no wishlist found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Message<WishList>> createWishList(@RequestBody WishList wishList) {
        WishList createdWishList = wishListService.createWishList(wishList);
        Message<WishList> response = new Message<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("success");
        response.setData(createdWishList);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{wishListId}")
    public ResponseEntity<Message<WishList>> updateWishList(@PathVariable long wishListId, @RequestBody WishList wishList) {
        WishList updatedWishList = wishListService.updateWishList(wishListId, wishList);
        Message<WishList> response = new Message<>();

        if (updatedWishList != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(updatedWishList);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no wishlist found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /*@GetMapping("/user/{userId}")
    public ResponseEntity<List<WishList>> getWishListByUser(@PathVariable long userId) {
        List<WishList> wishLists = wishListService.getWishListByUser(userId);
        return ResponseEntity.ok(wishLists);
    }*/

    @GetMapping("/user/{userId}")
    public ResponseEntity<Message<List<Product>>> getWishListByUser(@PathVariable long userId) {
        List<WishList> wishList = wishListService.getWishListByUser(userId);
        Message<List<Product>> response = new Message<>();

        if (wishList == null || wishList.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no wishlist found for this user");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } else {
            List<Product> products = wishList.stream()
                    .map(WishList::getProduct)
                    .collect(Collectors.toList());

            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(products);
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message<String>> deleteWishList(@PathVariable long id) {
        WishList wishList = wishListService.getWishListById(id);
        if (wishList != null) {
            wishListService.deleteWishListById(id);
            Message<String> response = new Message<>();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success: wishlist deleted");
            return ResponseEntity.ok(response);
        } else {
            Message<String> response = new Message<>();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: wishlist not found with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
