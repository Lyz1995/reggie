package com.lyz.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyz.reggie.entity.User;
import com.lyz.reggie.service.UserService;
import com.lyz.reggie.service.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import com.lyz.reggie.common.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping ("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        String userPhone = user.getPhone();
        if(StringUtils.isNotEmpty(userPhone)) {
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);
            session.setAttribute(userPhone, code);
            return R.success("手机短信验证码发送成功");
        }
        return R.error("短信发送失败");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){

        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        Object sessionAttribute = session.getAttribute(phone);
        if(null != sessionAttribute && code.equals(sessionAttribute)){
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getPhone,phone);

            User one = userService.getOne(userLambdaQueryWrapper);
            if(null == one){
                one = new User();
                one.setPhone(phone);
                one.setStatus(1);
                userService.save(one);
            }
            session.setAttribute("user",one.getId());
            return R.success(one);
        }
        return R.error("验证失败");
    }
}
