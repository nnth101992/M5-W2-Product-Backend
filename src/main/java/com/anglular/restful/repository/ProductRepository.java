package com.anglular.restful.repository;

import com.anglular.restful.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
