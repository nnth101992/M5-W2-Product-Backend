package com.anglular.restful.service;

import com.anglular.restful.model.Product;

public interface ProductService {
    Iterable<Product> findAll();
    Product findById(Long id);
    void save(Product product);
    void remove(Long id);
}
