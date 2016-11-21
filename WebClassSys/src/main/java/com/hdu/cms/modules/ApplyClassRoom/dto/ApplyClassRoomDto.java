package com.hdu.cms.modules.ApplyClassRoom.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by JetWang on 2016/11/17.
 */
public class ApplyClassRoomDto implements Serializable{

    private  Integer id;
    private String applyIndexCode;
    private String  applicant;
    private String phone;
    /**
     * 用途 数据字典....上课，自习，社团活动，班级活动，比赛排练
     */
    private Integer  purpose;
    private String purposeStr;

    private String applyReason;

    private String classRoomIndexCode;
    private String classRoomName;


    private Timestamp createTime;
    private String createTimeStr;

    private Integer timetableId;

    private String  whichLesson;

    private String realLessonTime; //当前的真实申请时间

    /**
     * 申请状态 审批通过 不能通过 意见
     */
    private Integer state;
    private String stateStr;

    private String  handleAddvice;

    public String getRealLessonTime() {
        return realLessonTime;
    }

    public void setRealLessonTime(String realLessonTime) {
        this.realLessonTime = realLessonTime;
    }

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

    public String getPurposeStr() {
        return purposeStr;
    }

    public void setPurposeStr(String purposeStr) {
        this.purposeStr = purposeStr;
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

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
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

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getHandleAddvice() {
        return handleAddvice;
    }

    public void setHandleAddvice(String handleAddvice) {
        this.handleAddvice = handleAddvice;
    }
}
