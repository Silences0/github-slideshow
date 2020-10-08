package com.example.layuidemo.service;

import com.example.layuidemo.entity.Product;
import com.example.layuidemo.mapper.ProductMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ProductMapper productMapper;
    @Resource
    OrderService orderService;
    @Cacheable(cacheNames = "productAll",key = "1")
    public List<Product> getAllProductByPage() {
        System.out.println("122222222222222");
        return productMapper.getAllProductByPage();
    }
    @Override
    public Product findProductById(Integer id) {
        System.out.println("=======================");
        return productMapper.findProductById(id);
    }




}