package utez.edu.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
	@Column(name = "keyImage", nullable = false, length = 255)
    private String keyImage;
    @Column(name = "url", nullable = false, length = 255)
    private String url;
}
