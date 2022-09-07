package com.study.controller;

import com.study.pojo.User;
import com.study.service.UserService;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody User user, HttpSession session){// 接收前端的JSON格式的User对象sout
        //System.out.println("进入到 login的接口之中!");
       Map<String,Object> map = new HashMap<>();

       try{
           // 用业务层的login方法，传递前端的use参数
           User logUser = userService.login(user);

           // 生成token信息，（可将sessionID作为 token信息）放入Redis中
           String token = session.getId();
           redisTemplate.opsForValue().set("token: "+token,logUser, 30, TimeUnit.MINUTES);

           // 将token返回给前端
           map.put("success",true);
           map.put("msg","登陆成功");
           map.put("token",token);

       }catch (Exception e){
           e.printStackTrace();
           map.put("success",false);
           map.put("msg",e.getMessage());
       }

        return map;
    }


    @GetMapping("/token")
    public User useToken(String token){
        return (User)redisTemplate.opsForValue().get("token: "+token);
    }

    @DeleteMapping("/token")
    public void logout(String token){
        System.out.println("删除token!");
        redisTemplate.delete("token: "+token);
    }

}
