package com.example.layuidemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.layuidemo.entity.Order;
import com.example.layuidemo.mapper.BangKeMapper;
import com.example.layuidemo.service.OrderService;
import com.example.layuidemo.service.UserService;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BangKeController {
    @Autowired
    OrderService orderService;
    ZhenziSmsClient zhenziSmsClient = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "106304", "e377f2e8-0b0a-4a00-a1f2-0497b2b95190");
    @Resource
    UserService userService;

    @RequestMapping("/bangke/listinfo")
    public String index() {
        return "/views/bangke/bangkeorder";
    }

    @RequestMapping("/bangKe/orderList")
    @ResponseBody
    public JSONObject index1(Principal principal) {
        List<Order> orderByBangKeName = orderService.findOrderByBangKeName(principal.getName());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", orderByBangKeName);
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        return jsonObject;
    }

    @RequestMapping("/overOrder/{id}/{phone}/{bangKeId}")
    @ResponseBody
    public String index3(@PathVariable Integer id, @PathVariable String phone, @PathVariable String bangKeId) {
        orderService.updateStatus(id);
        Map<String, Object> map = new HashMap<>();
        map.put("number", phone);//userById.getPhone()
        map.put("templateId", "1817");
        userService.updateMoney(BigDecimal.valueOf(5), Integer.parseInt(bangKeId));
        try {
            String result1 = zhenziSmsClient.send(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
