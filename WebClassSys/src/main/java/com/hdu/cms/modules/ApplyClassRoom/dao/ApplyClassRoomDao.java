package com.hdu.cms.modules.ApplyClassRoom.dao;

import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.modules.ApplyClassRoom.entity.ApplyClassRoom;
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
public class ApplyClassRoomDao extends HibernateEntityDao<ApplyClassRoom> {

    public void acSaveOrUpdate(ApplyClassRoom applyClassRoom) {
        super.saveOrUpdate(applyClassRoom);
        super.flush();
    }

    public void acdeleteById(Integer id) {
        super.deleteById(id);
        super.flush();
    }

    public ApplyClassRoom acFindById(Integer id) {
        return super.findById(id);
    }

    /**
     * TODO 根据各种信息获取查询的数据
     *
     * @param pageNo
     * @param pageSize
     * @param applyIndexCode
     * @param applicant
     * @param phone
     * @param purpose
     * @param applyReason
     * @param classRoomIndexCode
     * @param classRoomName
     * @param state
     * @param handleAddvice
     * @param whichLesson
     * @return
     */
    public PageBean acGetPageBean(Integer pageNo,
                                  Integer pageSize,
                                  String applyIndexCode,//申请人Indexcode
                                  String applicant,//申请人
                                  String phone,//电话
                                  Integer purpose,//申请类型！
                                  String applyReason,//原因
                                  String classRoomIndexCode,//申请教室
                                  String classRoomName,//教室名称
                                  Integer state,//状态
                                  String handleAddvice,//处理意见
                                  String whichLesson//哪一节课
    ) {

        DetachedCriteria detachedCriteria1 =getDetachedCriteria(applyIndexCode,applicant,phone,purpose,applyReason,classRoomIndexCode,classRoomName,state,handleAddvice,whichLesson);
        return super.findPageByCriteria(detachedCriteria1, pageNo, pageSize);
    }

    public List<ApplyClassRoom> acfindAllByCondition(Integer state,String search){
        DetachedCriteria detachedCriteria1 =getDetachedCriteria(null,search,search,null,search,null,search,state,search,search);
        return super.findAllByCriteria(detachedCriteria1);
    }

    private DetachedCriteria getDetachedCriteria(String applyIndexCode,//申请人Indexcode
                                                 String applicant,//申请人
                                                 String phone,//电话
                                                 Integer purpose,//申请类型！
                                                 String applyReason,//原因
                                                 String classRoomIndexCode,//申请教室
                                                 String classRoomName,//教室名称
                                                 Integer state,//状态
                                                 String handleAddvice,//处理意见
                                                 String whichLesson)//哪一节课
    {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ApplyClassRoom.class);
        if (StringUtils.isNotEmpty(applyIndexCode)) {
            detachedCriteria.add(Restrictions.eq("applyIndexCode", applyIndexCode));
        }
        if (StringUtils.isNotEmpty(classRoomIndexCode)) {
            detachedCriteria.add(Restrictions.eq("classRoomIndexCode", classRoomIndexCode));
        }
        if (purpose != null && purpose != -1) {
            detachedCriteria.add(Restrictions.eq("purpose", purpose));
        }
        if (state != null && state != -1) {
            detachedCriteria.add(Restrictions.eq("state", state));
        }
        //下面的都是处理 or 语句的
        Disjunction disjunction = Restrictions.disjunction();
        if (StringUtils.isNotEmpty(applicant)) {
            disjunction.add(Restrictions.like("applicant", "%" + applicant + "%"));
        }
        if (StringUtils.isNotEmpty(phone)) {
            disjunction.add(Restrictions.like("phone", "%" + phone + "%"));
        }
        if (StringUtils.isNotEmpty(applyReason)) {
            disjunction.add(Restrictions.like("applyReason", "%" + applyReason + "%"));
        }
        if (StringUtils.isNotEmpty(classRoomName)) {
            disjunction.add(Restrictions.like("classRoomName", "%" + classRoomName + "%"));
        }
        if (StringUtils.isNotEmpty(handleAddvice)) {
            disjunction.add(Restrictions.like("handleAddvice", "%" + handleAddvice + "%"));
        }
        if (StringUtils.isNotEmpty(whichLesson)) {
            disjunction.add(Restrictions.like("whichLesson", "%" + whichLesson + "%"));
        }
        detachedCriteria.add(disjunction);
        detachedCriteria.addOrder(Order.asc("state"));
        detachedCriteria.addOrder(Order.desc("createTime"));
        return detachedCriteria;
    }





}
