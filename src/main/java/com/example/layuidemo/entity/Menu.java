package com.example.layuidemo.entity;

import java.util.List;

public class Menu {
    private int id;
    private String name;
    private int parentId;
    private String url;
    private String menuType;

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    private List<Role> roles;
    //对比这2个对象的name属性值是否相等
    public boolean equals(Object object){
        Menu menu = (Menu)object;
        return name.equals(menu.name);
    }
    //hash值是否相等
    public int hashCode(){
        String in = name;
        return in.hashCode();
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
