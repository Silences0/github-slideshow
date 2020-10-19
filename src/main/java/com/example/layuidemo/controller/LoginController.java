package com.example.layuidemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.layuidemo.entity.Menu;
import com.example.layuidemo.entity.Role;
import com.example.layuidemo.entity.vo.Bar;
import com.example.layuidemo.entity.User;
import com.example.layuidemo.entity.vo.UserVo;
import com.example.layuidemo.mapper.UserMapper;
import com.example.layuidemo.service.MenuService;
import com.example.layuidemo.service.RoleService;
import com.example.layuidemo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    @RequestMapping("login")
    public String login() {
        System.out.println("12313");
        return "views/user/login";
    }

    @RequestMapping("/system/user/adduser")
    public String adduser(Model model) {
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "/views/user/user/userform";
    }

    @RequestMapping("/system/addrole")
    public String addRole() {
        return "/views/system/roleform";
    }

    @RequestMapping("/system/rolemgr")
    public String rolemgr() {
        return "/views/system/role";
    }

    @RequestMapping("index")
    public String index(Model model) {
        List<Bar> bars = menuService.getAllParentMenuByRole();
        model.addAttribute("bars", bars);
        return "/views/index";
    }


    @RequestMapping("/home/console.html")
    public String console() {
        return "/views/home/console";
    }

    @RequestMapping("/system/menumgr")
    public String menumgr() {
        return "/views/system/menu";
    }

    @RequestMapping("/addMenu")
    public String index111(Model model) {
        List<Bar> bars = menuService.getAllParentMenuByRole();
        model.addAttribute("bars", bars);
        return "/views/system/addmenu";
    }

    @RequestMapping("/system/updatemenu")
    public String updateMenu(Model model) {
        List<Role> roles = roleService.getRoles();
        List<Menu> menus = menuService.getAllParentMenu();
        model.addAttribute("roles", roles);
        model.addAttribute("menus", menus);
        return "/views/system/menuform";
    }

    @RequestMapping("/system/user/list")
    public String userList() {
        return "/views/user/user/list";
    }

    /**
     * 商品列表展示页
     *
     * @return
     */
    @RequestMapping("/shop/productlist")
    public String productList() {
        return "/views/product/product";
    }

    @RequestMapping("/user/modify/{id}")
    public String modify(@PathVariable Integer id, Model model) {
        List<Role> roles = roleService.getRoles();
        List<User> allUser = userService.getAllUser(id, null, null);
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            for (Role role1 : allUser.get(0).getRoles()) {
                if (role.getId() == role1.getId()) {
                    roles.remove(role);
                }
            }
        }
        model.addAttribute("user", allUser.get(0));
        model.addAttribute("roles", roles);
        return "/views/user/user/modifyuser";
    }

    @RequestMapping("/yezhu/orderlist")
    public String orderlist() {
        return "/views/order/order";
    }

    @RequestMapping("/sendToBangKe/{name}")
    public String bangKe(@PathVariable String name, Model model) {
        model.addAttribute("bangKeName", name);
        return "/views/bangke/onlinechat";
    }

    @RequestMapping("")
    public String index() {

        return "";
    }
}