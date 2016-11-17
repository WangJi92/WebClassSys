package com.hdu.cms.modules.ClassRoom.service.impl;

import com.google.common.collect.Lists;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.modules.Building.dto.BuildingInfoDto;
import com.hdu.cms.modules.Building.service.IBuildingInfoService;
import com.hdu.cms.modules.ClassRoom.dao.ClassRoomDao;
import com.hdu.cms.modules.ClassRoom.dto.ClassRoomDto;
import com.hdu.cms.modules.ClassRoom.entity.ClassRoom;
import com.hdu.cms.modules.ClassRoom.service.IClassRoomService;
import com.hdu.cms.modules.ClassRoomEquipment.service.IClassRoomEquipmentService;
import com.hdu.cms.modules.Dictionary.service.impl.DictionaryService;
import com.hdu.cms.modules.TimeTable.service.ITimeTableService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.Entity;
import java.util.List;
import java.util.Map;

/**
 * Created by JetWang on 2016/11/4.
 * 教室的类
 */
@Service
public class ClassRoomService  implements IClassRoomService{
    @Resource
    private ClassRoomDao classRoomDao;
   @Resource
   private ITimeTableService iTimeTableService;
    @Resource
    private IClassRoomEquipmentService iClassRoomEquipmentService;

    @Resource
    private IBuildingInfoService iBuildingInfoService;//教学楼信息查询

    @Override
    public PageBean findPageInfo(Integer pageSize, Integer pageNo, String name, String buildindexcode,Integer type) {
        PageBean oldPageBean = classRoomDao.cmPageBean(pageNo,pageSize,name,buildindexcode,type);
        if(oldPageBean!=null && CollectionUtils.isNotEmpty(oldPageBean.getRows())){
            PageBean newPageBean = new PageBean();
            List<ClassRoom> classRoomList = oldPageBean.getRows();
            BeanUtils.copyProperties(oldPageBean,newPageBean);
            List<ClassRoomDto> classRoomDtoList = Lists.newArrayList();
            for(ClassRoom Item : classRoomList){
                classRoomDtoList.add(entityToDto(Item));
            }
            newPageBean.setRows(classRoomDtoList);
            return  newPageBean;
        }
        return new PageBean();
    }
    public ClassRoomDto entityToDto(ClassRoom Item){
        ClassRoomDto dto = new ClassRoomDto();
        Map<Integer,String> map = DictionaryService.mapInteger.get(DICTIONARY.CLASSROOM);
        BeanUtils.copyProperties(Item,dto);
        dto.setTypeName(map.get(Item.getType()));
        if(StringUtils.isNotEmpty(Item.getBuildingIndexCode())){
            /**
             * todo 加载教学楼信息字段 包括教学楼的名称 教学楼值班人员的信息字段
             */
           BuildingInfoDto buildingInfoDto = iBuildingInfoService.getBuildingInfoByIndexCode(Item.getBuildingIndexCode());
            if(buildingInfoDto !=null){
                dto.setBuildingName(buildingInfoDto.getName());
                dto.setMaintancePeoplePhone(buildingInfoDto.getMaintancePeoplePhone());
                dto.setMaintancePeopleName(buildingInfoDto.getMaintancePeopleName());
                dto.setDutyRoomPeopleName(buildingInfoDto.getDutyRoomPeopleName());
                dto.setDutyRoomPeoplePhone(buildingInfoDto.getDutyRoomPeoplePhone());
            }
        }
        return dto;
    }

    /**
     * 保存信息 包括创建课程表 设备等等
     * @param classRoom
     */
    @Override
    public void saveOrUpdate(ClassRoom classRoom) {
        classRoomDao.cmsSaveORUpdate(classRoom);
    }

    @Override
    public ClassRoomDto getClassRoomInfoById(Integer id) {
        ClassRoom classRoom = classRoomDao.cmFindBuyId(id);
        return entityToDto(classRoom);
    }

    @Override
    public ClassRoomDto getClassRoomInfoByIndexCode(String indexCode) {
        ClassRoom classRoom = classRoomDao.cmFindBuyIndexcode(indexCode);
        return entityToDto(classRoom);
    }

    @Override
    public List<ClassRoomDto> getAllInfo() {
        List<ClassRoom> classRoomList = classRoomDao.cmFindAll();
        List<ClassRoomDto> classRoomDtoList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(classRoomList)){
            for(ClassRoom Item : classRoomList){
                classRoomDtoList.add(entityToDto(Item));
            }
        }
        return classRoomDtoList;
    }

    /**
     * 删除教室需要做点什么工作
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        classRoomDao.cmsDeleteById(id);
    }

    /**
     * todo 删除和当前的教室相关的信息哦
     * @param indexcode
     */
    @Override
    public void deleteByIndexcode(String indexcode) {
        classRoomDao.cmDeleteByIndexcode(indexcode);
        iClassRoomEquipmentService.deleteByClassRoomIndexcode(indexcode);
        iTimeTableService.deleteByClassRoomIndexCode(indexcode);

    }

    @Override
    public ClassRoom findBuyName(String name) {
        return classRoomDao.cmFindBuyName(name);
    }

    @Override
    public ClassRoom findBuyIdEntity(Integer id) {
        return classRoomDao.cmFindBuyId(id);
    }

    @Override
    public ClassRoom findBuyIndexCodeEntity(String indexcode) {
        return classRoomDao.cmFindBuyIndexcode(indexcode);
    }

    @Override
    public boolean BuidlingCountOfClasRoom(String buildingIndexcode) {
        if(StringUtils.isNotEmpty(buildingIndexcode)){
            int count = classRoomDao.cmFindClassRoomCount(buildingIndexcode);
            if(count>=1){
                return  true;
            }
        }
        return false;
    }
}
