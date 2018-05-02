package com.yigong.faceverify.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author tianyi
 * @date 2018-02-12 17:49
 */
@Controller
@RequestMapping(value = "/jump")
@Slf4j
public class JumpController {

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    @ResponseBody
    public String toHome(){
        return "home";
    }

    @RequestMapping(value = "/centre",method = RequestMethod.GET)
    public String toCentre(HttpServletRequest request){
        Cookie[] cookie=request.getCookies();
        log.info(cookie.toString());
        return "centre";
    }

}
