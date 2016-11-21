package com.hdu.cms.modules.ApplyMaintain.service;

import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.modules.ApplyMaintain.dto.ApplyMaintainDto;
import com.hdu.cms.modules.ApplyMaintain.entity.ApplyMaintain;

import java.util.List;

/**
 * Created by JetWang on 2016/11/14.
 */
public interface IApplyMaintainService {

    public void save(ApplyMaintain applyMaintain);

    /**
     * 修改表的状态和数据信息
     *
     * @param id
     * @param state
     * @param adminAdrice
     */
    public void update(Integer id, Integer state, String adminAdrice ,String maintainIndexCode,String maintainPeople,String maintainPeoplePhone,String message);

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
    public PageBean getPageBean(Integer pageNo,
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
    );

    /**
     *
     * @param id
     */
    public void deleteById(Integer id);

    /**
     *
     * @param id
     * @return
     */
    public ApplyMaintain findByEnetityId(Integer id);

    public ApplyMaintainDto findByDtoId(Integer id);

    public void ExportApplyMainInfo(Integer state,Integer type,Integer uergencyState,String search);

    public List<ApplyMaintainDto> getALlMaintainInfoByApplyIndexcode(String applyIndexoce);

}
