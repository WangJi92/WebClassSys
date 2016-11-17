package com.hdu.cms.modules.TimeTable.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by JetWang on 2016/11/5.
 */
public class TimeTableDto {

    private  Integer id;

    private String classRoomIndexCode;
    private String classRoomName;

    private Integer whichWeek;
    private String weekStr;

    /**
     * 周一 周二 周三
     */
    private Integer whiichDay;
    private  String dayStr;

    /**
     *
     */
    private Integer whichLesson;
    private String  lessonStr;

    private Integer seatNo;//座位数
    private String classTypeName;//教室类型的中文结果哦

    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    public String getClassTypeName() {
        return classTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }

    public String getLessonStr() {
        return lessonStr;
    }

    public void setLessonStr(String lessonStr) {
        this.lessonStr = lessonStr;
    }

    /**
     * 当前的占用的状态
     */
    private Integer type;
    private String typeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getWhichWeek() {
        return whichWeek;
    }

    public void setWhichWeek(Integer whichWeek) {
        this.whichWeek = whichWeek;
    }

    public String getWeekStr() {
        return weekStr;
    }

    public void setWeekStr(String weekStr) {
        this.weekStr = weekStr;
    }

    public Integer getWhiichDay() {
        return whiichDay;
    }

    public void setWhiichDay(Integer whiichDay) {
        this.whiichDay = whiichDay;
    }

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public Integer getWhichLesson() {
        return whichLesson;
    }

    public void setWhichLesson(Integer whichLesson) {
        this.whichLesson = whichLesson;
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
}
