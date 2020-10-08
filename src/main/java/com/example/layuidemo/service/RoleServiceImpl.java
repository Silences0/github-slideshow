package com.example.layuidemo.service;

import com.example.layuidemo.entity.Role;
import com.example.layuidemo.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getAllRoles(int start, int size) {
        return roleMapper.getAllRoles(start, size);
    }

    @Override
    public int getRoleCount() {
        return roleMapper.getRoleCount();
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.getRoles();
    }

    @Override
    public int addUserRole(int userId, int roleId) {
        return roleMapper.addUserRole(userId, roleId);
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int deleteRoleByUserId(int[] id) {
        int result = 0;
        for (int i : id) {
            result += roleMapper.delRoleByUserId(i);
        }
        return result;
    }

    @Override
    public int modifyRole(Role role) {
        return roleMapper.modifyRole(role);
    }

    @Override
    public int delRole(Integer id) {
        return roleMapper.delRole(id);
    }
}
