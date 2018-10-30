package com.xioazhu.rpccommon.model;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "test_user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String name;
    private Integer age;
    private String address;
    private Date addTime;
    private Date modifyTime;

    public User(){
        super();
    }

    public User(Long id, String name, Integer age, String address, Date addTime, Date modifyTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.addTime = addTime;
        this.modifyTime = modifyTime;
    }
}
