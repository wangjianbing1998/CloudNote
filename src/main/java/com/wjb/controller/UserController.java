package com.wjb.controller;

import com.wjb.dao.UserDao;
import com.wjb.model.UserEntity;
import com.wjb.utils.KVEntity;
import com.wjb.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")//Contoller下所有接口统一入口
public class UserController {
    //映射一个action
    UserDao userDao = new UserDao();
    @RequestMapping(value = "/user")
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转，本例是为了写接口，这里直接返回json
    public List<UserEntity> getUser() {

        return userDao.query("from UserEntity order by id desc ",null);
    }
    @ResponseBody
    @RequestMapping(value="/login")
    public String dologin(@RequestParam String username,@RequestParam String password){
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        parameter.add(new KVEntity("username", username));
        parameter.add(new KVEntity("password", MD5Util.getMd5(password)));
        List<UserEntity>list= userDao.query("select user from UserEntity user where username = :username and password= :password",parameter);
        int user_id=list.get(0).getId();
        return String.valueOf(user_id);
    }
    @ResponseBody
    @RequestMapping(value = "/register")
    public String doRegister(@RequestParam String username,@RequestParam String password){
        List<KVEntity> parameter=new ArrayList<KVEntity>();
        boolean result = userDao.insert(new UserEntity(0, username, MD5Util.getMd5(password)));
        return String.valueOf(result);
    }
}
