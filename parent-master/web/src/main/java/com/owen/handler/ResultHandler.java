package com.owen.handler;/**
 * @Auther: Administrator
 * @Date: 2018/11/2 18:17
 * @Description:
 */

import com.google.common.base.Throwables;
import com.xioazhu.rpccommon.exception.ServiceException;
import com.xioazhu.rpccommon.model.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 *@ClassName ResultHandler
 *@Description 统一处理handler
 *@Author zhusm
 *@Date 2018/11/2 18:17    
 *@Version 1.0
 */
@Slf4j
@ControllerAdvice(annotations = {RestController.class,Controller.class})
public class ResultHandler implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        final String returntypeName = returnType.getParameterType().getName();
        return !"com.xioazhu.rpccommon.model.ResultBean".equals(returntypeName)
                && !"org.springframework.http.ResponseEntity".equals(returntypeName);
    }

    /**
     * @Author zhusm
     * @Description 异常统一处理
     * @Date 18:26 2018/11/2
     * @Param [o, methodParameter, mediaType, aClass, serverHttpRequest, serverHttpResponse]
     * @return java.lang.Object
     **/
    @Override
    public Object beforeBodyWrite(Object object, MethodParameter returnType, MediaType selectContentType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        final String returnTypeName = returnType.getParameterType().getName();
        if ("void".equals(returnTypeName)) return ResultBean.success(null);
        if (!selectContentType.includes(MediaType.APPLICATION_JSON)) return ResultBean.success(object);
        if ("com.xioazhu.rpccommon.model.ResultBean".equals(returnTypeName)) return ResultBean.success(object);
        return ResultBean.success(object);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ServiceException.class})
    public <T> ResultBean<T> handlerException(ServiceException e){
        log.error(Throwables.getStackTraceAsString(e));
        return ResultBean.fail(e.getMessage(),5001);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Throwable.class})
    public <T> ResultBean<T> handlerThrowable(Throwable throwable){
        log.error(Throwables.getStackTraceAsString(throwable));
        return ResultBean.fail(Throwables.getStackTraceAsString(throwable),5002);
    }
}
