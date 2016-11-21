package com.hdu.cms.modules.ApplyMaintain.dao;

import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.modules.ApplyClassRoom.entity.ApplyClassRoom;
import com.hdu.cms.modules.ApplyMaintain.entity.ApplyMaintain;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JetWang on 2016/11/14.
 */
@Repository
public class ApplyMaintainDao extends HibernateEntityDao<ApplyMaintain> {

    /**
     * @param applyMaintain
     */
    public void amSaveOrUpdate(ApplyMaintain applyMaintain) {
        super.saveOrUpdate(applyMaintain);
        super.flush();
    }

    public ApplyMaintain amFindById(Integer id) {
        return super.findById(id);
    }

    /**
     * @param id
     */
    public void amDeleteById(Integer id) {
        super.deleteById(id);
        super.flush();
    }

    /**
     * @param pageNo
     * @param pageSize
     * @param type
     * @param state
     * @param uergencyState
     * @param ClassRoomName
     * @param applyName
     * @param applyPhone
     * @param maintainPeople
     * @param maintainPeoplePhone
     * @param adminAddrice
     * @return
     */
    public PageBean amGetPageBean(Integer pageNo,
                                  Integer pageSize,
                                  Integer type,//维护类型
                                  Integer state,//处理的状态
                                  Integer uergencyState,//紧急程度
                                  String ClassRoomName,//教室名称
                                  String applyName,//申请者名字
                                  String applyPhone,//申请者的电话
                                  String maintainPeople,//维护人员
                                  String maintainPeoplePhone,//维护人员的电话
                                  String adminAddrice,//管理员意见
                                  String applyDetail,//维护信息的详细说明
                                  String applyIndexCode//申请者indexcode
    ) {
        DetachedCriteria detachedCriteria = searchInfo(type,state,uergencyState,ClassRoomName,applyName,applyPhone,maintainPeople,maintainPeoplePhone,adminAddrice,applyDetail,applyIndexCode);
        return super.findPageByCriteria(detachedCriteria, pageNo, pageSize);
    }

    private DetachedCriteria searchInfo(Integer type,//维护类型
                                        Integer state,//处理的状态
                                        Integer uergencyState,//紧急程度
                                        String ClassRoomName,//教室名称
                                        String applyName,//申请者名字
                                        String applyPhone,//申请者的电话
                                        String maintainPeople,//维护人员
                                        String maintainPeoplePhone,//维护人员的电话
                                        String adminAddrice,//管理员意见
                                        String applyDetail,//维护信息的详细说明
                                        String applyIndexCode//申请者indexcode
    ) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ApplyMaintain.class);
        if (state != null && state != -1) {
            detachedCriteria.add(Restrictions.eq("state", state));
        }
        if (type != null && type != -1) {
            detachedCriteria.add(Restrictions.eq("type", type));
        }
        if(StringUtils.isNotEmpty(applyIndexCode)){
            detachedCriteria.add(Restrictions.eq("applyIndexCode", applyIndexCode));
        }

        if (uergencyState != null && uergencyState != -1) {
            detachedCriteria.add(Restrictions.eq("uergencyState", uergencyState));
        }
        Disjunction disjunction = Restrictions.disjunction();
        if (StringUtils.isNotEmpty(ClassRoomName))
        {
            disjunction.add(Restrictions.like("classRoomName", "%" + ClassRoomName + "%"));
        }
        if (StringUtils.isNotEmpty(applyName))
        {
            disjunction.add(Restrictions.like("applyName", "%" + applyName + "%"));
        }
        if (StringUtils.isNotEmpty(applyPhone))
        {
            disjunction.add(Restrictions.like("applyPhone", "%" + applyPhone + "%"));
        }
        if (StringUtils.isNotEmpty(maintainPeople))
        {
            disjunction.add(Restrictions.like("maintainPeople", "%" + maintainPeople + "%"));
        }
        if (StringUtils.isNotEmpty(maintainPeoplePhone))
        {
            disjunction.add(Restrictions.like("maintainPeoplePhone", "%" + maintainPeoplePhone + "%"));
        }
        if (StringUtils.isNotEmpty(adminAddrice))
        {
            disjunction.add(Restrictions.like("adminAddrice", "%" + adminAddrice + "%"));
        }
        if (StringUtils.isNotEmpty(applyDetail))
        {
            disjunction.add(Restrictions.like("applyDetail", "%" + applyDetail + "%"));
        }
        detachedCriteria.add(disjunction);
        detachedCriteria.addOrder(Order.asc("state"));
        detachedCriteria.addOrder(Order.desc("createTime"));
        return detachedCriteria;
    }

    /**
     * 导出excel的时候使用的数据信息
     * @param type
     * @param state
     * @param uergencyState
     * @param ClassRoomName
     * @param applyName
     * @param applyPhone
     * @param maintainPeople
     * @param maintainPeoplePhone
     * @param adminAddrice
     * @param applyDetail
     * @return
     */
    public List<ApplyMaintain> exportInfo(Integer type,//维护类型
                                          Integer state,//处理的状态
                                          Integer uergencyState,//紧急程度
                                          String ClassRoomName,//教室名称
                                          String applyName,//申请者名字
                                          String applyPhone,//申请者的电话
                                          String maintainPeople,//维护人员
                                          String maintainPeoplePhone,//维护人员的电话
                                          String adminAddrice,//管理员意见
                                          String applyDetail//维护信息的详细说明
    ) {
        DetachedCriteria detachedCriteria = searchInfo(type,state,uergencyState,ClassRoomName,applyName,applyPhone,maintainPeople,maintainPeoplePhone,adminAddrice,applyDetail,null);
        return super.findAllByCriteria(detachedCriteria);
    }

    /**
     * 查找当前用户的所有的信息
     * @param ApplyIndexcode
     * @return
     */

    public List<ApplyMaintain> getALlByApplyIndexcode(String ApplyIndexcode){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ApplyMaintain.class);
        detachedCriteria.add(Restrictions.eq("applyIndexCode",ApplyIndexcode));
        detachedCriteria.addOrder(Order.asc("state"));
        detachedCriteria.addOrder(Order.desc("createTime"));
        return  super.findAllByCriteria(detachedCriteria);
    }
}
