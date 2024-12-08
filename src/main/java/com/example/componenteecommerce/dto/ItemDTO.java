package com.example.componenteecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    @NotNull
    private ProductDTO product;
    @NotNull
    @Range(min = 1, message = "The quantity must be greater than 0")
    private Integer quantity;

}
