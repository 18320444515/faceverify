package com.yigong.faceverify.controller;

import com.yigong.faceverify.VO.ResultVO;
import com.yigong.faceverify.constant.RedisConstant;
import com.yigong.faceverify.entity.User;
import com.yigong.faceverify.repository.UserRepository;
import com.yigong.faceverify.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author tianyi
 * @date 2018-02-28 15:57
 */
@Controller
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO userLogin(@RequestParam("userName")String userName, @RequestParam("passWord")String passWord){
        log.info("into toLogin()");
        User user=userRepository.findByName(userName);
        if (user==null){
            log.info("用户不存在！");
            return ResultVOUtil.error(1404,"用户不存在！");
        }else {
            if (!passWord.equals(user.getPassword())){
                log.info("密码错误！");
                return ResultVOUtil.error(111,"密码错误！");
            }else {
                //2. 设置token至redis
                String tokenUU = UUID.randomUUID().toString();
                Integer expire = RedisConstant.EXPIRE;
                String tokenName=String.format(RedisConstant.TOKEN_PREFIX, tokenUU);
                redisTemplate.opsForValue().set(tokenName, String.valueOf(user.getUserId()), expire, TimeUnit.SECONDS);
                log.info("【tokenName in LoginController】"+tokenName);
                String appendUrl = "?tokenName=" + tokenName+"&Jumpto="+"centre";

                return ResultVOUtil.successLogin(tokenName,user);
                //前一个版本 return "redirect:http://127.0.0.1:8080/cookie/set/" + appendUrl;
                // TODO 欠整理
            }
        }
    }

}
