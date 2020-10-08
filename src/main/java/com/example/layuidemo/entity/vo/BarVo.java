package com.example.layuidemo.entity.vo;

import java.util.List;

public class BarVo {
    /*"id":"001","title": "湖南省","parentId": "0","children":[]*/
    private int id;
    private String title;
    private Integer parentId;
    private List<MenuVo> children;

    public BarVo(int id, String title, Integer parentId, List<MenuVo> children) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.children = children;
    }

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


}
