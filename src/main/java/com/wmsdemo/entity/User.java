package com.wmsdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String no;

    private String name;

    private String password;

    private Integer age;

    private Integer sex;

    private String phone;
    //超级管理员0 管理员1 普通账号2
    private Integer roleId;

    @TableField("isValid")
    private String isvalid;
}
