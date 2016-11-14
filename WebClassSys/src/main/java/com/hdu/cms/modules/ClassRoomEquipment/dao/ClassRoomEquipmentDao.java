package com.hdu.cms.modules.ClassRoomEquipment.dao;

import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.modules.ClassRoomEquipment.entity.ClassRoomEquipment;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * todo 教室中的设备信息的维护
 * Created by JetWang on 2016/11/9.
 */
@Repository
public class ClassRoomEquipmentDao extends HibernateEntityDao<ClassRoomEquipment> {

    /**
     * todo 展示查找所有的设备的信息
     * @return
     */
    public List<ClassRoomEquipment> classRoomEquipmentFindAll(String classRoomIndexcode){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ClassRoomEquipment.class);
        detachedCriteria.add(Restrictions.eq("classRoomIndexCode",classRoomIndexcode));
        return super.findAllByCriteria(detachedCriteria);
    }

    /**
     * 保存设备的信息
     * @param classRoomEquipment
     */
    public void classropmEquipmentSaveOrUpdate(ClassRoomEquipment classRoomEquipment){
        super.saveOrUpdate(classRoomEquipment);
        super.flush();
    }

    /**
     * 删除设备的信息
     * @param id
     */
    public void classroomDeleteById(Integer id){
        super.deleteById(id);
        super.flush();
    }

    /**
     * todo 删除所有的教师相关的东西
     * @param indexcode
     */
    public void classroomDeleteByIndexcode(String  indexcode){
        super.executeUpdateHql("delete  from ClassRoomEquipment  where classRoomIndexCode ='" + indexcode + "'");
    }




}
