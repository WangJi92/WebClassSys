package com.hdu.cms.modules.TimeTable.service.impl;

import com.google.common.collect.Lists;
import com.hdu.cms.common.ConstantParam.*;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.modules.ClassRoom.dto.ClassRoomDto;
import com.hdu.cms.modules.ClassRoom.entity.ClassRoom;
import com.hdu.cms.modules.ClassRoom.service.IClassRoomService;
import com.hdu.cms.modules.Dictionary.service.impl.DictionaryService;
import com.hdu.cms.modules.TimeTable.dao.TimeTableDao;
import com.hdu.cms.modules.TimeTable.dto.TimeTableDto;
import com.hdu.cms.modules.TimeTable.entity.TimeTable;
import com.hdu.cms.modules.TimeTable.service.ITimeTableService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by JetWang on 2016/11/5.
 */
@Service
public class TimeTableService implements ITimeTableService {

    @Resource
    private TimeTableDao timeTableDao;

    @Resource
    private IClassRoomService iClassRoomService;

    private String getWihichWeek(Integer week){
        return "第"+week.toString()+"周";
    }
    private String getWihichDay(Integer day){
        WEEKDAY weekday = WEEKDAY.getString(day);
        return  weekday.getValue();
    }
    private String getWhichLesson(Integer whichLesson){
        LESSONDAY lessonday = LESSONDAY.getString(whichLesson);
        return  lessonday.getValue();
    }

    /**
     * 是否需要教室的名称
     * @param tt
     * @param booleanGetClassRoomName
     * @return
     */
    private TimeTableDto entityToDto(TimeTable tt,boolean booleanGetClassRoomName){
        Map<Integer,String> map = DictionaryService.mapInteger.get(DICTIONARY.CLASSSTATE);
        TimeTableDto dto = new TimeTableDto();
        BeanUtils.copyProperties(tt,dto);
        dto.setWeekStr(getWihichWeek(tt.getWhichWeek()));//某周
        dto.setTypeStr(map.get(tt.getType()));//状态
        dto.setLessonStr(getWhichLesson(tt.getWhichLesson()));//哪节课
        dto.setDayStr(getWihichDay(tt.getWhiichDay()));//周几
        if(booleanGetClassRoomName){
            ClassRoomDto classRoom = iClassRoomService.getClassRoomInfoByIndexCode(tt.getClassRoomIndexCode());
            dto.setClassRoomName(classRoom.getName());//教室的名称
            dto.setSeatNo(classRoom.getSeatNo());//座位数
            dto.setClassTypeName(classRoom.getTypeName());//教室的类型
        }
        return dto;
    }

    @Override
    public TimeTable getEntityById(Integer id) {
        return timeTableDao.getTtById(id);
    }

    @Override
    public TimeTableDto getDtoById(Integer id) {
       TimeTable timeTable = timeTableDao.getTtById(id);
        TimeTableDto  timeTableDto = entityToDto(timeTable,true);
        return timeTableDto;
    }

    @Override
    public void deleteById(Integer id) {
        timeTableDao.ttDeleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        timeTableDao.ttDeleteByIds(ids);
    }

    @Override
    public PageBean getPageInfoBySearch(Integer pageNo, Integer pageSize, Integer whiicWeek, Integer whiicDay, Integer whiicLesson, Integer timetableType, Integer classType, Integer seatNo, String classRoomName, String classRoomIndexcode) {
        PageBean oldPageBean = timeTableDao.getPageInfoBySearch(pageNo,pageSize,whiicWeek,whiicDay,whiicLesson,timetableType,classType,seatNo,classRoomName,classRoomIndexcode);
        boolean wheaerGetClassName = true;
        if(oldPageBean !=null && CollectionUtils.isNotEmpty(oldPageBean.getRows())){
            PageBean newPageBean = new PageBean();
            BeanUtils.copyProperties(oldPageBean,newPageBean);
            List<TimeTable> listOld = oldPageBean.getRows();
            List<TimeTableDto> timeTableDtos = Lists.newArrayList();
            for(TimeTable Item : listOld){
                timeTableDtos.add(entityToDto(Item,wheaerGetClassName));
            }
            newPageBean.setRows(timeTableDtos);
            return  newPageBean;
        }
        return new PageBean();
    }

    /**
     * 创建教室的时候要创建课程表
     * @param classRoomIndexCode
     */
    @Override
    public void saveinitTimeTable(String classRoomIndexCode) {
      List<TimeTable>  list = Lists.newArrayList();
        for(int week=1;week<= ConstantParam.DEFAULT_MAX_WEEK;week++) {//20周
            for (int day = 1; day <= 7; day++) {//周一....
                for (int lesson = 1; lesson <= 16; lesson = lesson * 2) {//哪节课 1 2 4 8 16
                    TimeTable timeTable = new TimeTable();
                    timeTable.setType(TIMETABLESTATE.FREE.getKey());//空闲
                    timeTable.setWhichLesson(lesson);
                    timeTable.setWhichWeek(week);
                    timeTable.setClassRoomIndexCode(classRoomIndexCode);
                    timeTable.setWhiichDay(day);
                    list.add(timeTable);
                }
            }
        }
        timeTableDao.ttSaveList(list);
    }

    @Override
    public void saveOrUpdata(TimeTable tt) {
        timeTableDao.ttSvaeOrUpdate(tt);
    }


    /**
     * 修改教室的状态
     * @param id
     * @param type
     */
    @Override
    public void saveOrUpdateState(Integer id, Integer type) {
       TimeTable tt = timeTableDao.getTtById(id);
        if(tt!=null && type!=null){
            tt.setType(type);
            timeTableDao.ttSvaeOrUpdate(tt);
        }
    }

    /**
     * 用来检查新增的课程是否存在
     * @param classRoomIndexCode
     * @param week
     * @param day
     * @param lesson
     * @return
     */
    @Override
    public boolean wheatherExist(String classRoomIndexCode, Integer week, Integer day, Integer lesson) {
        return timeTableDao.getTimeTableByClassRoomIndexcodeWeekDayLesson(classRoomIndexCode,week,day,lesson);
    }

    @Override
    public void deleteByClassRoomIndexCode(String classRoomIndexcode) {
        timeTableDao.ttDeleteByClassRoomIndexCode(classRoomIndexcode);
    }
}
