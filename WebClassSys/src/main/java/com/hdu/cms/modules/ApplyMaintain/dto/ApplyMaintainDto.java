package com.hdu.cms.modules.ApplyMaintain.dto;

import java.sql.Timestamp;

/**
 * Created by JetWang on 2016/11/15.
 */
public class ApplyMaintainDto {

    private Integer type;
    private String typeStr;

    private Integer uergencyState;
    private String uergencyStateStr;

    private Timestamp createTime;
    private String createTimeStr;

    private Integer state;
    private String stateStr;

    private  Integer id;
    private String classRoomIndexcode;
    private String classRoomName;

    private String applyIndexCode;
    private String applyName;
    private String applyPhone;

    private String maintainIndexCode;
    private String maintainPeople;
    private String  maintainPeoplePhone;
    private String adminAddrice;
    private String applyDetail;

    private String message;

    public String getApplyDetail() {
        return applyDetail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setApplyDetail(String applyDetail) {
        this.applyDetail = applyDetail;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Integer getUergencyState() {
        return uergencyState;
    }

    public void setUergencyState(Integer uergencyState) {
        this.uergencyState = uergencyState;
    }

    public String getUergencyStateStr() {
        return uergencyStateStr;
    }

    public void setUergencyStateStr(String uergencyStateStr) {
        this.uergencyStateStr = uergencyStateStr;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassRoomIndexcode() {
        return classRoomIndexcode;
    }

    public void setClassRoomIndexcode(String classRoomIndexcode) {
        this.classRoomIndexcode = classRoomIndexcode;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public String getApplyIndexCode() {
        return applyIndexCode;
    }

    public void setApplyIndexCode(String applyIndexCode) {
        this.applyIndexCode = applyIndexCode;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }

    public String getMaintainIndexCode() {
        return maintainIndexCode;
    }

    public void setMaintainIndexCode(String maintainIndexCode) {
        this.maintainIndexCode = maintainIndexCode;
    }

    public String getMaintainPeople() {
        return maintainPeople;
    }

    public void setMaintainPeople(String maintainPeople) {
        this.maintainPeople = maintainPeople;
    }

    public String getMaintainPeoplePhone() {
        return maintainPeoplePhone;
    }

    public void setMaintainPeoplePhone(String maintainPeoplePhone) {
        this.maintainPeoplePhone = maintainPeoplePhone;
    }


    public String getAdminAddrice() {
        return adminAddrice;
    }

    public void setAdminAddrice(String adminAddrice) {
        this.adminAddrice = adminAddrice;
    }
}
