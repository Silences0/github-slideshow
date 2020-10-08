package com.example.layuidemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.layuidemo.entity.Order;
import com.example.layuidemo.entity.Product;
import com.example.layuidemo.entity.User;
import com.example.layuidemo.entity.vo.UserVo;
import com.example.layuidemo.service.OrderService;
import com.example.layuidemo.service.ProductService;
import com.example.layuidemo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    ProductService productService;
    @Resource
    UserService userService;

    @RequestMapping("/order/list")
    public JSONObject orderList(Principal principal, String page, @RequestParam(required = false, defaultValue = "10") int limit, String id) {
        if (page == null) {
            page = "1";
        }
        int start = (Integer.parseInt(page) - 1) * limit;
        JSONObject jsonObject = new JSONObject();
        PageHelper.startPage(start, limit);
        List<Order> orderByUserName = orderService.findOrderByUserName(principal.getName());
        PageInfo pageInfo = new PageInfo<>(orderByUserName);
        jsonObject.put("data", pageInfo.getList());
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", pageInfo.getTotal());
        return jsonObject;
    }

    @RequestMapping("/order/create")
    @ResponseBody
    public String orderCreate(int num, int pid, Principal principal) {
        String name = principal.getName();
        User user = userService.findUserVo(name);
        List<Product> products = productService.getAllProductByPage();
        for (Product product : products) {
            if (product.getId() == pid) {
                if (user.getAmount().compareTo(product.getProductPrice().multiply(BigDecimal.valueOf(num))) == -1) {
                    return "1";
                } else {
                    if (product.getProductStack() >= num) {
                        orderService.delKuCun(product.getId(), -num);
                        int i = userService.updateMoney(product.getProductPrice().multiply(BigDecimal.valueOf(num)).negate(), user.getId());
                        Order order = new Order();
                        order.setUserId(user.getId());
                        order.setProductNum(num);
                        order.setCreateBy(user.getId());
                        order.setProductName(product.getProductName());
                        BigDecimal productPrice = product.getProductPrice();
                        order.setProductPrice(productPrice.multiply(BigDecimal.valueOf(num)));
                        order.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                        order.setUseraddress("阿巴阿巴阿巴");
                        orderService.createOrder(order);
                        return "0";
                    } else {
                        return "2";
                    }
                }
            }
        }
        /**
         * 创建订单：把商品信息以及库存数量存入redis list set
         * 下单后查询redis该商品的库存，并判断库存后再执行下单业务逻辑
         * 下单成功后，将下单信息发送到rabbitmq消息队列，
         * 通过websockt通知空闲的帮客处理订单，把订单信息的通知
         * 通过短信的方式发送给帮客。帮客接单确认成功后再更新订单表里面的bangkeId字段
         * 订单处理完毕之后，给出相应评价，评分，以及佣金结算。
         * 后续继续优化商品秒杀功能
         */
        return "3";
    }
}