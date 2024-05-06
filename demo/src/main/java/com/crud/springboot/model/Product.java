package com.crud.springboot.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String taskId;

    @NotNull(message = "productName must be filled")
    private String productName;

    @NotNull(message = "type must be filled")
    private String type;

    @NotNull(message = "price must be filled")
    @Min(value = 1, message = "price must be greater than 0")
    private int price;
    private Date createdAt;
    private Date updatedAt;
}
