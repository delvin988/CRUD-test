package com.crud.springboot.service;

import com.crud.springboot.model.CartProduct;
import com.crud.springboot.model.Order;
import com.crud.springboot.model.OrderRequestBody;
import com.crud.springboot.model.ProductOrder;
import com.crud.springboot.repository.CartRepository;
import com.crud.springboot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrderFromCart(OrderRequestBody orderRequestBody) {
        CartProduct cartProduct = cartRepository.findById(orderRequestBody.getCartId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid cartId"));

        List<ProductOrder> products = cartProduct.getProducts();

        int totalOrderPrice = calculateTotalOrderPrice(products);

        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString().split("-")[0]);
        order.setCustomer(orderRequestBody.getCustomer());
        order.setAddress(orderRequestBody.getAddress());
        order.setProducts(products);
        order.setTotal(totalOrderPrice);
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());

        return orderRepository.save(order);
    }

    private int calculateTotalOrderPrice(List<ProductOrder> products) {
        int totalOrderPrice = 0;
        for (ProductOrder productOrder : products) {
            totalOrderPrice += productOrder.getTotalPrice();
        }
        return totalOrderPrice;
    }
}
