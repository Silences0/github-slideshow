package com.example.layuidemo.service;

import com.example.layuidemo.entity.Order;
import com.example.layuidemo.entity.Product;

import java.util.List;

public interface OrderService {
    int createOrder(Order order);

    List<Product> delKuCun(Integer id, Integer num);

    int updateBangKe(Integer id, Integer bangKeId);

    List<Order> findOrderByUserName(String name);
    List<Order> findOrderByBangKeName(String name);
    int updateStatus(Integer id);
}
