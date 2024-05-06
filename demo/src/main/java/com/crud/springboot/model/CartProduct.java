package com.crud.springboot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
    @Id
    private String cartId;

    @NotNull(message = "products must be filled")
    @Size(min = 1, message = "At least one product must be present")
    private List<ProductOrder> products;
    private Date createdAt;
    private Date updatedAt;
}
