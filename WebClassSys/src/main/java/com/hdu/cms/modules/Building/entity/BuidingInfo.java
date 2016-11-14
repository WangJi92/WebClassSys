package com.hdu.cms.modules.Building.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by JetWang on 2016/10/10.
 * 楼宇信息实体类   维护人员可能是两个....
 */
@Entity
public class BuidingInfo  implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private  Integer id;

    /**
     * 唯一标识符
     */
    @Column(name ="build_indexcode",length = 64)
    private String indexCode;
    /**
     * 楼宇的名字
     */
    @Column(name ="build_name",length = 64)
    private String name;
    /**
     * 楼层的数量
     */
    @Column(name ="build_floorTotal",length = 2)
    private Integer floorTotal;

    /**
     * 值班负责人的indexcode
     */
    @Column(name ="build_dutyroompeopleindexcode",length = 64)
    private String dutyRoomPeopleIndexcode;

    /**
     * 维修负责人人员的indexcode
     */
    @Column(name ="build_maintancepeopleindexcode",length = 64)
    private String maintancePeopleIndexcode;


    /**
     * 简介信息字段
     */
    @Column(name ="build_introduceInfo",length = 2046)
    private String introduceInfo;

    /**
     * 照片信息字段
     */
    @Column(name ="build_pictures",length = 2046)
    private String pictures;

    /**
     * 备用字段1
     */
    @Column(name ="build_res1",length = 1024)
    private String res1;
    /**
     * 备用字段2
     */
    @Column(name ="build_res2",length = 1024)
    private String res2;

    /**
     * 备用字段3
     */
    @Column(name ="build_res3",length = 32)
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

    public Integer getFloorTotal() {
        return floorTotal;
    }

    public void setFloorTotal(Integer floorTotal) {
        this.floorTotal = floorTotal;
    }

    public String getDutyRoomPeopleIndexcode() {
        return dutyRoomPeopleIndexcode;
    }

    public void setDutyRoomPeopleIndexcode(String dutyRoomPeopleIndexcode) {
        this.dutyRoomPeopleIndexcode = dutyRoomPeopleIndexcode;
    }

    public String getMaintancePeopleIndexcode() {
        return maintancePeopleIndexcode;
    }

    public void setMaintancePeopleIndexcode(String maintancePeopleIndexcode) {
        this.maintancePeopleIndexcode = maintancePeopleIndexcode;
    }

    public String getIntroduceInfo() {
        return introduceInfo;
    }

    public void setIntroduceInfo(String introduceInfo) {
        this.introduceInfo = introduceInfo;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuidingInfo that = (BuidingInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (indexCode != null ? !indexCode.equals(that.indexCode) : that.indexCode != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (floorTotal != null ? !floorTotal.equals(that.floorTotal) : that.floorTotal != null) return false;
        if (dutyRoomPeopleIndexcode != null ? !dutyRoomPeopleIndexcode.equals(that.dutyRoomPeopleIndexcode) : that.dutyRoomPeopleIndexcode != null)
            return false;
        if (maintancePeopleIndexcode != null ? !maintancePeopleIndexcode.equals(that.maintancePeopleIndexcode) : that.maintancePeopleIndexcode != null)
            return false;
        if (introduceInfo != null ? !introduceInfo.equals(that.introduceInfo) : that.introduceInfo != null)
            return false;
        if (pictures != null ? !pictures.equals(that.pictures) : that.pictures != null) return false;
        if (res1 != null ? !res1.equals(that.res1) : that.res1 != null) return false;
        if (res2 != null ? !res2.equals(that.res2) : that.res2 != null) return false;
        return !(res3 != null ? !res3.equals(that.res3) : that.res3 != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (indexCode != null ? indexCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (floorTotal != null ? floorTotal.hashCode() : 0);
        result = 31 * result + (dutyRoomPeopleIndexcode != null ? dutyRoomPeopleIndexcode.hashCode() : 0);
        result = 31 * result + (maintancePeopleIndexcode != null ? maintancePeopleIndexcode.hashCode() : 0);
        result = 31 * result + (introduceInfo != null ? introduceInfo.hashCode() : 0);
        result = 31 * result + (pictures != null ? pictures.hashCode() : 0);
        result = 31 * result + (res1 != null ? res1.hashCode() : 0);
        result = 31 * result + (res2 != null ? res2.hashCode() : 0);
        result = 31 * result + (res3 != null ? res3.hashCode() : 0);
        return result;
    }
}
