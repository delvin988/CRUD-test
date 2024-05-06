package com.crud.springboot.repository;

import com.crud.springboot.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {


     Product findByProductName(String productName);


}
