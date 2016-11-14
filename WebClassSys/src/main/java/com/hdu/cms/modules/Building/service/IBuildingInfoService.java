package com.hdu.cms.modules.Building.service;

import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.modules.Building.dto.BuildingInfoDto;
import com.hdu.cms.modules.Building.entity.BuidingInfo;

import java.util.List;

/**
 * Created by JetWang on 2016/10/11.
 *楼宇信息
 */
public interface IBuildingInfoService {

    public PageBean  findPageInfo(Integer pageNo,Integer pageSize,String search);

    public void saveOrUpdate(BuidingInfo info);

    public BuildingInfoDto getBuildingInfoById(Integer id);

    public BuidingInfo  getBuildingInfoByIdEntity(Integer idEntity);

    public BuildingInfoDto getBuildingInfoByIndexCode(String indexCode);

    public List<BuildingInfoDto> getAllInfo();

    public void deleteByIds(List<Integer> ids);

    public void deleteById(Integer id);

    public  void deleteByIndexCode(String indexCode);

    public void deleteByIndexCodes(List<String> indexCodes);
    public BuidingInfo getBuildInfoByName(String name);
}
