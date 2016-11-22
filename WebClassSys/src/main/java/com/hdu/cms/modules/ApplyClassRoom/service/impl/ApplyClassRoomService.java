package com.hdu.cms.modules.ApplyClassRoom.service.impl;

import com.google.common.collect.Lists;
import com.hdu.cms.common.ConstantParam.APPLYSTATE;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.ConstantParam.TIMETABLESTATE;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.RequestResponseContext.RequestResponseContext;
import com.hdu.cms.common.Utils.*;
import com.hdu.cms.modules.ApplyClassRoom.dao.ApplyClassRoomDao;
import com.hdu.cms.modules.ApplyClassRoom.dto.ApplyClassRoomDto;
import com.hdu.cms.modules.ApplyClassRoom.entity.ApplyClassRoom;
import com.hdu.cms.modules.ApplyClassRoom.service.IApplyClassRoomService;
import com.hdu.cms.modules.ApplyMaintain.dto.ApplyMaintainDto;
import com.hdu.cms.modules.ApplyMaintain.entity.ApplyMaintain;
import com.hdu.cms.modules.Dictionary.service.impl.DictionaryService;
import com.hdu.cms.modules.TimeTable.dto.TimeTableDto;
import com.hdu.cms.modules.TimeTable.entity.TimeTable;
import com.hdu.cms.modules.TimeTable.service.ITimeTableService;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import com.hdu.cms.modules.UserInfo.service.IUserInfoService;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by JetWang on 2016/11/14.
 */
@Service
public class ApplyClassRoomService implements IApplyClassRoomService{

    @Resource
    private ApplyClassRoomDao applyClassRoomDao;
    @Resource
    private ITimeTableService iTimeTableService;

    @Resource
    private IUserInfoService iUserInfoService;
    @Override
    public void save(ApplyClassRoom applyClassRoom,ActionResult result) {
        if(applyClassRoom !=null && applyClassRoom.getTimetableId() !=null){
            TimeTable timeTable = iTimeTableService.getEntityById(applyClassRoom.getTimetableId());
            if(timeTable!=null &&timeTable.getType() != TIMETABLESTATE.FREE.getKey()){
                result.setSuccess(false);
                result.setMessage("教室已被申请,重新选择");
            }else{
                /**
                 * todo 保存当前用户的信息啊！
                 */
                timeTable.setType(TIMETABLESTATE.APPLYING.getKey());//申请中....
                iTimeTableService.saveOrUpdata(timeTable);
                applyClassRoomDao.acSaveOrUpdate(applyClassRoom);
            }
        }else{
            result.setSuccess(false);
            result.setMessage("信息错误");
        }

    }

    /**
     * 更新数据的状态值
     * @param id 数据Id
     * @param state 更新请求的状态
     * @param handleAddvice 管理员的意见
     */
    @Override
    public void update(Integer id, Integer state, String handleAddvice) {
     ApplyClassRoom applyClassRoom = applyClassRoomDao.acFindById(id);
        if(applyClassRoom != null){
            if(state !=null){
                applyClassRoom.setState(state);
                TimeTable timeTable = iTimeTableService.getEntityById(applyClassRoom.getTimetableId());
                if(state == APPLYSTATE.APPLYSUCESS.getIndex()){
                    timeTable.setType(TIMETABLESTATE.OCUPPLY.getKey());//批准成功，就让起借用成功，课程表占用状态
                }else if(state == APPLYSTATE.APPLYERROR.getIndex()){
                    timeTable.setType(TIMETABLESTATE.FREE.getKey());
                }//申请失败就是空闲了
                iTimeTableService.saveOrUpdata(timeTable);
            }
            if(StringUtils.isNotEmpty(handleAddvice)){
                applyClassRoom.setHandleAddvice(handleAddvice);
            }
            applyClassRoomDao.acSaveOrUpdate(applyClassRoom);
        }
    }

    @Override
    public void deleteById(Integer id) {
        applyClassRoomDao.acdeleteById(id);
    }

    @Override
    public ApplyClassRoom findById(Integer id) {
        ApplyClassRoom applyClassRoom = applyClassRoomDao.acFindById(id);
        return applyClassRoom;
    }

