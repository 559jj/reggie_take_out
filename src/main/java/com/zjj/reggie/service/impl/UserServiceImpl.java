package com.zjj.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjj.reggie.common.R;
import com.zjj.reggie.entity.User;
import com.zjj.reggie.mapper.UserMapper;
import com.zjj.reggie.service.UserService;
import org.apache.http.util.NetUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public R<User> login(Map map, HttpSession session) {
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();
        Object codeInSession = session.getAttribute(phone);
        if (codeInSession!=null && codeInSession.equals(code)){
            LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,map.get("phone"));
            User user = getOne(queryWrapper);
            if (user==null){
                user=new User();
                user.setPhone(phone);
                user.setStatus(1);
                save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success(user);

        }
        return R.error("登陆失败");
    }
}
