package utez.edu.ecommerce.dto;

import lombok.Data;
import utez.edu.ecommerce.entity.User;

@Data
public class AuthResponseDTO {
    private User user;
    private String accessToken;
    private int status;
    private String tokenType = "Bearer";

    public AuthResponseDTO(String accessToken, User user, int status) {
        this.accessToken = accessToken;
        this.user = user;
        this.status = status;
    }
}