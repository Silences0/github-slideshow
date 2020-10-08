package com.example.layuidemo.service;

import com.example.layuidemo.entity.vo.Bar;
import com.example.layuidemo.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAllChildMenu(int parentId);

    List<Bar> getAllParentMenuByRole();

    int getMenuCount();

    //菜单列表查的菜单集合
    List<Menu> getAllMenuByPage(int start, int size);

    //菜单管理也查询所有父类菜单
    List<Menu> getAllParentMenu();

    //更新权限角色关系表
    int updateMenuAndRole(int roleId, int menuId);

    int delRole_MenuByRid(Integer id);

    int delRole_MenuByMid(Integer id);

    int addMenu(Menu menu);

    int delMenu(Integer id);

    int delChildrenMenu(Integer id);
}
