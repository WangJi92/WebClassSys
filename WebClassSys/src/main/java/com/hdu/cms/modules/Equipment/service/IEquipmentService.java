package com.hdu.cms.modules.Equipment.service;

import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.modules.Building.dto.BuildingInfoDto;
import com.hdu.cms.modules.Equipment.entity.Equipment;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;

import java.util.List;

/**
 * Created by JetWang on 2016/10/11.
 * 设备信息登记哦
 */
public interface IEquipmentService {

    /**
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageBean findPageInfo(Integer pageSize,Integer pageNo,String searchText);



    public void saveOrUpdate(Equipment info);

    /**
     * @param id
     * @return
     */
    public Equipment getEquipmentInfoById(Integer id);

    /**
     *
     * @param indexCode
     * @return
     */
    public Equipment getEquipInfoByIndexCode(String indexCode);
    /**
     *
     * @return
     */
    public List<Equipment> getAllInfo();

    /**
     *
     * @param ids
     */
    public void deleteByIds(List<Integer> ids);

    /**
     *
     * @param id
     */
    public void deleteById(Integer id);

    /**
     *
     * @param indexCode
     */
    public  void deleteByIndexCode(String indexCode);

    /**
     *
     * @param indexCodes
     */
    public void deleteByIndexCodes(List<String> indexCodes);

    /**
     * 导出数据信息
     */
    public  void  exportEquipment();
}
