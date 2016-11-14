package com.hdu.cms.modules.TimeTable.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by JetWang on 2016/10/12.
 */
@Entity
public class TimeTable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private  Integer id;
    /**
     * 教室外键
     */
    @Column(name="tt_classroomindexcode",length = 64)
    private String classRoomIndexCode;

    @Column(name="tt_whichweek",length = 4)
    private Integer whichWeek;

    @Column(name="tt_whichday",length = 4)
    private Integer whiichDay;

    @Column(name="tt_whichlesson",length = 4)
    private Integer whichLesson;

    /**
     * 教室的类型 自习 上课... 申请中
     */
    @Column(name="tt_type",length = 4)
    private Integer type;

    public Integer getWhichWeek() {
        return whichWeek;
    }

    public void setWhichWeek(Integer whichWeek) {
        this.whichWeek = whichWeek;
    }

    public Integer getWhiichDay() {
        return whiichDay;
    }

    public void setWhiichDay(Integer whiichDay) {
        this.whiichDay = whiichDay;
    }

    public Integer getWhichLesson() {
        return whichLesson;
    }

    public void setWhichLesson(Integer whichLesson) {
        this.whichLesson = whichLesson;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
