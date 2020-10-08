package com.example.layuidemo.entity;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Document(indexName = "bangke", type = "product")
public class Product implements Serializable {
    /**
     * 'id',
     * 'productName',
     * 'productPrice',
     * 'productShopId',
     * 'productType',
     * 'productStack',
     * 'createDate',
     * 'createBy',
     * 'ModifyDate',
     * 'ModifyBy'
     */
    private int id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String productName;
    private BigDecimal productPrice;
    private int productShopId;
    private int productType;
    private int productStack;
    private int createBy;
    private int ModifyBy;
    private Date createDate;
    private Date ModifyDate;
    //页面展示字段
    private String typeName;
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productShopId=" + productShopId +
                ", productType=" + productType +
                ", productStack=" + productStack +
                ", createBy=" + createBy +
                ", ModifyBy=" + ModifyBy +
                ", createDate=" + createDate +
                ", ModifyDate=" + ModifyDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductShopId() {
        return productShopId;
    }

    public void setProductShopId(int productShopId) {
        this.productShopId = productShopId;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public int getProductStack() {
        return productStack;
    }

    public void setProductStack(int productStack) {
        this.productStack = productStack;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public int getModifyBy() {
        return ModifyBy;
    }

    public void setModifyBy(int modifyBy) {
        ModifyBy = modifyBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return ModifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        ModifyDate = modifyDate;
    }
}
