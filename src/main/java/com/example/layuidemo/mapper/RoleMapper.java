package com.example.layuidemo.mapper;

import com.example.layuidemo.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int delRoleByUserId(int id);

    List<Role> getAllRoles(int start, int size);

    int getRoleCount();

    List<Role> getRoles();

    int addUserRole(int userId, int roleId);

    int addRole(Role role);

    int modifyRole(Role role);

    int delRole(Integer id);

}
