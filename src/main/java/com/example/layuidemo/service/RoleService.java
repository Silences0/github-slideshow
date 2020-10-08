package com.example.layuidemo.service;

import com.example.layuidemo.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles(int start, int size);

    int getRoleCount();

    List<Role> getRoles();

    int addUserRole(int userId, int roleId);

    int addRole(Role role);

    int deleteRoleByUserId(int [] id);

    int modifyRole(Role role);

    int delRole(Integer id);
}
