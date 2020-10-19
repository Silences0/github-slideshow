package com.example.layuidemo.service;

import com.example.layuidemo.entity.Role;
import com.example.layuidemo.entity.User;
import com.example.layuidemo.entity.vo.UserVo;
import com.example.layuidemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }

    @Override
    public List<Role> getUserRolesByUid(int uid) {
        return userMapper.getUserRolesByUid(uid);
    }

    @Override
    public List<User> getAllUser(Integer id, String email, String username) {
        List<User> users = userMapper.getAllUser(id, email, username);
        /**
         * user对象
         * java.lang.NullPointerException: null
         * 	at com.example.layuidemo.entity.User.getAuthorities(User.java:133) ~[classes/:na]
         * 	at com.alibaba.fastjson.serializer.ASMSerializer_1_User.write(Unknown Source) ~[na:na]
         */
        for (int i = 0; i < users.size(); i++) {
            List<Role> roles = userMapper.getUserRolesByUid(users.get(i).getId());
            users.get(i).setRoles(roles);
        }
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updateMoney(BigDecimal money, Integer id) {
        return userMapper.updateMoeny(money, id);
    }

    @Override
    public int deleteUser(int[] ids) {
        return userMapper.deleteUser(ids);
    }

    @Override
    public int modifyUser(Integer id, String phone, String username, String password, String[] roles, String open) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setCreateBy(16);
        user.setModifyBy(16);
        user.setCreateDate(new Date());
        user.setModifyDate(new Date());
        user.setPhone(phone);
        if (open.equals("on")) {
            user.setIsenable(1);
            user.setIslocked(1);
        } else {
            user.setIsenable(0);
            user.setIslocked(0);
        }
        int result = userMapper.updateUserAll(user);
        if (result > 0) {
            // int userId = userMapper.getUidByName(user.getUsername());
            int[] ints = {user.getId()};
            roleService.deleteRoleByUserId(ints);
            for (int i = 0; i < roles.length; i++) {
                roleService.addUserRole(user.getId(), Integer.parseInt(roles[i]));
            }
        }
        return 0;
    }

    @Override
    public int addUser(String username, String password, String[] roles, String open,String phone) {
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(passwordEncoder.encode(password));
        user.setCreateBy(16);
        user.setModifyBy(16);
        user.setCreateDate(new Date());
        user.setModifyDate(new Date());
        user.setAmount(new BigDecimal(11000));
        if (open.equals("on")) {
            user.setIsenable(1);
            user.setIslocked(1);
        } else {
            user.setIsenable(0);
            user.setIslocked(0);
        }
        int result = userMapper.addUser(user);
        if (result > 0) {
            int userId = userMapper.getUidByName(user.getUsername());
            for (int i = 0; i < roles.length; i++) {
                roleService.addUserRole(userId, Integer.parseInt(roles[i]));
            }
        }
        return result;
    }

    @Override
    public int getCount(Integer id, String email, String username) {
        return userMapper.getCount(id, email, username);
    }

    @Override
    public String getUserNameById(int id) {
        return userMapper.getUserNameById(id);
    }

    @Override
    public List<UserVo> getAllUserVo(List<User> users) {
        List<UserVo> userVos = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            int createById = user.getCreateBy();
            String createBy = userMapper.getUserNameById(createById);
            int modifyById = user.getModifyBy();
            String modifyByIdBy = userMapper.getUserNameById(modifyById);
            String enale = "可以使用";
            String lock = "未被锁定";
            if (user.getIsenable() != 1) {
                enale = "不可使用";
            }
            if (user.getIslocked() != 1) {
                lock = "已被锁定";
            }
            String roles = "";
            List<Role> roleList = user.getRoles();
            for (int j = 0; j < roleList.size(); j++) {
                roles += roleList.get(j).getRoleNameZH();
            }
            UserVo userVo = new UserVo(user.getId(), user.getUsername(),
                    user.getPassword(), user.getAmount(), enale, lock, user.getCreateDate(),
                    user.getModifyDate(), createBy, modifyByIdBy, roles);
            userVos.add(userVo);
        }
        return userVos;
    }


    @Override
    public User findUserVo(String name) {
        return userMapper.getUserByName(name);
    }
}
