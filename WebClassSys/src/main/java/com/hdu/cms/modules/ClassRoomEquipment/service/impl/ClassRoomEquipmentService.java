package com.hdu.cms.modules.ClassRoomEquipment.service.impl;

import com.google.common.collect.Lists;
import com.hdu.cms.modules.ClassRoom.entity.ClassRoom;
import com.hdu.cms.modules.ClassRoomEquipment.dao.ClassRoomEquipmentDao;
import com.hdu.cms.modules.ClassRoomEquipment.dto.ClassRoomEquipmentDto;
import com.hdu.cms.modules.ClassRoomEquipment.entity.ClassRoomEquipment;
import com.hdu.cms.modules.ClassRoomEquipment.service.IClassRoomEquipmentService;
import com.hdu.cms.modules.Equipment.dto.EquipmentDto;
import com.hdu.cms.modules.Equipment.service.IEquipmentService;
import com.hdu.cms.modules.Equipment.service.impl.EquipmentService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JetWang on 2016/11/9.
 */
@Service
public class ClassRoomEquipmentService implements IClassRoomEquipmentService{
    @Resource
    private ClassRoomEquipmentDao classRoomEquipmentDao;
    @Resource
    private IEquipmentService iEquipmentService;


    @Override
    public void saveOrUpdate(ClassRoomEquipment classRoomEquipment) {
        classRoomEquipmentDao.classropmEquipmentSaveOrUpdate(classRoomEquipment);
    }

    @Override
    public List<ClassRoomEquipmentDto> findAllByClassRoomIndexCode(String classroomIndex) {
        List<ClassRoomEquipment> classRoomEquipmentList = classRoomEquipmentDao.classRoomEquipmentFindAll(classroomIndex);
        List<ClassRoomEquipmentDto> classRoomEquipmentDtoList  = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(classRoomEquipmentList)){
            for(ClassRoomEquipment Item :classRoomEquipmentList){
                classRoomEquipmentDtoList.add(EntityToDto(Item));
            }
        }
        return classRoomEquipmentDtoList;
    }

    @Override
    public void deleteById(Integer id) {
       classRoomEquipmentDao.classroomDeleteById(id);
    }

    private ClassRoomEquipmentDto EntityToDto(ClassRoomEquipment classRoomEquipment){
        ClassRoomEquipmentDto dto = new ClassRoomEquipmentDto();
        BeanUtils.copyProperties(classRoomEquipment,dto);
        EquipmentDto equipmentDto = iEquipmentService.getEquipmentDtoByIndexcode(classRoomEquipment.getEquipmentIndexCode());
        if(equipmentDto !=null){
            dto.setBrandName(equipmentDto.getBrandName());
            dto.setIntroduce(equipmentDto.getIntroduce());
            dto.setTypeStr(equipmentDto.getTypeStr());
            dto.setName(equipmentDto.getName());
        }
        return  dto;
    }

    /**
     * 删除当前教室所有的设备
     * @param indexcode
     */
    @Override
    public void deleteByClassRoomIndexcode(String indexcode) {
        classRoomEquipmentDao.classroomDeleteByIndexcode(indexcode);
    }

}
