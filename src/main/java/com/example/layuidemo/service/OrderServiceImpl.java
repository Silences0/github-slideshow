package com.example.layuidemo.service;

import com.example.layuidemo.dao.ProductDao;
import com.example.layuidemo.entity.Order;
import com.example.layuidemo.entity.Product;
import com.example.layuidemo.mapper.OrderMapper;
import com.example.layuidemo.mapper.ProductMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;
    @Resource
    RabbitTemplate rabbitTemplate;
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductService productService;
    @Resource
    ProductMapper productMapper;

    @Override
    public int createOrder(Order order) {
        //创建订单先去查redis里面商品的库存信息，判断库存是否充足

        orderMapper.createOrder(order);
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("message", order.getId());
        map.put("createTime", createTime);
        map.put("userId", order.getUserId());
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        try {
            rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        } catch (Exception e) {
            System.out.println("信息无法使用");
        }
        return 1;
    }

    @Override
    @CachePut(cacheNames = "productAll", key = "1")
    public List<Product> delKuCun(Integer id, Integer num) {
        orderMapper.ModifyKuCun(id, num);
        List<Product> allProductByPage = productMapper.getAllProductByPage();
        for (Product product : allProductByPage) {
            if (product.getId() == id) {
                productDao.save(product);
                break;
            }
        }
        return allProductByPage;
    }

    @Override
    public int updateBangKe(Integer id, Integer bangKeId) {
        return orderMapper.updateBangKe(id, bangKeId);
    }

    @Override
    public List<Order> findOrderByUserName(String name) {
        return orderMapper.findOrderByUserName(name);
    }

    @Override
    public List<Order> findOrderByBangKeName(String name) {

        return orderMapper.findOrderByBangKeName(name);
    }

    @Override
    public int updateStatus(Integer id) {

        return orderMapper.updateStatus(id);
    }
}