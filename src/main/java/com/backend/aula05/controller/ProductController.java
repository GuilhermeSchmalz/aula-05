package com.backend.aula05.controller;

import com.backend.aula05.dto.ProductDto;
import com.backend.aula05.model.Product;
import com.backend.aula05.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productRepository.save(product));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProduct() {
        List<Product> products = productRepository
                .findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable UUID id) {
        Optional<Product> productOptional = productRepository
                .findById(id);
        return productOptional.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!")
                : ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
    }
}
