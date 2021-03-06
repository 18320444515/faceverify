package com.yigong.faceverify.aspect;

import com.yigong.faceverify.constant.CookieConstant;
import com.yigong.faceverify.constant.RedisConstant;
import com.yigong.faceverify.exception.SellerAuthorizeException;
import com.yigong.faceverify.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 廖师兄
 * 2017-07-30 17:31
 */
//@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.yigong.faceverify.controller.*.*(..))" +
    "&& !execution(public * com.yigong.faceverify.controller.CookieController.*(..))"+
    "&& !execution(public * com.yigong.faceverify.controller.RegisterController.*(..))"+
    "&& !execution(public * com.yigong.faceverify.controller.LoginController.*(..))")
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("【拦截成功】"+request);
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            log.info("【登录校验】Cookie中查de到token");
        }else {
            log.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(cookie.getValue());
        if (!StringUtils.isEmpty(tokenValue)) {
            log.info("【登录校验】Redis中查de到token");
        }else {
            log.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthorizeException();
        }

    }

    @After("verify()")
    public void finishVerify(){
        log.warn("【跳转完成】未进行当前已登录用户设置");
    }
}
