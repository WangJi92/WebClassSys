package com.hdu.cms.modules.ApplyClassRoom.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by JetWang on 2016/10/12.
 */
@Entity
public class ApplyClassRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ac_applyIndexCode", length = 64)
    private String applyIndexCode;
    @Column(name = "ac_applicant", length = 64)
    private String applicant;
    @Column(name = "ac_phone", length = 32)
    private String phone;

    /**
     * 用途 数据字典....上课，自习，社团活动，班级活动，比赛排练
     */
    @Column(name = "ac_purpose", length = 4)
    private Integer purpose;

    @Column(name = "ac_applyReason", length = 625)
    private String applyReason;

    @Column(name = "ac_classRoomIndexCode", length = 64)
    private String classRoomIndexCode;

    @Column(name = "ac_classRoomName", length = 64)
    private String classRoomName;


    @Column(name = "ac_createTime")
    private Timestamp createTime;

    @Column(name = "ac_timetableId")
    private Integer timetableId;//课程表的信息

    @Column(name = "ac_whichLesson", length = 625)
    private String whichLesson;


    /**
     * 申请状态 审批通过 不能通过 意见
     */
    @Column(name = "ac_state", length = 4)
    private Integer state;

    /**
     * 处理意见
     */
    @Column(name = "ac_handleAddvice", length = 625)
    private String handleAddvice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplyIndexCode() {
        return applyIndexCode;
    }

    public void setApplyIndexCode(String applyIndexCode) {
        this.applyIndexCode = applyIndexCode;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPurpose() {
        return purpose;
    }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getClassRoomIndexCode() {
        return classRoomIndexCode;
    }

    public void setClassRoomIndexCode(String classRoomIndexCode) {
        this.classRoomIndexCode = classRoomIndexCode;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(Integer timetableId) {
        this.timetableId = timetableId;
    }

    public String getWhichLesson() {
        return whichLesson;
    }

    public void setWhichLesson(String whichLesson) {
        this.whichLesson = whichLesson;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getHandleAddvice() {
        return handleAddvice;
    }

    public void setHandleAddvice(String handleAddvice) {
        this.handleAddvice = handleAddvice;
    }
}
