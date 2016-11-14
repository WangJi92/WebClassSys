package com.hdu.cms.modules.ClassRoom.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by JetWang on 2016/10/11.
 */
@Entity
public class ClassRoom implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private  Integer id;
    /**
     * 唯一标识符
     */
    @Column(name ="cm_indexcode",length = 64)
    private String indexCode;
    /**
     * 教室名称
     */
    @Column(name ="cm_name",length = 64)
    private String name;

    @Column(name ="cm_seatno",length = 64)
    private Integer seatNo;

    /**
     * 所在楼宇的信息
     */
    @Column(name ="cm_buildingIndexcode",length = 64)
    private String buildingIndexCode;
    /**
     * 教室的类别，多媒体教室还是其他的教室
     */
    @Column(name ="cm_type",length = 4)
    private Integer type;

    /**
     * 所在楼层
     */
    @Column(name ="cm_floorNo",length = 4)
    private Integer floorNo;



    @Column(name="cm_pictures",length = 1024)
    private String pictures;

    /**
     * 说明
     */
    @Column(name="cm_introduction",length = 1024)
    private String introduction;

    /**
     * 预留字段信息
     */
    @Column(name="cm_res1",length = 255)
    private String res1;
    @Column(name="cm_res2",length = 255)
    private String res2;
    @Column(name="cm_res3",length = 4)
    private Integer res3;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    public String getBuildingIndexCode() {
        return buildingIndexCode;
    }

    public void setBuildingIndexCode(String buildingIndexCode) {
        this.buildingIndexCode = buildingIndexCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }



    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRes1() {
        return res1;
    }

    public void setRes1(String res1) {
        this.res1 = res1;
    }

    public String getRes2() {
        return res2;
    }

    public void setRes2(String res2) {
        this.res2 = res2;
    }

    public Integer getRes3() {
        return res3;
    }

    public void setRes3(Integer res3) {
        this.res3 = res3;
    }
}
