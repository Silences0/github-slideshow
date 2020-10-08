package com.example.layuidemo.mapper;

import com.example.layuidemo.entity.Role;
import com.example.layuidemo.entity.User;
import com.example.layuidemo.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);

    List<Role> getUserRolesByUid(int uid);

    int updateMoeny(@Param("moeny") BigDecimal moeny, @Param("id") Integer id);

    List<User> getAllUser(@Param("id") Integer id, @Param("email") String email, @Param("username") String username);

    User getUserById(Integer id);

    int updateUser(User user);

    int updateUserAll(User user);

    int deleteUser(@Param("ids") int[] ids);

    int addUser(User user);

    int getCount(@Param("id") Integer id, @Param("email") String email, @Param("username") String username);

    String getUserNameById(int id);

    int getUidByName(String username);

    User getUserByName(@Param("username") String username);
}
