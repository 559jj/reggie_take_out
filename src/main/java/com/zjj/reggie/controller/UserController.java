package com.zjj.reggie.controller;

import com.zjj.reggie.common.R;
import com.zjj.reggie.entity.User;
import com.zjj.reggie.service.UserService;
import com.zjj.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //获取手机号
        String phone= user.getPhone();

        if (StringUtils.isNotEmpty(phone)){
            //生成验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            //调用阿里云提供的短信服务API完成发送短信
           // SMSUtils.sendMessage("瑞吉外卖","",phone,code);
            log.info("code={}",code);
            //保存验证码到session中
            session.setAttribute(phone,code);
            return R.success("手机验证码发送成功");
        }
        return R.error("验证码发送失败");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){
        return userService.login(map,session);
    }
}
