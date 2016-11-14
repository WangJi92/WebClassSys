package com.hdu.cms.modules.Building.dto;

import com.google.common.collect.Lists;
import com.hdu.cms.common.Utils.JsonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Created by JetWang on 2016/10/11.
 */
public class BuildingInfoDto implements Serializable {

    private Integer id;

    private String indexCode;
    /**
     * 楼宇的名字
     */
    private String name;
    /**
     * 楼层的数量
     */
    private Integer floorTotal;
    /**
     * 值班负责人的indexcode
     */
    private String dutyRoomPeopleIndexcode;
    private String dutyRoomPeoplePhone;
    private String dutyRoomPeopleName;
    /**
     * 维修负责人人员的indexcode
     */
    private String maintancePeopleIndexcode;
    private String maintancePeoplePhone;
    private String maintancePeopleName;

    /**
     * 照片信息字段
     */
    private String pictures;

    /**
     * 简介信息字段
     */
    private String introduceInfo;
    /**
     * 楼宇类型
     */
    private Integer buildingType;
    private String buildingTypeString;


    private String res1;
    private String res2;
    private Integer res3;

    public String getBuildingTypeString() {
        return buildingTypeString;
    }

    public void setBuildingTypeString(String buildingTypeString) {
        this.buildingTypeString = buildingTypeString;
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

    public String getDutyRoomPeoplePhone() {
        return dutyRoomPeoplePhone;
    }

    public void setDutyRoomPeoplePhone(String dutyRoomPeoplePhone) {
        this.dutyRoomPeoplePhone = dutyRoomPeoplePhone;
    }

    public String getDutyRoomPeopleName() {
        return dutyRoomPeopleName;
    }

    public void setDutyRoomPeopleName(String dutyRoomPeopleName) {
        this.dutyRoomPeopleName = dutyRoomPeopleName;
    }

    public String getMaintancePeopleIndexcode() {
        return maintancePeopleIndexcode;
    }

    public void setMaintancePeopleIndexcode(String maintancePeopleIndexcode) {
        this.maintancePeopleIndexcode = maintancePeopleIndexcode;
    }

    public String getMaintancePeoplePhone() {
        return maintancePeoplePhone;
    }

    public void setMaintancePeoplePhone(String maintancePeoplePhone) {
        this.maintancePeoplePhone = maintancePeoplePhone;
    }

    public String getMaintancePeopleName() {
        return maintancePeopleName;
    }

    public void setMaintancePeopleName(String maintancePeopleName) {
        this.maintancePeopleName = maintancePeopleName;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getIntroduceInfo() {
        return introduceInfo;
    }

    public void setIntroduceInfo(String introduceInfo) {
        this.introduceInfo = introduceInfo;
    }

    public Integer getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(Integer buildingType) {
        this.buildingType = buildingType;
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
