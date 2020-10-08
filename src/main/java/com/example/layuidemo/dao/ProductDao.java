package com.example.layuidemo.dao;

import com.example.layuidemo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDao extends ElasticsearchRepository<Product, Integer> {
}
