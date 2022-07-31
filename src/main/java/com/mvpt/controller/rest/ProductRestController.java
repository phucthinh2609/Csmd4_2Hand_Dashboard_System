package com.mvpt.controller.rest;

import com.mvpt.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @GetMapping()
    public ResponseEntity<?> getAll() {
        Iterable<Product> productList = null;

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = Optional.empty();

        return new ResponseEntity<>(productOptional, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> doUpdate(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
