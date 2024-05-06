package com.crud.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {
    private String productName;
    private String type;
    private int qty;
    private int totalPrice;
}