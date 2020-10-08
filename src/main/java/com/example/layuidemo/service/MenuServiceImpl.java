package com.example.layuidemo.service;

import com.example.layuidemo.entity.vo.Bar;
import com.example.layuidemo.entity.Menu;
import com.example.layuidemo.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuMapper menuMapper;

    @Override
    public List<Menu> getAllChildMenu(int parentId) {
        return menuMapper.getAllChildMenu(parentId);
    }

    @Override
    public List<Bar> getAllParentMenuByRole() {
        //SecurityContextHolder.getContext().getAuthentication()
        // 获取当前登录用户的信息
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        //当前登录用户所拥有的1级菜单
        List<Menu> menus = new ArrayList<>();
        List<Bar> bars = new ArrayList<>();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object[] roles = authentication.getAuthorities().toArray();
            for (Object role : roles) {
                //拿到该登录的用户所拥有的角色以及角色对应的1级菜单
                List<Menu> menus1 = menuMapper.getAllParentMenuByRole(role.toString());
                //把该角色下的所有1级菜单加入到集合中
                menus.addAll(menus1);
            }
            //遍历这些1级菜单下的二级菜单
            //合并后的集合去重
            Set<Menu> userSet = new HashSet<>(menus);
            List<Menu> menuDistinct = new ArrayList<>(userSet);
            for (int i=0;i<menuDistinct.size();i++){
                Menu menu = menuDistinct.get(i);
                Bar bar = new Bar();
                //1个bar对象里面就有一个1级菜单，并且有它对应的2级菜单的集合
                bar.setId(menu.getId());
                bar.setName(menu.getName());
                bar.setUrl(menu.getUrl());
                List<Menu> menus2 = this.getAllChildMenu(menu.getId());
                bar.setMenus(menus2);
                bars.add(bar);
            }
        }
        return bars;
    }

    @Override
    public int getMenuCount() {
        return menuMapper.getMenuCount();
    }

    @Override
    public List<Menu> getAllMenuByPage(int start,int size) {
        return menuMapper.getAllMenuByPage(start,size);
    }

    @Override
    public List<Menu> getAllParentMenu() {
        return menuMapper.getAllParentMenu();
    }

    @Override
    public int updateMenuAndRole(int roleId, int menuId) {
        return menuMapper.updateMenuAndRole(roleId,menuId);
    }

    @Override
    public int delRole_MenuByRid(Integer id) {

        return menuMapper.delRole_MenuByRid(id);
    }

    @Override
    public int delRole_MenuByMid(Integer id) {

        return menuMapper.delRole_MenuByMid(id);
    }

    @Override
    public int addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public int delMenu(Integer id) {
        return menuMapper.delMenu(id);
    }

    @Override
    public int delChildrenMenu(Integer id) {
        return menuMapper.delChildrenMenu(id);
    }


}
