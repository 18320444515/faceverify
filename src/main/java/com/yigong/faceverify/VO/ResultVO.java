package com.yigong.faceverify.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象 TODO @Data注解？
 * Created by 廖师兄
 * 2017-05-12 14:13
 */
@Data
public class ResultVO<T> {

    /** 错误码. */
    private Integer resultCode;

    /** 提示信息. */
    private String resultMsg;

    /** 具体内容. */
    private T data;
}
