package com.hdu.cms.modules.TimeTable.service;

import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.modules.TimeTable.dto.TimeTableDto;
import com.hdu.cms.modules.TimeTable.entity.TimeTable;

import java.util.List;

/**
 * Created by JetWang on 2016/11/5.
 */
public interface ITimeTableService {

    public TimeTable getEntityById(Integer id);

    public TimeTableDto getDtoById(Integer id);

    public void deleteById(Integer id);
    
    public  void deleteByIds(List<Integer> ids);

    /**
     * 搜索课程表
     * @param pageNo
     * @param pageSize
     * @param whiicWeek
     * @param whiicDay
     * @param whiicLesson
     * @param timetableType
     * @param classType
     * @param seatNo
     * @param classRoomName
     * @param classRoomIndexcode
     * @return
     */
    public PageBean  getPageInfoBySearch(Integer pageNo,
                               Integer pageSize,
                               Integer whiicWeek,
                               Integer whiicDay,
                               Integer whiicLesson,
                               Integer timetableType,//占用状态
                               Integer classType,
                               Integer seatNo,
                               String classRoomName,
                               String classRoomIndexcode
    );

    /**
     * 初始化课程表
     * @param classRoomIndexCode
     */
    public void saveinitTimeTable(String classRoomIndexCode);

    /**
     * 通过教室的indexcode去删除所有的 课程表
     * @param classRoomIndexcode
     */
    public void deleteByClassRoomIndexCode(String classRoomIndexcode);


    /**
     * 保存或者修改课程表
     * @param tt
     */
    public void saveOrUpdata(TimeTable tt);

    /**
     * 修改课程表的状态
     * @param id
     * @param type
     */
    public void saveOrUpdateState(Integer id,Integer type);

    /**
     * 新增加课程的时候用来检查是否存在
     * @param classRoomIndexCode
     * @param week
     * @param day
     * @param lesson
     * @return
     */
    public boolean  wheatherExist(String classRoomIndexCode,Integer week,Integer day,Integer lesson);

    /**
     * todo 批量更新课程表信息
     * @param whichLesson  哪一节课
     * @param classRoomIndexcode 教室
     * @param whiichDay   哪一天
     * @param beginWeek  结束周
     * @param endWeek  开始周
     * @param weekType 单周双周
     * @param type 课表状态
     */
    public void updateBath(Integer whichLesson,String classRoomIndexcode,Integer whiichDay,Integer beginWeek,Integer endWeek, Integer weekType,Integer type);


    /**
     * 批量更新当前教室的所有的状态
     * @param classRoomIndexcode
     * @param type 教室的状态
     */
    public void updateBacthClassRoom(String classRoomIndexcode,Integer type);


}
