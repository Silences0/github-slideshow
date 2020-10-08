package com.example.layuidemo.entity.vo;

public class MenuVo {
    /*"id":"001001",
			"title": "长沙市",
			"last":true,
			"parentId": "001"*/
    private Integer id;
    private String title;
    private boolean last;
    private Integer parentId;

    public MenuVo(Integer id, String title, boolean last, Integer parentId) {
        this.id = id;
        this.title = title;
        this.last = last;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
