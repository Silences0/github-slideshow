package com.example.layuidemo.mapper;

import com.example.layuidemo.entity.Remark;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RemarkMapper {
    List<Remark> findAllRemark(Integer id);
    int addRemark(Remark remark);
}
