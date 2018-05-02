package com.yigong.faceverify.controller;

import com.yigong.faceverify.constant.CookieConstant;
import com.yigong.faceverify.constant.RedisConstant;
import com.yigong.faceverify.enums.ResultEnum;
import com.yigong.faceverify.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/*import com.imooc.dataobject.SellerInfo;*/

/**
 * 卖家用户
 * Created by 廖师兄
 * 2017-07-30 15:28
 */
@Controller
@RequestMapping("/cookie")
@Slf4j
public class CookieController {

    /*@Autowired
    private SellerService sellerService;*/

    @Autowired
    private StringRedisTemplate redisTemplate;

/*    @Autowired
    private ProjectUrlConfig projectUrlConfig;*/

    @GetMapping("/set")
    public ModelAndView setCookie(@RequestParam("tokenName") String tokenName,
                            @RequestParam("Jumpto") String jumpto,
                            HttpServletResponse response,
                            Map<String, Object> map) {
        try {
            //3. 设置token至cookie
            Integer expire = RedisConstant.EXPIRE;
            CookieUtil.set(response, CookieConstant.TOKEN, tokenName, expire);
        }catch (Exception e){
            e.printStackTrace();
        }

        log.info("【tokenName in CookieController】"+tokenName);
        //原来 return new ModelAndView("redirect:../jump/centre");
        return new ModelAndView("redirect:../../jump/"+jumpto);
    }

    @GetMapping("/remove")
    @ResponseBody
    public ModelAndView removeCookie(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
