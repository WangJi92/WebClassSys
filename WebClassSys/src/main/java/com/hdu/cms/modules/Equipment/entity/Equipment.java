package com.hdu.cms.modules.Equipment.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by JetWang on 2016/10/11.
 * 设备 名字
 */
@Entity
public class Equipment implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private  Integer id;

    /**
     * 唯一标识符
     */
    @Column(name ="eq_indexcode",length = 64)
    private String indexCode;
    /**
     * 类型
     */
    @Column(name ="eq_type",length = 4)
    private Integer type;

    /**
     * 设备的名称
     */
    @Column(name = "eq_name",length = 64)
    private String name;
    /**
     * 品牌名称
     */
    @Column(name ="eq_brandname",length = 64)
    private String brandName;

    /**
     * 简介
     */
    @Column(name ="eq_introduce",length = 64)
    private String introduce;
    /**
     * 图片
     */
    @Column(name ="eq_pictures",length = 1024)
    private String pictures;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }
}
