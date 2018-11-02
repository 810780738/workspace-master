package com.xioazhu.rpccommon.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: Administrator
 * @Date: 2018/10/17 19:04
 * @Description:
 */
@Data
@NoArgsConstructor
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code = 200;
    private String message;
    private T data;
    protected boolean success;

    public ResultBean(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public static <T> ResultBean<T> success(T data) {
        return new ResultBean<T>(data,true);
    }

    public static <T> ResultBean<T> fail(String message,int code) {
        ResultBean<T> resultBean = new ResultBean<T>();
        resultBean.setMessage(message);
        resultBean.setCode(code);
        resultBean.setSuccess(false);
        return resultBean;
    }
}
