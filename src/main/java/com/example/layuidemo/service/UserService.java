package com.example.layuidemo.service;

import com.example.layuidemo.entity.Role;
import com.example.layuidemo.entity.User;
import com.example.layuidemo.entity.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    List<UserVo> getAllUserVo(List<User> users);

    int modifyUser(Integer id,String phone,String username, String password, String[] roles, String open);

    User findUserVo(String name);

    List<Role> getUserRolesByUid(int uid);

    List<User> getAllUser(Integer id, String email, String username);

    User getUserById(Integer id);

    int updateUser(User user);

    int updateMoney(BigDecimal money, Integer id);

    int deleteUser(@Param("ids") int[] ids);

    int addUser(String username, String password, String[] roles, String open);

    int getCount(Integer id, String email, String username);

    String getUserNameById(int id);
}
