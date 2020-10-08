package com.example.layuidemo.service;

import com.example.layuidemo.entity.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> getAllShopByPage(int page, int limit);
    Shop getShopById(int id);
}
