package com.owen.model;/**
 * @Auther: Administrator
 * @Date: 2018/11/5 20:06
 * @Description:
 */

import com.xioazhu.rpccommon.model.Users;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 *@ClassName JwtUser
 *@Description 扩展登录时加载用户信息
 *@Author zhusm
 *@Date 2018/11/5 20:06    
 *@Version 1.0
 */
@Slf4j
@NoArgsConstructor
public class JwtUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public JwtUser(Users user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    /** 获取权限信息（即该账号拥有哪些权限) */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /** 账号是否过期 默认false */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /** 账号是否被锁定 默认false */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /** 密码是否过期 默认false */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** 用户是否可用 默认false */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
