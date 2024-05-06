package com.crud.springboot.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestBody {

    @NotNull(message = "cartId must be filled")
    private String cartId;

    @NotNull(message = "customer must be filled")
    private String customer;

    @NotNull(message = "address must be filled")
    private String address;
}
