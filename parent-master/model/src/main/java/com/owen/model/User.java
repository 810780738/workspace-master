package com.owen.model;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "test_user")
public class User implements Serializable{
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String address;

    public User(){

    }
    public User(Long id,String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

}
