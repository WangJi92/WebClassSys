package com.hdu.cms.modules.TimeTable.action;

import com.google.common.collect.Lists;
import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.LESSONDAY;
import com.hdu.cms.common.ConstantParam.STATE;
import com.hdu.cms.common.ConstantParam.WEEKDAY;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.common.Utils.SelectBean;
import com.hdu.cms.modules.TimeTable.dto.TimeTableDto;
import com.hdu.cms.modules.TimeTable.entity.TimeTable;
import com.hdu.cms.modules.TimeTable.service.ITimeTableService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JetWang on 2016/11/6.
 */
@Controller
@RequestMapping("/timeTableAction")
public class TimeTableAction extends BaseAction {
    @Resource
    private ITimeTableService iTimeTableService;

    @RequestMapping("/pageInfo")
    @ResponseBody
    public ActionResult getPageInfo(Integer pageNo,
                                    Integer pageSize,
                                    Integer whichWeek,
                                    Integer whichDay,
                                    Integer whichLesson,
                                    Integer timetableType,//占用状态
                                    Integer classType,
                                    Integer seatNo,
                                    String classRoomName,
                                    String classRoomIndexcode) {
        ActionResult result = new ActionResult(true);
        if (pageNo == null || pageSize == null) {
            pageNo = ConstantParam.DEFAULT_PAGE_NO;
            pageSize = ConstantParam.DEFAULT_PAGE_SIZE;
        }
        try {
            PageBean pageBean = iTimeTableService.getPageInfoBySearch(pageNo,
                    pageSize,
                    whichWeek,
                    whichDay,
                    whichLesson,
                    timetableType,
                    classType,
                    seatNo,
                    classRoomName,
                    classRoomIndexcode);
            result.setData(pageBean);
            result.setMessage("all  is ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.setData(new PageBean());
            result.setMessage("error");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getSelectWeekDay")
    public ActionResult getSelectWeekDay(){
        ActionResult result = new ActionResult(true);
        List<SelectBean> list = Lists.newArrayList();
        for(WEEKDAY item : WEEKDAY.values()){
            SelectBean selectBean = new SelectBean();
            /**
             * 之前封装写错了....
             */
            selectBean.setKey(item.getValue());
            selectBean.setValue(item.getKey());
            list.add(selectBean);
        }
        result.setData(list);
        return result;
    }

    /**
     * 获取当前是第几节课0
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSelectLesson")
    public ActionResult getSelectLesson(){
        ActionResult result = new ActionResult(true);
        List<SelectBean> list = Lists.newArrayList();
        for(LESSONDAY item:LESSONDAY.values()){
            SelectBean selectBean = new SelectBean();
            selectBean.setKey(item.getValue());
            selectBean.setValue(item.getKey());
            list.add(selectBean);
        }
        result.setData(list);
        return  result;
    }
    @ResponseBody
    @RequestMapping("/getDtoById")
    public ActionResult getById(Integer id){
        ActionResult result = new ActionResult(true);
        try {
            TimeTableDto tableDto = iTimeTableService.getDtoById(id);
            result.setData(tableDto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统错误");
        }
        return  result;
    }
    @ResponseBody
    @RequestMapping("/saveOrUpdate")
    public ActionResult saveOrUpdate(TimeTable timeTable){
        ActionResult result = new ActionResult(true);
        try {
            if(timeTable!=null){
               if(StringUtils.isNotEmpty(timeTable.getClassRoomIndexCode())//信息完整性检查
                       && timeTable.getWhichLesson() !=null
                       && timeTable.getWhichWeek() !=null
                       && timeTable.getWhiichDay() !=null
                       &&timeTable.getType() !=null
                       ){
                   if(timeTable.getId()!=null){
                    iTimeTableService.saveOrUpdata(timeTable);
                   }else{
                       /**
                        * 1.检查当前课程是否存在
                        * 2.然后在保存涩
                        */
                     boolean exist =  iTimeTableService.wheatherExist(timeTable.getClassRoomIndexCode(),timeTable.getWhichWeek(),timeTable.getWhiichDay(),timeTable.getWhichLesson());
                     if(exist == false){
                         iTimeTableService.saveOrUpdata(timeTable);
                     }else{
                         result.setMessage("课程信息存在");
                         result.setSuccess(false);
                     }
                   }
               }else{
                   result.setSuccess(false);
                   result.setMessage("信息不完整");
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统错误");
            result.setSuccess(false);
        }
        return  result;
    }

    @RequestMapping("/deleteIds")
    @ResponseBody
    public ActionResult  deleteByIds(@RequestParam("ids")List<Integer> ids){
        ActionResult result = new ActionResult(true);
        try {
            if(CollectionUtils.isNotEmpty(ids)){
                iTimeTableService.deleteByIds(ids);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("删除异常");
        }
        return  result;
    }

    /**
     * 批量更新当前时间的当前教室的选择的周次
     * @param classRoomIndexCode
     * @param whiichDay
     * @param whichLesson
     * @param type
     * @param batch
     * @param beginWeek
     * @param endWeek
     * @param weekType
     * @return
     */
    @ResponseBody
    @RequestMapping("/bathUpdate")
    public ActionResult bathUpdate(String classRoomIndexCode,Integer whiichDay,Integer whichLesson,Integer type,Integer batch,Integer beginWeek,Integer endWeek,Integer weekType ){
        ActionResult result = new ActionResult(true);
        try {
            if(StringUtils.isNotEmpty(classRoomIndexCode) &&null != whiichDay &&  null !=whichLesson && null!=type && null != batch && null!=beginWeek &&null!=endWeek && null !=weekType && beginWeek <=endWeek && batch == STATE.YES.getValue()){
                iTimeTableService.updateBath(whichLesson, classRoomIndexCode, whiichDay, beginWeek, endWeek, weekType, type);
            }else{
                result.setMessage("参数错误");
                result.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统错误");
            result.setSuccess(false);
        }
        return  result;
    }

    @ResponseBody
    @RequestMapping("/bathUpdateClassRoom")
    public ActionResult bathUpdateClassRoom(String classRoomIndexCode,Integer type,Integer batch){
        ActionResult result = new ActionResult(true);
        try {
            if(StringUtils.isNotEmpty(classRoomIndexCode) &&null!=type && null != batch && 4 ==batch){
                iTimeTableService.updateBacthClassRoom(classRoomIndexCode,type);
            }else{
                result.setMessage("参数错误");
                result.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统错误");
            result.setSuccess(false);
        }
        return  result;
    }

}
