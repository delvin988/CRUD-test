package com.crud.springboot.controller;

import com.crud.springboot.model.CartProduct;
import com.crud.springboot.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartProduct> addCart(@RequestBody @Valid CartProduct cartProduct){
        try {
            CartProduct addedCartProduct = cartService.addCart(cartProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedCartProduct);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
