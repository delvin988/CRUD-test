package com.crud.springboot.controller;

import com.crud.springboot.model.Order;
import com.crud.springboot.model.OrderRequestBody;
import com.crud.springboot.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Order> addOrderFromCart(@RequestBody @Valid OrderRequestBody orderRequestBody) {
            Order newOrder = orderService.createOrderFromCart(orderRequestBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }
}
