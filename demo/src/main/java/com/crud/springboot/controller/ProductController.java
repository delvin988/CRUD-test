package com.crud.springboot.controller;

import com.crud.springboot.model.Product;
import com.crud.springboot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createTask(@RequestBody @Valid Product product) {
        return service.addProduct(product);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getTasks(@RequestParam(defaultValue = "0") int pageNo,
                                                  @RequestParam(defaultValue = "10") int pageSize) {
        Page<Product> products = service.findAllProduct(pageNo, pageSize);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        Product productById = service.getProductByProductId(productId);
        return ResponseEntity.ok(productById);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> modifyTask(@PathVariable String productId, @RequestBody Product product) {
        Product updateProduct = service.updateProduct(productId, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteTask(@PathVariable String productId) {
        ResponseEntity<String> responseEntity = service.deleteProduct(productId);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }
}
