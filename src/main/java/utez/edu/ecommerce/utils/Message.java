package utez.edu.ecommerce.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Message<T> {
    private int status;
    private String message;
    private T data;

    public Message(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Message() {
    }
}

