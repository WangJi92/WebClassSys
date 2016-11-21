package com.hdu.cms.modules.UserInfo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by JetWang on 2016/10/6.
 */
@Entity
public class UserInfo implements Serializable{

    /**
     * 用户姓名
     */
    @Column(name="user_name",length=32)
    private String userName;

    @Column(name="user_loginAccount",length=32)
    private String loginAccount;

    /**
     * 用户类型 1 admin 2 teacher 3 student
     */
    @Column(name="user_type",length=2)
    private Integer userType;

    /**
     *用户密码
     */
    @Column(name="user_password",length=32)
    private String password;

    /**
     * 主键Id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    /**
     * 用户唯一标识符
     */
    @Column(name="user_indexcode",length=64)
    private String indexCode;

    /**
     * 座机号码
     */
    @Column(name="user_tell",length=32)
    private String tell;
    /**
     * 手机号码
     */
    @Column(name="user_phone",length=32)
    private String phone;

    @Column(name="user_picture",length=64)
    private String picture;

    public String getLoginAccount() {
        return loginAccount;
    }
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public UserInfo() {
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

}
