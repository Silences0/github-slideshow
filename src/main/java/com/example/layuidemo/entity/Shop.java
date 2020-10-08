package com.example.layuidemo.entity;

import java.util.Date;

public class Shop {
    /**
     * 'id',
     *     'shopName',
     *     'shopaddress',
     *     'createBy',
     *     'createDate',
     *     'modifyBy',
     *     'modifyDate'
     */
    private int id;
    private String shopName;
    private Date createDate;
    private Date modifyDate;
    private int createBy;
    private int modifyBy;

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", createBy=" + createBy +
                ", modifyBy=" + modifyBy +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public int getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
    }
}
