package com.example.layuidemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.layuidemo.entity.Menu;
import com.example.layuidemo.entity.Role;
import com.example.layuidemo.entity.vo.Bar;
import com.example.layuidemo.entity.vo.BarVo;
import com.example.layuidemo.entity.vo.MenuVo;
import com.example.layuidemo.service.MenuService;
import com.example.layuidemo.service.RoleService;
import com.example.layuidemo.util.ResponseResult;
import com.github.pagehelper.PageHelper;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuController {
    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/modifyMenu")
    @ResponseBody
    public String index(Integer parentId, String name, String url) {
        Menu menu = new Menu();
        menu.setParentId(parentId);
        if (parentId == 0) {
            menu.setMenuType("一级菜单");
        } else {
            menu.setMenuType("二级菜单");
        }
        menu.setName(name);
        menu.setUrl(url);
        menuService.addMenu(menu);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "0");
        jsonObject.put("msg", "操作成功");
        return jsonObject.toJSONString();
    }

    @RequestMapping("/delMenu")
    @ResponseBody
    public String delMenu(Integer[] ids) {
        for (Integer id : ids) {
            menuService.delRole_MenuByMid(id);
            menuService.delMenu(id);
            menuService.delChildrenMenu(id);
        }
        return "0";
    }

    @RequestMapping("/system/menulist")
    public String menuList(String page,
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
        List<Menu> menus = menuService.getAllMenuByPage(start, size);
        int count = menuService.getMenuCount();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        jsonObject.put("data", menus);
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/updatemenu")
    public String updateMenu(String role, String[] menus) {
        int result = 0;
        for (int i = 0; i < menus.length; i++) {
            result = menuService.updateMenuAndRole(Integer.parseInt(role), Integer.parseInt(menus[i]));
        }
        return "success";
    }
}
