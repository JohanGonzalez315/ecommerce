package utez.edu.ecommerce.dto;

import lombok.Data;
import utez.edu.ecommerce.entity.User;

@Data
public class OrderItemDTO<T> {
    private int status;
    private String message;
    private int data;

    public OrderItemDTO(int status, String message, int data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public OrderItemDTO() {
    }
}

