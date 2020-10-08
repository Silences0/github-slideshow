package com.example.layuidemo.service;

import com.example.layuidemo.entity.Shop;
import com.example.layuidemo.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopMapper shopMapper;
    @Override
    public List<Shop> getAllShopByPage(int page, int limit) {
        int start = (page-1)*limit;
        int size = limit;
        return shopMapper.getAllShopByPage(start,size);
    }

    @Override
    public Shop getShopById(int id) {
        return shopMapper.getShopById(id);
    }
}
