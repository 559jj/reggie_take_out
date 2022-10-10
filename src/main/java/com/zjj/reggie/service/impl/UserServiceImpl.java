package com.zjj.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjj.reggie.common.R;
import com.zjj.reggie.entity.User;
import com.zjj.reggie.mapper.UserMapper;
import com.zjj.reggie.service.UserService;
import com.zjj.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.util.NetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public R<String> sendMsg(User user) {
        //获取手机号
        String phone= user.getPhone();

        if (StringUtils.isNotEmpty(phone)){
            //生成验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            //调用阿里云提供的短信服务API完成发送短信
            // SMSUtils.sendMessage("瑞吉外卖","",phone,code);
            log.info("code={}",code);
            //保存验证码到session中
            //session.setAttribute(phone,code);
            stringRedisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.success("手机验证码发送成功");
        }
        return R.error("验证码发送失败");
    }
    @Override
    public R<User> login(Map map, HttpSession session) {
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();
        //Object codeInSession = session.getAttribute(phone);
        Object codeInSession = stringRedisTemplate.opsForValue().get(phone);
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
            stringRedisTemplate.delete(phone);
            return R.success(user);

        }
        return R.error("登陆失败");
    }

}
