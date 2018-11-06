package com.xioazhu.rpccommon.model;/**
 * @Auther: Administrator
 * @Date: 2018/11/2 19:20
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *@ClassName Users
 *@Description TODO
 *@Author zhusm
 *@Date 2018/11/2 19:20    
 *@Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    private Long id;

    private String username;

    private String password;

    private String role;

    private Date addtime;

    private Date modifytime;
}
