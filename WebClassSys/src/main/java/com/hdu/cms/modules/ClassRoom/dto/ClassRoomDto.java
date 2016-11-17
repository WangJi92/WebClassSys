package com.hdu.cms.modules.ClassRoom.dto;

import com.google.common.collect.Lists;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.Utils.JsonUtils;
import com.hdu.cms.modules.Building.entity.BuidingInfo;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;
import com.hdu.cms.modules.Dictionary.service.impl.DictionaryService;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by JetWang on 2016/10/11.
 */
public class ClassRoomDto implements Serializable {

    private Integer id;

    private String indexCode;
    /**
     * 教室名称
     */
    private String name;
    private Integer seatNo;
    private String buildingIndexCode;

    private String buildingName;//教学楼名称
    private String dutyRoomPeoplePhone;//教学楼值班人员信息
    private String dutyRoomPeopleName;

    private String maintancePeoplePhone;//教学楼维修人员信息
    private String maintancePeopleName;


    /**
     * 教室的类别，多媒体教室还是其他的教室
     */
    private Integer type;
    private String typeName;//教室类型的中文结果哦
    /**
     * 所在楼层
     */
    private Integer floorNo;

    private String pictures;

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    /**
     * 说明
     */
    private String introduction;

    /**
     * 预留字段信息
     */
    private String res1;
    private String res2;
    private Integer res3;
//


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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
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
