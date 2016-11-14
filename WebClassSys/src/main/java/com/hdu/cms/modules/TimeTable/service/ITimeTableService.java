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
}
