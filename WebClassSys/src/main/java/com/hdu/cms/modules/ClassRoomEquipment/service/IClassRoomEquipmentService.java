package com.hdu.cms.modules.ClassRoomEquipment.service;

import com.hdu.cms.modules.ClassRoomEquipment.dto.ClassRoomEquipmentDto;
import com.hdu.cms.modules.ClassRoomEquipment.entity.ClassRoomEquipment;
import com.hdu.cms.modules.Equipment.dto.EquipmentDto;

import java.util.List;

/**
 * Created by JetWang on 2016/11/9.
 */
public interface IClassRoomEquipmentService {


    public void saveOrUpdate(ClassRoomEquipment classRoomEquipment);

    public List<ClassRoomEquipmentDto> findAllByClassRoomIndexCode(String classroomIndex);

    public void deleteById(Integer id);

    public void deleteByClassRoomIndexcode(String  indexcode);






}
