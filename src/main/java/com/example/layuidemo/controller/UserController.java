package com.example.layuidemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.layuidemo.entity.User;
import com.example.layuidemo.entity.vo.UserVo;
import com.example.layuidemo.service.RoleService;
import com.example.layuidemo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @RequestMapping("adduserobject")
    public String addUser(String username, String password, String[] roles, String open) {
        int result = userService.addUser(username, password, roles, open);
        return JSON.toJSONString(result);
    }

    @RequestMapping("modifyUser")
    public String modify(Integer id,String username, String phone, String password, String[] roles, String open) {
        int result = userService.modifyUser(id,phone,username, password, roles, open);
        return JSON.toJSONString(result);
    }

    //layui分页插件传递过来的2个参数 page代表当前页码，从1开始
    //limit 页面大小 默认10email: ""
    //id: "1"
    //sex: "0"
    //username: ""
    @RequestMapping(value = "/user/userlist")
    @ResponseBody
    public JSONObject userList1(Integer id, String email, String username, String page, String limit) {
        if (page == null) {
            page = "1";
        }
        if (limit == null) {
            limit = "5";
        }
        int size = Integer.parseInt(limit);
        int start = (Integer.parseInt(page) - 1) * size;
        //start起始位置  size页面容量
        PageHelper.startPage(start, size, "id desc");
        List<User> users = userService.getAllUser(id, email, username);
        List<UserVo> list = userService.getAllUserVo(users);
        //总记录数
        int count = userService.getCount(id, email, username);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public JSONObject deleteUser(@Param("ids") int[] ids) {
        roleService.deleteRoleByUserId(ids);
        userService.deleteUser(ids);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "删除成功");
        jsonObject.put("data", "success");
        return jsonObject;
    }
}