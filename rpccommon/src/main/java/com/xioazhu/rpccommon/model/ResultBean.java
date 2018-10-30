package com.xioazhu.rpccommon.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Administrator
 * @Date: 2018/10/17 19:04
 * @Description:
 */
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;
    private Exception exception;
}
