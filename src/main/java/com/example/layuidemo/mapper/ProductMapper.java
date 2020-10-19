package com.example.layuidemo.mapper;

import com.example.layuidemo.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getAllProductByPage();

    Product findProductById(Integer id);
    Product findProductByName(String name);


}
