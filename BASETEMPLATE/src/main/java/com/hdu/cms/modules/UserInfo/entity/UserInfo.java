package com.hdu.cms.modules.UserInfo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by JetWang on 2016/10/25.
 */
@Entity
public class UserInfo implements Serializable {

    @Column(name="user_name",length=32)
    private String name;
    /**
     *用户密码
     */
    @Column(name="user_password",length=32)
    private String password;

    /**
     * 主键Id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private Integer id;
    /**
     * 手机号码
     */
    @Column(name="user_phone",length=32)
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
