package com.hdu.cms.modules.TimeTable.dao;

import com.google.common.collect.Lists;
import com.hdu.cms.common.ConstantParam.UPDATEWEEKTYPE;
import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ArrayUtils;
import com.hdu.cms.common.Utils.LogUtils;
import com.hdu.cms.modules.TimeTable.entity.TimeTable;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JetWang on 2016/11/4.
 */
@Repository
public class TimeTableDao extends HibernateEntityDao<TimeTable> {

/**
 SELECT
 *
 FROM
 timetable
 WHERE
 1 = 1
 AND timetable.tt_type = 1
 AND timetable.tt_whichweek = 1 ##那一周
 AND timetable.tt_whichlesson = 1 ##哪节课
 AND timetable.tt_whichday ##哪一天
 AND tt_classroomindexcode IN (
 SELECT
 classroom.cm_buildingIndexcode
 FROM
 classroom
 WHERE
 1 = 1
 AND classroom.cm_floorNo >= 1
 AND classroom.cm_name LIKE '汪'
 AND classroom.cm_seatno >= 3
 AND classroom.cm_type = 1
 )
 ORDER BY
 timetable.tt_whichweek;
 */
    /**
     * 1.查找的功能
     * 1教室名称
     * 2.第几周
     * 3.第几节课
     * (1:1-2 :2 3-5 :3:6-7 4:8-9;5:10-12)
     * 4.教室内型 ?
     * 5.座位数 >=0?
     */

    public PageBean getPageInfoBySearch(Integer pageNo,
                                        Integer pageSize,
                                        Integer whiicWeek,
                                        Integer whiicDay,
                                        Integer whiicLesson,
                                        Integer timetableType,//占用状态
                                        Integer classType,
                                        Integer seatNo,
                                        String classRoomName,
                                        String classRoomIndexcode
    ) {
        StringBuffer hql = new StringBuffer("from TimeTable where 1=1 ");
        List<Object> params = Lists.newArrayList();
        if(whiicDay !=null && whiicDay !=-1){//星期几
            hql.append(" and whiichDay = ? ");
            params.add(whiicDay);
        }
        if(whiicLesson !=null && whiicLesson != -1){//那一节课
            hql.append(" and whichLesson = ? ");
            params.add(whiicLesson);
        }
        if(timetableType != null && timetableType != -1 ){//占用状态
            hql.append(" and type = ? ");
            params.add(timetableType);
        }
        if(whiicWeek !=null && whiicWeek !=-1){
            hql.append(" and whichWeek = ? ");
            params.add(whiicWeek);
        }
        //子查询我们的教室信息
        hql.append(" and classRoomIndexCode  in (  select  indexCode from ClassRoom  where 1=1 ");
        if(classType !=null && classType !=-1){
            hql.append(" and type = ? ");
            params.add(classType);
        }
        if(StringUtils.isNotEmpty(classRoomName)){
            hql.append(" and name like  ? ");
            params.add("%"+classRoomName+"%");
        }
        if(seatNo !=null && seatNo !=-1){
            hql.append(" and seatNo >= ? ");
            params.add(seatNo);
        }
        if(StringUtils.isNotEmpty(classRoomIndexcode)){
            hql.append(" and indexCode = ? ");
            params.add(classRoomIndexcode);
        }
        hql.append(" )");
        /**
         * todo 这里写的不是很好.....
         */
        String finalHql = hql.append(" ORDER BY whichWeek ").toString();
        LogUtils.logInfo(hql.toString());
        if(params.size()>=1){
            return  super.findPageByHql(hql.toString(),finalHql,pageNo,pageSize,params.toArray());
        }else{
            return  super.findPageByHql(hql.toString(),finalHql,pageNo,pageSize);
        }

    }
    public void ttSvaeOrUpdate(TimeTable tt){
        super.saveOrUpdate(tt);
        super.flush();
    }

    /**
     * 保存一个List集合
     * @param ttList
     */
    public void ttSaveList(List<TimeTable> ttList){
        super.save(ttList);
        super.flush();
    }

    /**
     * todo 通过教室的Indexcode 去删除课程表
     * @param classRoomIndexcode
     */
    public void ttDeleteByClassRoomIndexCode(String classRoomIndexcode){
        super.executeUpdateHql("delete  from TimeTable  where classRoomIndexCode ='" + classRoomIndexcode + "'");
    }

    /**
     * 根据主键的值获取到我们的信息
     * @param id
     * @return
     */
    public TimeTable  getTtById(Integer id) {
        return super.findById(id);
    }

    public void ttDeleteById(Integer id) {
        super.deleteById(id);
    }
    public void ttDeleteByIds(List<Integer> ids) {
        String idsStr = ArrayUtils.listToDotString(ids);
        super.deleteAll("id", idsStr);
    }

    /**
     * 添加新的课程 可能会和以前的冲突 这里做一下检查
     * @param classRoomIndexcode
     * @param week
     * @param day
     * @param lesson
     * @return
     */
    public boolean  getTimeTableByClassRoomIndexcodeWeekDayLesson(String classRoomIndexcode,Integer week,Integer day,Integer lesson){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TimeTable.class);
        detachedCriteria.add(Restrictions.eq("classRoomIndexCode",classRoomIndexcode));
        detachedCriteria.add(Restrictions.eq("whiichDay",day));
        detachedCriteria.add(Restrictions.eq("whichWeek",week));
        detachedCriteria.add(Restrictions.eq("whichLesson",lesson));
       List<TimeTable> list =  super.findAllByCriteria(detachedCriteria);
        if(CollectionUtils.isNotEmpty(list)){
            return  true;
        }
        return  false;
    }

    /**
     * 批量更新课程表
     * @param whichLesson 哪一节课
     * @param classRoomIndexcode 教室
     * @param whiichDay 哪一天
     * @param beginWeek 开始周
     * @param endWeek 结束周
     * @param weekType 周的类型
     * @param type 教室状态
     */
    public void ttBathUpate(Integer whichLesson,String classRoomIndexcode,Integer whiichDay,Integer beginWeek,Integer endWeek, Integer weekType,Integer type){
       StringBuffer hql = new StringBuffer("update TimeTable set type ='"+type+"' where classRoomIndexCode ='" + classRoomIndexcode + "' ");
        hql.append(" AND whiichDay='"+whiichDay+"' and whichLesson='"+whichLesson+"'");
       if(UPDATEWEEKTYPE.SIGLEWEEK.getIndex().equals(weekType)){//单周
           hql.append(" AND 1 =whichWeek % 2 ");
       }else if(UPDATEWEEKTYPE.DOUBLEWEEK.getIndex().equals(weekType)){//双周
           hql.append(" AND 0 =whichWeek % 2 ");
       }
        hql.append(" AND whichWeek BETWEEN '"+beginWeek+"' AND '"+endWeek+"'");
        super.executeUpdateHql(hql.toString());
    }

    /**
     * 批量更新教室的状态 所有的时间的当前的教室
     * @param classRoomIndexcode 当前的状态
     * @param type 当前的状态
     */
    public void ttBathUpateClassRoom(String classRoomIndexcode,Integer type){
        StringBuffer hql = new StringBuffer("update TimeTable set type ='"+type+"' where classRoomIndexCode ='" + classRoomIndexcode + "' ");
        super.executeUpdateHql(hql.toString());
    }


}
