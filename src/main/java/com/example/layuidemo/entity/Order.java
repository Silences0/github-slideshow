package com.example.layuidemo.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private int id;
    private Integer productNum;
    private BigDecimal productPrice;
    private String productName;
    private String useraddress;
    private int bangkeid;
    private String username;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBangKeName() {
        return bangKeName;
    }

    public void setBangKeName(String bangKeName) {
        this.bangKeName = bangKeName;
    }

    private String bangKeName;
    private String createDate;
    private Integer userId;
    private Integer createBy;
    private String modifyDate;
    private String status;
    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        if (statusName.equals("0")){
            this.statusName = "未完成";
        }else {
            this.statusName = "已完成";
        }
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        setStatusName(status);
    }

    private Integer modifyBy;

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public int getBangkeid() {
        return bangkeid;
    }

    public void setBangkeid(int bangkeid) {
        this.bangkeid = bangkeid;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }


    public Integer getModifyBy() {
        return modifyBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }


}
