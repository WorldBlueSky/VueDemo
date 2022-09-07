package com.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.mapper.UserMapper;
import com.study.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public User login(User user){

        // 设置条件构造器，查询条件 “username”= user.username
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());

        // 根据用户名查询用户
        User userDB = userMapper.selectOne(wrapper);

        // 如果没有查到的话，那么用户不存在
        if(userDB==null){
            throw new RuntimeException("用户名不存在!");
        }

        if(!userDB.getPassword().equals(user.getPassword())){
            throw new RuntimeException("用户密码匹配错误!");
        }

        // 如果匹配成功的话，那么返回登陆的这个User对象

        return userDB;

    }

}
