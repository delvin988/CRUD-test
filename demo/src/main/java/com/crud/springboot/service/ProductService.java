package com.crud.springboot.service;

import com.crud.springboot.model.Product;
import com.crud.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product addProduct(Product product){
        product.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        return repository.save(product);
    }

    public Page<Product> findAllProduct(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return repository.findAll(pageable);
    }

    public Product getProductByProductId(String productId){
        return repository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public Product updateProduct(String productId,Product productRequest){
        Product existingProduct = repository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid productId"));

        existingProduct.setProductName(productRequest.getProductName());
        existingProduct.setType(productRequest.getType());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setUpdatedAt(new Date());
        return repository.save(existingProduct);
    }

    public ResponseEntity<String> deleteProduct(String productId){
        Product existingProduct = repository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid productId"));
        repository.deleteById(productId);
        return ResponseEntity.ok("Product with ID " + productId + " deleted from dashboard");
    }
}
