package com.owen.exception;/**
 * @Auther: Administrator
 * @Date: 2018/11/2 18:32
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@ClassName ServiceException
 *@Description TODO
 *@Author zhusm
 *@Date 2018/11/2 18:32    
 *@Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException {

    private String errorMsg;

    private Integer errorCode;
}
