package utez.edu.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import utez.edu.ecommerce.entity.Product;
import utez.edu.ecommerce.entity.ProductImage;
import utez.edu.ecommerce.service.ProductService;
import utez.edu.ecommerce.serviceImpl.AWSS3ServiceImp;
import utez.edu.ecommerce.utils.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final AWSS3ServiceImp awss3ServiceImp;

    @Autowired
    public ProductController(ProductService productService, AWSS3ServiceImp awss3ServiceImp) {
        this.productService = productService;
        this.awss3ServiceImp = awss3ServiceImp;
    }

    @GetMapping
    public ResponseEntity<Message<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        Message<List<Product>> response = new Message<>();

        if (products != null && !products.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(products);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no products found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Message<Product>> getProductById(@PathVariable long productId) {
        Product product = productService.getProductById(productId);
        Message<Product> response = new Message<>();

        if (product != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(product);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Message<Product>> createProduct(@RequestPart List<MultipartFile> images, @RequestPart Product product) {
    	List<ProductImage> savedImagesFiles = new ArrayList<>();
    	product.setImageLinks(savedImagesFiles);
    	for (int i = 0; i < images.size(); i++) {
            MultipartFile image = images.get(i);
    		String key = awss3ServiceImp.uploadFile(image);
    		String url = awss3ServiceImp.getObjectUrl(key);
    		product.getImageLinks().add(new ProductImage(key,url));       
    	}
    	
    	Product createdProduct = productService.createProduct(product);
        Message<Product> response = new Message<>();

        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    

    @PutMapping("/{productId}")
    public ResponseEntity<Message<Product>> updateProduct(@PathVariable long productId, @RequestPart Product product, @RequestPart(required = false) List<MultipartFile> images) {
        if(images != null) {
        	List<ProductImage> storedList = productService.getProductById(productId).getImageLinks();
        	for (int i = 0; i < images.size(); i++) {
                MultipartFile image = images.get(i);
        		String key = awss3ServiceImp.uploadFile(image);
        		String url = awss3ServiceImp.getObjectUrl(key);
        		storedList.add(new ProductImage(key,url));       
        	}
        	product.setImageLinks(storedList);
        }
    	
    	Product updatedProduct = productService.updateProduct(productId, product);
        Message<Product> response = new Message<>();

        if (updatedProduct != null) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("success");
            response.setData(updatedProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("error: usuario no autorizado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
   @PostMapping("deleteImage/{productId}")
    public ResponseEntity<Message<Product>> deleteProductImage(@PathVariable long productId, @RequestParam String keyImage) {
    	Product product = productService.getProductById(productId);
    	List<ProductImage> newListImages =  new ArrayList<>();
    	for (int i = 0; i < product.getImageLinks().size(); i++) {
    		String storedKey = product.getImageLinks().get(i).getKeyImage();
    		if(!storedKey.equals(keyImage)) {
    			newListImages.add(product.getImageLinks().get(i));
    		}      
    	}
    	product.setImageLinks(newListImages);
    	Product updatedProduct = productService.updateProduct(productId, product);
        Message<Product> response = new Message<>();

        if (updatedProduct != null) {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("success");
            response.setData(updatedProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("error: usuario no autorizado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
   
   @PostMapping("/mobile")
   public ResponseEntity<Message<Product>> createProductMobile(@RequestBody Product product) {
   	List<ProductImage> savedImagesFiles = new ArrayList<>();
   	product.setImageLinks(savedImagesFiles);
   	Product createdProduct = productService.createProduct(product);
       Message<Product> response = new Message<>();

       response.setStatus(HttpStatus.CREATED.value());
       response.setMessage("success");
       response.setData(createdProduct);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<Message<List<Product>>> getProductsBySellerId(@PathVariable long sellerId) {
        List<Product> products = productService.getProductsBySellerId(sellerId);
        Message<List<Product>> response = new Message<>();

        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("success");
        response.setData(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
   
   @PostMapping("addImages/{productId}")
   public ResponseEntity<Message<Product>> addProductImages(@PathVariable long productId, @RequestPart List<MultipartFile> images) {
   	Product product = productService.getProductById(productId);
   
	for (int i = 0; i < images.size(); i++) {
        MultipartFile image = images.get(i);
		String key = awss3ServiceImp.uploadFile(image);
		String url = awss3ServiceImp.getObjectUrl(key);
		product.getImageLinks().add(new ProductImage(key,url));       
	}
   	Product updatedProduct = productService.updateProduct(productId, product);
       Message<Product> response = new Message<>();

       if (updatedProduct != null) {
           response.setStatus(HttpStatus.CREATED.value());
           response.setMessage("success");
           response.setData(updatedProduct);
           return ResponseEntity.status(HttpStatus.CREATED).body(response);
       } else {
           response.setStatus(HttpStatus.UNAUTHORIZED.value());
           response.setMessage("error: usuario no autorizado");
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
       }
   }
    

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<Message<List<Product>>> getProductsByCategory(@PathVariable String categoryName) {
        List<Product> products = productService.getProductsByCategory(categoryName);
        Message<List<Product>> response = new Message<>();

        if (products != null && !products.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(products);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no products found for the category");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Message<List<Product>>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.getProductsByName(name);
        Message<List<Product>> response = new Message<>();

        if (products != null && !products.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(products);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no products found for the given name");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/top-selling")
    public ResponseEntity<Message<List<Product>>> getTopSellingProducts(@RequestParam int limit) {
        List<Product> topSellingProducts = productService.getTopSellingProducts(limit);
        Message<List<Product>> response = new Message<>();

        if (!topSellingProducts.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(topSellingProducts);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no top selling products found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @GetMapping("/most-sold-categories")
    public ResponseEntity<Message<List<Map<String, Object>>>> getMostSoldCategories() {
        List<Map<String, Object>> mostSoldCategories = productService.getMostSoldCategories();
        Message<List<Map<String, Object>>> response = new Message<>();

        if (!mostSoldCategories.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("success");
            response.setData(mostSoldCategories);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("error: no categories found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
