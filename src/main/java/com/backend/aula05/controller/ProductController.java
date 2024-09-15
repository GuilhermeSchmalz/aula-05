package com.backend.aula05.controller;

import com.backend.aula05.dto.ProductDto;
import com.backend.aula05.model.Product;
import com.backend.aula05.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }
}
