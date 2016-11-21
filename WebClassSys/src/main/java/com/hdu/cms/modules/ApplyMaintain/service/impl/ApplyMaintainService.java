package com.hdu.cms.modules.ApplyMaintain.service.impl;

import com.google.common.collect.Lists;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.ConstantParam.MAINTAINSTATE;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.RequestResponseContext.RequestResponseContext;
import com.hdu.cms.common.Utils.DateUtil;
import com.hdu.cms.common.Utils.ExcelUtil;
import com.hdu.cms.common.Utils.HttpRequestUtils;
import com.hdu.cms.common.Utils.LogUtils;
import com.hdu.cms.modules.ApplyMaintain.dao.ApplyMaintainDao;
import com.hdu.cms.modules.ApplyMaintain.dto.ApplyMaintainDto;
import com.hdu.cms.modules.ApplyMaintain.entity.ApplyMaintain;
import com.hdu.cms.modules.ApplyMaintain.service.IApplyMaintainService;
import com.hdu.cms.modules.Dictionary.service.impl.DictionaryService;
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
public class ApplyMaintainService implements IApplyMaintainService {

    @Resource
    private ApplyMaintainDao applyMaintainDao;

    @Override
    public void save(ApplyMaintain applyMaintain) {
        applyMaintainDao.amSaveOrUpdate(applyMaintain);
    }

    @Override
    public void update(Integer id, Integer state, String adminAdrice,String maintainIndexCode,String maintainPeople,String maintainPeoplePhone,String message) {
        ApplyMaintain applyMaintain = applyMaintainDao.amFindById(id);
        if (applyMaintain != null) {
            if (state != null) {
                applyMaintain.setState(state);
            }
            if (StringUtils.isNotEmpty(adminAdrice)) {
                applyMaintain.setAdminAddrice(adminAdrice);
            }
            if(StringUtils.isNotEmpty(maintainIndexCode)){
                applyMaintain.setMaintainIndexCode(maintainIndexCode);
            }
            if(StringUtils.isNotEmpty(maintainPeople)){
                applyMaintain.setMaintainPeople(maintainPeople);
            }
            if(StringUtils.isNotEmpty(maintainPeoplePhone)){
                applyMaintain.setMaintainPeoplePhone(maintainPeoplePhone);
            }
            if(StringUtils.isNotEmpty(message)){//发送的短信内容
                /**
                 * 去掉前端的换行符
                 */
                message = message.replaceAll("\r|\n", "");
                applyMaintain.setMessage(message);
            }
            applyMaintainDao.amSaveOrUpdate(applyMaintain);
        }
    }

    @Override
    public PageBean getPageBean(Integer pageNo, Integer pageSize, Integer type, Integer state, Integer uergencyState, String ClassRoomName, String applyName, String applyPhone, String maintainPeople, String maintainPeoplePhone, String adminAddrice, String applyDetail,String applyIndexCode) {
        PageBean pageBean = applyMaintainDao.amGetPageBean(pageNo, pageSize, type, state, uergencyState, ClassRoomName, applyName, applyPhone, maintainPeople, maintainPeoplePhone, adminAddrice, applyDetail,applyIndexCode);
        if (pageBean != null && CollectionUtils.isNotEmpty(pageBean.getRows())) {
            PageBean newPageBean = new PageBean();
            BeanUtils.copyProperties(pageBean, newPageBean);
            List<ApplyMaintain> oldList = pageBean.getRows();
            List<ApplyMaintainDto> newListDto = Lists.newArrayList();
            for (ApplyMaintain Item : oldList) {
                newListDto.add(EntityToDto(Item));
            }
            newPageBean.setRows(newListDto);
            return newPageBean;
        }
        return new PageBean();
    }

