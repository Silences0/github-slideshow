package com.example.layuidemo.bangke;

import com.example.layuidemo.entity.BangKe;
import com.example.layuidemo.entity.User;
import com.example.layuidemo.mapper.BangKeMapper;
import com.example.layuidemo.service.OrderService;
import com.example.layuidemo.service.UserService;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class DirectReciver {
    @Resource
    BangKeMapper bangKeMapper;
    @Resource
    UserService userService;
    @Resource
    OrderService orderService;

    @RabbitListener(queues = "TestDirectQueue")
    public void process(Map testMessage) {
        Object o = testMessage.get("message");
        Object userId = testMessage.get("userId");
        User userById = userService.getUserById((int) userId);
        List<BangKe> allBangKeByStatusOrAll = bangKeMapper.findAllBangKeByStatusOrAll();
        Random random = new Random();
        int i = random.nextInt(allBangKeByStatusOrAll.size() );
        BangKe bangKe = allBangKeByStatusOrAll.get(i);
        ZhenziSmsClient zhenziSmsClient = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "106304", "e377f2e8-0b0a-4a00-a1f2-0497b2b95190");
        try {
            String[] templateParams = new String[3];
            templateParams[0] = ":" + o.toString();
            templateParams[1] = ":" + bangKe.getUsername();
            templateParams[2] = ":" + bangKe.getPhone();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("number", userById.getPhone());//userById.getPhone()
            map1.put("templateId", "1774");
            map1.put("templateParams", templateParams);
            String result = zhenziSmsClient.send(map1);
            System.out.println(result);
            orderService.updateBangKe((int) o, bangKe.getId());
            System.out.println("欢迎使用牛中牛服务系统：" +
                    "帮客信息：订单编号为" + o + "，帮客姓名为" + bangKe.getUsername() + "，帮客手机号为" + bangKe.getPhone() + ",帮客已经成功接单。");
            try {
                Thread.sleep(60000);
                int i1 = orderService.updateStatus((int) o);
                System.out.println("订单信息已经转变成功!" + i1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}