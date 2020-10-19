package com.example.layuidemo.controller;

import com.example.layuidemo.entity.Product;
import com.example.layuidemo.entity.Remark;
import com.example.layuidemo.entity.User;
import com.example.layuidemo.mapper.ProductMapper;
import com.example.layuidemo.mapper.RemarkMapper;
import com.example.layuidemo.service.ProductService;
import com.example.layuidemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RemarkController {
    @Resource
    RemarkMapper remarkMapper;
    @Resource
    UserService userService;
    @Resource
    ProductMapper productMapper;

    @RequestMapping("/lookRemark/{id}")
    public String index1(@PathVariable Integer id, Model model) {
        List<Remark> allRemark = remarkMapper.findAllRemark(id);
        model.addAttribute("remarks", allRemark);
        return "/views/order/lookremark";
    }

    @RequestMapping("/addRemark")
    public String index(String productName, Model model) {
        model.addAttribute("id", productName);
        return "/views/order/addremark";
    }

    @RequestMapping("/InsertRemark")
    @ResponseBody
    public Integer index(Remark remark, Principal principal) {
        String name = principal.getName();
        User userVo = userService.findUserVo(name);
        Product productByName = productMapper.findProductByName(remark.getProductName());
        remark.setProductId(productByName.getId());
        remark.setCreattime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        remark.setUserId(userVo.getId());
        int i = remarkMapper.addRemark(remark);
        return i;
    }
}
