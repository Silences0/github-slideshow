package com.example.layuidemo;

import com.example.layuidemo.dao.ProductDao;
import com.example.layuidemo.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testMain {
    @Autowired
    ProductDao productDao;

    @Test
    public void context() {
        Random random=new Random();
        int i = random.nextInt(2);
        System.out.println(i);
    }


    @Test
    public void text1() {
        Optional<Product> byId = productDao.findById(2);
        Product product = byId.get();
        product.setProductStack(20);
        productDao.save(product);
    }
}
