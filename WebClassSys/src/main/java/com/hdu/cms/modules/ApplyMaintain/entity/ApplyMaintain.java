package com.hdu.cms.modules.ApplyMaintain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by JetWang on 2016/10/12.
 */
@Entity
public class ApplyMaintain implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private  Integer id;

    /**
     * 维修类型 水电
     */
    @Column(name="am_type",length = 4)
    private Integer type;
    /**
     * 紧急程度 紧急
     */
    @Column(name="am_uergencystate",length = 4)
    private Integer uergencyState;
    /**
     * 当前下的维修
     */

    @Column(name="am_ClassRoomIndexCode",length = 64)
    private String classRoomIndexcode;

    @Column(name="am_ClassRoomName",length = 64)
    private String classRoomName;

    /**
     * 创建时间
     */
    @Column(name="am_createTime")
    private Timestamp createTime;
    /**
     * 申请者 indexcode
     */
    @Column(name="am_applyIndexCode",length = 64)
    private String applyIndexCode;

    @Column(name="am_applyName",length = 64)
    private String applyName;

    @Column(name="am_applyPhone",length = 32)
    private String applyPhone;

    /**
     * 保修详细说明！
     */
    @Column(name="am_applyDetail",length = 625)
    private String applyDetail;

    /**
     * 处理状态
     */
    @Column(name="am_state",length = 4)
    private Integer state;

    @Column(name="am_maintainIndexCode",length = 64)
    private String maintainIndexCode;

    @Column(name="am_maintainPeople",length = 64)
    private String maintainPeople;

    @Column(name="am_maintainPeoplePhone",length = 64)
    private String  maintainPeoplePhone;

    /**
     * 管理员意见
     */
    @Column(name="am_adminAddrice",length = 625)
    private String adminAddrice;

    @Column(name="am_message",length = 625)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApplyDetail() {
        return applyDetail;
    }

    public void setApplyDetail(String applyDetail) {
        this.applyDetail = applyDetail;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public String getClassRoomIndexcode() {
        return classRoomIndexcode;
    }

    public void setClassRoomIndexcode(String classRoomIndexcode) {
        this.classRoomIndexcode = classRoomIndexcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUergencyState() {
        return uergencyState;
    }

    public void setUergencyState(Integer uergencyState) {
        this.uergencyState = uergencyState;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
