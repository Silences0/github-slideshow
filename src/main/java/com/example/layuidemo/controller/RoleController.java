package com.example.layuidemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.layuidemo.entity.Menu;
import com.example.layuidemo.entity.Role;
import com.example.layuidemo.service.MenuService;
import com.example.layuidemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @RequestMapping("/system/rolelist")
    public String roleList(String page,
                           String limit) {
        if (page == null) {
            page = "1";
        }
        if (limit == null) {
            limit = "5";
        }
        int size = Integer.parseInt(limit);
        int start = (Integer.parseInt(page) - 1) * size;
        //start起始位置  size页面容量
        //PageHelper.startPage(start,size,"id desc");
        List<Role> roles = roleService.getAllRoles(start, size);
        int count = roleService.getRoleCount();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        jsonObject.put("data", roles);
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/addroleobject")
    public String addRole(String roleName, String roleNameZH) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleNameZH(roleNameZH);
        return JSONObject.toJSONString(roleService.addRole(role));
    }

    @RequestMapping("/modifyRole")
    public String modifyRole(Integer id, String name, String nameZh) {
        Role role = new Role();
        role.setId(id);
        role.setRoleName(name);
        role.setRoleNameZH(nameZh);
        int i = roleService.modifyRole(role);
        return JSONObject.toJSONString(0);
    }

    @RequestMapping("/delRole")
    @ResponseBody
    public String index(int[] ids) {
        int result = 0;
        for (int id : ids) {
            menuService.delRole_MenuByRid(id);
            result += roleService.delRole(id);
        }
        return JSONObject.toJSONString(result);
    }
}
