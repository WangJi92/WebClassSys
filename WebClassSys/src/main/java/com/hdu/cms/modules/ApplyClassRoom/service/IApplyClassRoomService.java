package com.hdu.cms.modules.ApplyClassRoom.service;

import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.modules.ApplyClassRoom.entity.ApplyClassRoom;

/**
 * Created by JetWang on 2016/11/14.
 */
public interface IApplyClassRoomService {

    /**
     * 添加
     *
     * @param applyClassRoom
     */
    public void save(ApplyClassRoom applyClassRoom, ActionResult result);

    /**
     * 更新请求的数据信息
     *
     * @param id            数据Id
     * @param state         更新请求的状态
     * @param handleAddvice 管理员的意见
     */
    public void update(Integer id, Integer state, String handleAddvice);


    public void updateCanccelApply(Integer id);


    /**
     * 根据Id去删除结果~
     *
     * @param id
     */
    public void deleteById(Integer id);

    /**
     * 根据主键获取值！
     *
     * @param id
     * @return
     */
    public ApplyClassRoom findById(Integer id);

    public PageBean getPageBean(Integer pageNo,
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
                                String whichLesson);//哪一节课


    public void exportExcel(Integer state,String search);


}


