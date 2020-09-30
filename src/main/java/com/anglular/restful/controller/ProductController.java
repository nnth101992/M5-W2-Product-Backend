package com.anglular.restful.controller;

import com.anglular.restful.model.Product;
import com.anglular.restful.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Iterable<Product>> listProducts() {
        Iterable<Product> products = productService.findAll();
        if (products == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/products/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, UriComponentsBuilder uriComponentsBuilder) {
        productService.save(product);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        Product currentProduct = productService.findById(id);
        if (currentProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentProduct.setName(product.getName());
        currentProduct.setDescription(product.getDescription());

        productService.save(product);
        return new ResponseEntity<>(currentProduct, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }



}
