package com.example.layuidemo.service;

import com.example.layuidemo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProductByPage();
    Product findProductById(Integer id);
}
