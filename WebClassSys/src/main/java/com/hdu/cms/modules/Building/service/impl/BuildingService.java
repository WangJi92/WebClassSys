package com.hdu.cms.modules.Building.service.impl;

import com.google.common.collect.Lists;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.JsonUtils;
import com.hdu.cms.modules.Building.dao.BuildingDao;
import com.hdu.cms.modules.Building.dto.BuildingInfoDto;
import com.hdu.cms.modules.Building.entity.BuidingInfo;
import com.hdu.cms.modules.Building.service.IBuildingInfoService;
import com.hdu.cms.modules.Dictionary.service.impl.DictionaryService;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import com.hdu.cms.modules.UserInfo.service.IUserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JetWang on 2016/10/11.
 */
@Service(value = "buildingService")
public class BuildingService  implements IBuildingInfoService{

    @Resource
    private BuildingDao buildingDao;

    @Resource
    private IUserInfoService iUserInfoService;

    @Override
    public PageBean findPageInfo(Integer pageNo, Integer pageSize,String search) {
        PageBean<BuidingInfo> oldPageBean = buildingDao.buildPageBean(pageNo,pageSize,search);
        if(CollectionUtils.isNotEmpty(oldPageBean.getRows())){
            List<BuidingInfo> buidingInfoList = oldPageBean.getRows();
            PageBean newPageBean = new PageBean();
            BeanUtils.copyProperties(oldPageBean,newPageBean);
            List<BuildingInfoDto> dtoList =entityListToDtoList(buidingInfoList);
            newPageBean.setRows(dtoList);
            return  newPageBean;
        }
        return new PageBean();
    }
    /**
     * 转换为dto 增加外键的属性 比如 人员属性  还有 Type属性
     * @param entityList
     * @return
     */
    private  List<BuildingInfoDto> entityListToDtoList(List<BuidingInfo> entityList){
        List<BuildingInfoDto> buildingInfoDtoList = Lists.newArrayList();
        for(BuidingInfo item : entityList){
            buildingInfoDtoList.add(entityToDot(item));
        }
        return buildingInfoDtoList;
    }

    /**
     * 实体对象转为 带有外键信息的属性
     * @param item
     * @return
     */
    private BuildingInfoDto entityToDot(BuidingInfo item){
        BuildingInfoDto dto = new BuildingInfoDto();
        if(item !=null){
            BeanUtils.copyProperties(item,dto);
            UserInfo dutyinfo = iUserInfoService.getUserInfoByIndexCode(item.getDutyRoomPeopleIndexcode());
            UserInfo maintenceInfo = iUserInfoService.getUserInfoByIndexCode(item.getMaintancePeopleIndexcode());
            String phone = "";
            if(dutyinfo !=null){
                if(StringUtils.isNotEmpty(dutyinfo.getPhone())){
                    phone= dutyinfo.getPhone();
                }else if(StringUtils.isNotEmpty(dutyinfo.getTell())){
                    phone = dutyinfo.getTell();
                }else{
                    phone=" ";
                }
                dto.setDutyRoomPeoplePhone(phone);
                dto.setDutyRoomPeopleName(dutyinfo.getUserName());
            }
            if(maintenceInfo !=null){
                if(StringUtils.isNotEmpty(maintenceInfo.getPhone())){
                    phone= maintenceInfo.getPhone();
                }else if(StringUtils.isNotEmpty(maintenceInfo.getTell())){
                    phone = maintenceInfo.getTell();
                }else{
                    phone=" ";
                }
                dto.setMaintancePeopleName(maintenceInfo.getUserName());
                dto.setMaintancePeoplePhone(phone);
            }
            return dto;
        }else{
            return null;
        }
    }

    @Override
    public void saveOrUpdate(BuidingInfo  info) {
        buildingDao.buildSaveORUpdate(info);
    }

    @Override
    public BuildingInfoDto getBuildingInfoById(Integer id) {
        BuidingInfo info = buildingDao.buildFindBuyId(id);
        BuildingInfoDto dto = entityToDot(info);
        return dto;
    }

    @Override
    public BuildingInfoDto getBuildingInfoByIndexCode(String indexCode) {
        BuidingInfo info = buildingDao.buildFindBuyIndexcode(indexCode);
        BuildingInfoDto dto = entityToDot(info);
        return dto;
    }

    @Override
    public List<BuildingInfoDto> getAllInfo() {
        List<BuidingInfo> buidingInfoList = buildingDao.buildFindAll();
        List<BuildingInfoDto> buildingInfoDtoList =entityListToDtoList(buidingInfoList);
        return buildingInfoDtoList;
    }

    /**
     * 这里还需要处理删除教室的动作
     * @param ids
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
       buildingDao.buildDeleteByIds(ids);
    }

    @Override
    public void deleteById(Integer id) {
        buildingDao.buildDeleteById(id);
    }

    @Override
    public void deleteByIndexCode(String indexCode) {
        buildingDao.buildDeleteByIndexcode(indexCode);
    }

    @Override
    public BuidingInfo getBuildInfoByName(String name) {
        return buildingDao.buildFindBuyName(name);
    }

    @Override
    public BuidingInfo getBuildingInfoByIdEntity(Integer idEntity) {
        return buildingDao.buildFindBuyId(idEntity);
    }

    @Override
    public void deleteByIndexCodes(List<String> indexCodes) {
        buildingDao.buildDeleteByIndexcode(indexCodes);
    }
}
