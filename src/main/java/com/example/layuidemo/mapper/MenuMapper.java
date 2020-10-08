package com.example.layuidemo.mapper;

import com.example.layuidemo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
    int delMenu(Integer id);

    int delChildrenMenu(Integer id);

    //security权限框架查的菜单集合
    List<Menu> getAllMenu();

    //菜单列表查的菜单集合
    List<Menu> getAllMenuByPage(@Param("start") int start, @Param("size") int size);

    List<Menu> getAllChildMenu(int parentId);

    List<Menu> getAllParentMenuByRole(String roleName);

    int getMenuCount();

    //菜单管理也查询所有父类菜单
    List<Menu> getAllParentMenu();

    //更新权限角色关系表
    int updateMenuAndRole(int roleId, int menuId);

    int delRole_MenuByRid(Integer id);

    int addMenu(Menu menu);

    int delRole_MenuByMid(Integer id);


}
