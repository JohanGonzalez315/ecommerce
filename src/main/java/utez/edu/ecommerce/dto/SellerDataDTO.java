package utez.edu.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import utez.edu.ecommerce.entity.User;
@Data
@AllArgsConstructor
public class SellerDataDTO {
	private User seller;
    private String rfc;
    private String shopType;
    //private String status = "pendiente";
}