    @Override
    public PageBean getPageBean(Integer pageNo, Integer pageSize, String applyIndexCode, String applicant, String phone, Integer purpose, String applyReason, String classRoomIndexCode, String classRoomName, Integer state, String handleAddvice, String whichLesson) {
        PageBean pageBean = applyClassRoomDao.acGetPageBean(pageNo,pageSize,applyIndexCode,applicant,phone,purpose,applyReason,classRoomIndexCode,classRoomName,state,handleAddvice,whichLesson);
        if (pageBean != null && CollectionUtils.isNotEmpty(pageBean.getRows())) {
            PageBean newPageBean = new PageBean();
            BeanUtils.copyProperties(pageBean, newPageBean);
            List<ApplyClassRoom> oldList = pageBean.getRows();
            List<ApplyClassRoomDto> newListDto = Lists.newArrayList();
            for (ApplyClassRoom Item : oldList) {
                newListDto.add(entityToDto(Item));
            }
            newPageBean.setRows(newListDto);
            return newPageBean;
        }
        return new PageBean();
    }
    private ApplyClassRoomDto entityToDto(ApplyClassRoom applyClassRoom){
        ApplyClassRoomDto dto = new ApplyClassRoomDto();
        Map<Integer,String> map = DictionaryService.mapInteger.get(DICTIONARY.CLASSUSEPOPURSE);//教室的使用用途
        BeanUtils.copyProperties(applyClassRoom,dto);
        dto.setPurposeStr(map.get(applyClassRoom.getPurpose()));//用途
        dto.setCreateTimeStr(DateUtil.convertTimestampToStrings(applyClassRoom.getCreateTime()));//时间
        dto.setStateStr(APPLYSTATE.MAP.get(applyClassRoom.getState()));//获取申请的状态
        TimeTableDto timeTableDto  = iTimeTableService.getDtoById(applyClassRoom.getTimetableId());
        if(timeTableDto !=null){//获取当前申请的真实的时间！
            dto.setRealLessonTime(timeTableDto.getWeekStr()+timeTableDto.getDayStr()+timeTableDto.getLessonStr());
        }
        if(StringUtils.isNotEmpty(applyClassRoom.getApplyIndexCode())){
            UserInfo userInfo =iUserInfoService.getUserInfoByIndexCode(applyClassRoom.getApplyIndexCode());
            Map<Integer,String> userTypeMap = DictionaryService.mapInteger.get(DICTIONARY.USERINFO);
            dto.setUserType(userTypeMap.get(userInfo.getUserType()));
        }
        return  dto;
    }

    /**
     * 撤销教室的申请~无论是否申请成功，反正撤销了！
     * @param id
     */
    @Override
    public void updateCanccelApply(Integer id) {
        ApplyClassRoom applyClassRoom = applyClassRoomDao.acFindById(id);
        if(applyClassRoom != null){
            TimeTable timeTable = iTimeTableService.getEntityById(applyClassRoom.getTimetableId());
            if(timeTable !=null && timeTable.getType()!=TIMETABLESTATE.FREE.getKey()){
                timeTable.setType(TIMETABLESTATE.FREE.getKey());//空闲
            }
            iTimeTableService.saveOrUpdata(timeTable);
            applyClassRoom.setState(APPLYSTATE.CANCEL.getIndex());//取消申请
            applyClassRoom.setHandleAddvice("撤销成功");
            applyClassRoomDao.acSaveOrUpdate(applyClassRoom);
        }
    }

    @Override
    public void exportExcel(Integer state, String search) {
        OutputStream output = ExcelUtil.getOutputStreamForExcelExport(RequestResponseContext.getResponse(), "equipment");
        WritableWorkbook wbook = null;
        InputStream input = null;
        try {
            //获得gen 目录
            input = new FileInputStream(HttpRequestUtils.getWebContextRootPath() + File.separator +
                    ConstantParam.DEFAULT_EXCEL_PATH
                    + ConstantParam.EXPORT_APPLYCLASSOOM);
            //从之前存在的xls中创建新的excel
            wbook = Workbook.createWorkbook(output, Workbook.getWorkbook(input));
            WritableSheet sheet = wbook.getSheet(0);
            exportApplyClassInfo(sheet, state, search);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.logException(e);
        } catch (BiffException e) {
            e.printStackTrace();
            LogUtils.logException(e);
        } finally {
            try {
                if (wbook != null) {
                    wbook.write();
                    wbook.close();
                }
                if (output != null) {
                    output.flush();
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LogUtils.logException(e);
            } catch (WriteException e) {
                e.printStackTrace();
                LogUtils.logException(e);
            }
        }
    }

    public void exportApplyClassInfo(WritableSheet sheet, Integer state,String search){
        try {
            List<ApplyClassRoom> applyClassRoomList = applyClassRoomDao .acfindAllByCondition(state,search);
            int row = 0;
            int col = 0;
            if (CollectionUtils.isNotEmpty(applyClassRoomList)) {
                for (ApplyClassRoom Item : applyClassRoomList) {
                    ApplyClassRoomDto dto = entityToDto(Item);
                    row++;
                    col = 0;
                    sheet.addCell(new Label(col++, row, dto.getCreateTimeStr()));//申请者时间
                    sheet.addCell(new Label(col++, row, dto.getApplicant()));//申请者姓名
                    sheet.addCell(new Label(col++, row, dto.getPhone()));//电话
                    sheet.addCell(new Label(col++, row, dto.getApplyReason()));//原因
                    sheet.addCell(new Label(col++, row, dto.getPurposeStr()));//用途
                    sheet.addCell(new Label(col++, row, dto.getClassRoomName()));//教室名称
                    sheet.addCell(new Label(col++, row, dto.getWhichLesson()));//申请时间
                    sheet.addCell(new Label(col++, row, "【"+dto.getRealLessonTime()+"】"));//实际使用时间
                    sheet.addCell(new Label(col++, row, dto.getStateStr()));//申请状态
                    sheet.addCell(new Label(col++, row, dto.getHandleAddvice()));//处理意见
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
