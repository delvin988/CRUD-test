package com.crud.springboot.service;

import com.crud.springboot.model.CartProduct;
import com.crud.springboot.model.Product;
import com.crud.springboot.model.ProductOrder;
import com.crud.springboot.repository.CartRepository;
import com.crud.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    private CartRepository repository;

    @Autowired
    private ProductRepository productRepository;

    public CartProduct addCart(CartProduct cartProduct){

        List<ProductOrder> products = cartProduct.getProducts();

        for (ProductOrder productOrder : products){
            String productCartName = productOrder.getProductName();
            int qty = productOrder.getQty();

            Product existingProduct = productRepository.findByProductName(productCartName);
            if (existingProduct == null){
                throw new IllegalArgumentException("Invalid product: " + productCartName);
            }
            int unitPrice = existingProduct.getPrice();
            int totalPrice = unitPrice * qty;

            productOrder.setTotalPrice(totalPrice);
            productOrder.setType(existingProduct.getType());
        }

        cartProduct.setCartId(UUID.randomUUID().toString().split("-")[0]);
        cartProduct.setCreatedAt(new Date());
        cartProduct.setUpdatedAt(new Date());

        return  repository.save(cartProduct);
    }
}