    private ApplyMaintainDto EntityToDto(ApplyMaintain applyMaintain) {
        Map<Integer, String> URGENCY_MAP = DictionaryService.mapInteger.get(DICTIONARY.URGENCYREPAIR);//紧急程度
        Map<Integer, String> REPAIR_MAP = DictionaryService.mapInteger.get(DICTIONARY.REPAIRCLASSFIY);//维修类别
        if (applyMaintain != null) {
            ApplyMaintainDto applyMaintainDto = new ApplyMaintainDto();
            BeanUtils.copyProperties(applyMaintain, applyMaintainDto);
            applyMaintainDto.setCreateTimeStr(DateUtil.convertTimestampToStrings(applyMaintain.getCreateTime()));//时间转换的处理函数
            /**
             * 维修类型 水电
             */
            applyMaintainDto.setTypeStr(REPAIR_MAP.get(applyMaintain.getType()));
            /**
             * 紧急程度
             */
            applyMaintainDto.setUergencyStateStr(URGENCY_MAP.get(applyMaintain.getUergencyState()));
            /**
             * 目前的状态
             */
            applyMaintainDto.setStateStr(MAINTAINSTATE.MAP.get(applyMaintain.getState()));
            return applyMaintainDto;

        }
        return new ApplyMaintainDto();

    }

    @Override
    public void deleteById(Integer id) {
        applyMaintainDao.amDeleteById(id);
    }

    @Override
    public ApplyMaintain findByEnetityId(Integer id) {
        return applyMaintainDao.amFindById(id);
    }

    @Override
    public ApplyMaintainDto findByDtoId(Integer id) {
        ApplyMaintain item = applyMaintainDao.amFindById(id);
        return EntityToDto(item);
    }

    @Override
    public List<ApplyMaintainDto> getALlMaintainInfoByApplyIndexcode(String applyIndexoce) {
        List<ApplyMaintain> applyMaintainList = applyMaintainDao.getALlByApplyIndexcode(applyIndexoce);
        List<ApplyMaintainDto> applyMaintainDtoList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(applyMaintainList)){
          for(ApplyMaintain Item : applyMaintainList){
              applyMaintainDtoList.add(EntityToDto(Item));
          }
        }
        return applyMaintainDtoList;
    }

    @Override
    public void ExportApplyMainInfo(Integer state, Integer type, Integer uergencyState, String search) {
        OutputStream output = ExcelUtil.getOutputStreamForExcelExport(RequestResponseContext.getResponse(), "equipment");
        WritableWorkbook wbook = null;
        InputStream input = null;
        try {
            //获得gen 目录
            input = new FileInputStream(HttpRequestUtils.getWebContextRootPath() + File.separator +
                    ConstantParam.DEFAULT_EXCEL_PATH
                    + ConstantParam.EXPORT_MAINTAIN);
            //从之前存在的xls中创建新的excel
            wbook = Workbook.createWorkbook(output, Workbook.getWorkbook(input));
            WritableSheet sheet = wbook.getSheet(0);
            exportApplyMainTainInfo(sheet, state, type, uergencyState, search);
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

    public void exportApplyMainTainInfo(WritableSheet sheet, Integer state, Integer type, Integer uergencyState, String search) {
        try {
            List<ApplyMaintain> applyMaintainList = applyMaintainDao.exportInfo(type, state, uergencyState, search, search, search, search, search, search, search);
            int row = 0;
            int col = 0;
            if (CollectionUtils.isNotEmpty(applyMaintainList)) {
                for (ApplyMaintain Item : applyMaintainList) {
                    ApplyMaintainDto dto = EntityToDto(Item);
                    row++;
                    col = 0;
                    sheet.addCell(new Label(col++, row, dto.getApplyName()));//申请者姓名
                    sheet.addCell(new Label(col++, row, dto.getApplyPhone()));//申请者电话
                    sheet.addCell(new Label(col++, row, dto.getCreateTimeStr()));//时间
                    sheet.addCell(new Label(col++, row, dto.getTypeStr()));//报修类型
                    sheet.addCell(new Label(col++, row, dto.getUergencyStateStr()));//紧急程度
                    sheet.addCell(new Label(col++, row, dto.getApplyDetail()));//报修详情
                    sheet.addCell(new Label(col++, row, dto.getStateStr()));//处理状态
                    sheet.addCell(new Label(col++, row, dto.getClassRoomName()));//教室名称
                    sheet.addCell(new Label(col++, row, dto.getAdminAddrice()));//管理员备注信息
                    sheet.addCell(new Label(col++, row, dto.getMaintainPeople()));//维修人员姓名
                    sheet.addCell(new Label(col++, row, dto.getMaintainPeoplePhone()));//维修人员电话
                    sheet.addCell(new Label(col++, row, dto.getMessage()));//发送的短信内容
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
