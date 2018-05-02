package com.yigong.faceverify.utils;

import com.yigong.faceverify.VO.ResultVO;

/**
 * Copy from 廖师兄
 * @author tianyi
 * @date 2018-02-15 16:50
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setResultCode(200);
        resultVO.setResultMsg("成功");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO successLogin(String tokenName,Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setResultCode(200);
        resultVO.setResultMsg(tokenName);
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setResultCode(code);
        resultVO.setResultMsg(msg);
        return resultVO;
    }
}
