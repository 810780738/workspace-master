package com.xiaozhu.rpm_server;/**
 * @Auther: Administrator
 * @Date: 2018/10/30 19:14
 * @Description:
 */

import lombok.Data;

import java.util.Arrays;

/**
 *@ClassName TestModel
 *@Description TODO
 *@Author zhusm
 *@Date 2018/10/30 19:14    
 *@Version 1.0
 */
//@Data
public class TestModel {
    private String name;
    private int age;
    private byte[] bytes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
