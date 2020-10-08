package com.example.layuidemo.mapper;

import com.example.layuidemo.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {
    List<Shop> getAllShopByPage(int start,int size);
    Shop getShopById(int id);
}
