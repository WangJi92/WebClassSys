package com.hdu.cms.modules.Equipment.service.impl;

import com.google.common.collect.Lists;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.RequestResponseContext.RequestResponseContext;
import com.hdu.cms.common.Utils.ExcelUtil;
import com.hdu.cms.common.Utils.HttpRequestUtils;
import com.hdu.cms.common.Utils.LogUtils;
import com.hdu.cms.modules.Dictionary.service.impl.DictionaryService;
import com.hdu.cms.modules.Equipment.dao.EquipmentDao;
import com.hdu.cms.modules.Equipment.dto.EquipmentDto;
import com.hdu.cms.modules.Equipment.entity.Equipment;
import com.hdu.cms.modules.Equipment.service.IEquipmentService;
import com.hdu.cms.modules.UserInfo.dto.UserInfoDto;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by JetWang on 2016/10/24.
 */
@Service
public class EquipmentService implements IEquipmentService{

    @Resource
    private EquipmentDao equipmentDao;


    @Override
    public PageBean findPageInfo(Integer pageSize, Integer pageNo, String searchText) {
        PageBean<Equipment> oldPageBean = equipmentDao.equipPageBean(pageNo,pageSize,searchText);
        PageBean newPageBean = new PageBean();
        if(CollectionUtils.isNotEmpty(oldPageBean.getRows())){
            BeanUtils.copyProperties(oldPageBean,newPageBean);
            List<EquipmentDto>  equipmentDtos = Lists.newArrayList();
            for(Equipment  Item : oldPageBean.getRows()){
                equipmentDtos.add(EntityToDto(Item));
            }
            newPageBean.setRows(equipmentDtos);
        }
        return newPageBean;
    }
    /**
     * 将设备数据中的 设备类型修改一下
     * @param equipment
     * @return
     */
    private EquipmentDto EntityToDto(Equipment  equipment){
        if(equipment != null){
            EquipmentDto dto = new EquipmentDto();
            BeanUtils.copyProperties(equipment,dto);
            if(equipment.getType() !=null){
                Map<Integer,String> equipmentTypeMap = DictionaryService.mapInteger.get(DICTIONARY.EQUIPMENT);
                dto.setTypeStr(equipmentTypeMap.get(equipment.getType()));
            }
            return dto;
        }
        return null;
    }

    @Override
    public void saveOrUpdate(Equipment info) {
        equipmentDao.equipSaveORUpdate(info);
    }

    @Override
    public Equipment getEquipmentInfoById(Integer id) {
        Equipment equipment = equipmentDao.equipFindBuyId(id);
        return equipment;
    }

    @Override
    public Equipment getEquipInfoByIndexCode(String indexCode) {
        Equipment equipment = equipmentDao.equipFindBuyIndexcode(indexCode);
        return equipment;
    }
    @Override
    public List<Equipment> getAllInfo() {

        List<Equipment> equipments = equipmentDao.equipFindAll();
        return equipments;
    }
    @Override
    public void deleteByIds(List<Integer> ids) {
         equipmentDao.equipDeleteByIds(ids);
    }

    @Override
    public void deleteById(Integer id) {
        equipmentDao.equipDeleteById(id);
    }

    @Override
    public void deleteByIndexCode(String indexCode) {
        equipmentDao.equipDeleteByIndexcode(indexCode);
    }

    @Override
    public void deleteByIndexCodes(List<String> indexCodes) {
        equipmentDao.equipDeleteByIndexcode(indexCodes);
    }

    @Override
    public void exportEquipment() {
        OutputStream output = ExcelUtil.getOutputStreamForExcelExport(RequestResponseContext.getResponse(), "equipment");
        WritableWorkbook wbook = null;
        InputStream input = null;
        try {
            //获得gen 目录
            input = new FileInputStream(HttpRequestUtils.getWebContextRootPath()+File.separator+
                    ConstantParam.DEFAULT_EXCEL_PATH
                    +ConstantParam.EXPORT_EQUIPMENT);
            //从之前存在的xls中创建新的excel
            wbook = Workbook.createWorkbook(output, Workbook.getWorkbook(input));
            WritableSheet sheet = wbook.getSheet(0);
            exportEquipment(sheet);
        } catch (IOException e){
            e.printStackTrace();
            LogUtils.logException(e);
        }
        catch (BiffException e){
            e.printStackTrace();
            LogUtils.logException(e);
        }finally {
            try {
                if(wbook!=null){
                    wbook.write();
                    wbook.close();
                }
                if(output != null){
                    output.flush();
                    output.close();
                }
                if(input != null){
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
    private void exportEquipment(WritableSheet sheet){
        int row =0;
        int col =0;
        Map<Integer,String> equipmentTypeMap = DictionaryService.mapInteger.get(DICTIONARY.EQUIPMENT);
        try {
            List<Equipment> equipmentList= getAllInfo();
            if(CollectionUtils.isNotEmpty(equipmentList)){
                for(Equipment Item : equipmentList){
                    row++;
                    col=0;
                    sheet.addCell(new Label(col++,row,Item.getName()));//设备名称
                    sheet.addCell(new Label(col++,row,equipmentTypeMap.get(Item.getType())));//设备类型
                    sheet.addCell(new Label(col++,row,Item.getBrandName()));//品牌名称
                    sheet.addCell(new Label(col++,row,Item.getIntroduce()));//简介
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public EquipmentDto getEquipmentDtoByIndexcode(String indexcode) {
        Equipment equipment = equipmentDao.equipFindBuyIndexcode(indexcode);
        return EntityToDto(equipment);
    }
}
