package com.example.layuidemo.mapper;

import com.example.layuidemo.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    int createOrder(Order order);
    int ModifyKuCun(@Param("id") Integer id , @Param("num") Integer num);
    int updateBangKe(@Param("id") Integer id , @Param("bangKeId") Integer bangKeId);
    List<Order> findOrderByUserName(@Param("name") String name);
    int updateStatus(Integer id);
    List<Order> findOrderByBangKeName(String name);
}
