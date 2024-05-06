package com.crud.springboot.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String orderId;

    @NotNull(message = "customer must be filled")
    private String customer;

    @NotNull(message = "address must be filled")
    private String address;
    private List<ProductOrder> products;
    private int total;
    private Date createdAt;
    private Date updatedAt;
}
