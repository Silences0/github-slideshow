package com.example.layuidemo.config;

import com.example.layuidemo.entity.Menu;
import com.example.layuidemo.entity.Role;
import com.example.layuidemo.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 动态权限配置，自定义配置类
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource
    implements FilterInvocationSecurityMetadataSource {

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    MenuMapper menuMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation)obj).getRequestUrl();
        List<Menu> allMenus = menuMapper.getAllMenu();
        for (Menu menu:allMenus){
            if(antPathMatcher.match(menu.getUrl(),requestUrl)){
                List<Role> roles = menu.getRoles();
                String[] roleArry = new String[roles.size()];
                for(int i=0;i<roleArry.length;i++){
                    roleArry[i] = roles.get(i).getRoleName();
                }
                return SecurityConfig.createList(roleArry);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
