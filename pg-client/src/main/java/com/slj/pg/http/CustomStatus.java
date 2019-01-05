package com.slj.pg.http;

import com.alibaba.fastjson.JSON;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
public enum CustomStatus {
    /**
     * 状态，消息
     */
    /**
     * 请求成功
     */
    SUCCESS("000000", "请求成功"),
    /**
     * 参数缺失
     */
    PARAM_MISS("100001", "参数缺失"),
    /**
     * 参数不合法
     */
    PARAM_ERROR("100002", "参数不合法"),
    /**
     * 内部错误
     */
    INNER_ERROR("100003", "内部错误"),
    /**
     * 请求方式错误
     */
    REQUEST_METHOD_ERROR("100004", "请求方式错误");

    private String code;
    private String message;

    CustomStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
