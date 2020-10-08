package com.example.layuidemo.controller;

import com.example.layuidemo.dao.ProductDao;
import com.example.layuidemo.entity.Product;
import com.example.layuidemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExploController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductDao productDao;
    @RequestMapping("/export")
    public String index() {
        List<Product> allProductByPage = productService.getAllProductByPage();
        for (Product product : allProductByPage) {
            Product save = productDao.save(product);
        }
        return allProductByPage.toString();
    }
}
