package com.zjj.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjj.reggie.common.R;
import com.zjj.reggie.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService extends IService<User> {
    R<User> login(Map map, HttpSession session);

    R<String> sendMsg(User user);
}
