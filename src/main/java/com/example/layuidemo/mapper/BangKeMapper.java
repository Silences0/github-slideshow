package com.example.layuidemo.mapper;

import com.example.layuidemo.entity.BangKe;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BangKeMapper {
    List<BangKe> findAllBangKeByStatusOrAll();
}
