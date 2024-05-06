package com.crud.springboot.repository;

import com.crud.springboot.model.CartProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<CartProduct, String> {
}
