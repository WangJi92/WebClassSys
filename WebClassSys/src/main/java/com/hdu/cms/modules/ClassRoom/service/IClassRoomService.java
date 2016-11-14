package com.hdu.cms.modules.ClassRoom.service;

import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.modules.Building.dto.BuildingInfoDto;
import com.hdu.cms.modules.ClassRoom.dto.ClassRoomDto;
import com.hdu.cms.modules.ClassRoom.entity.ClassRoom;

import java.util.List;

/**
 * Created by JetWang on 2016/10/11.
 */
public interface IClassRoomService {
    /**
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageBean findPageInfo(Integer pageSize,Integer pageNo,String name,String buildindexcode);


    public void saveOrUpdate(ClassRoom classRoom);

    /**
     * @param id
     * @return
     */
    public ClassRoomDto getClassRoomInfoById(Integer id);

    /**
     *
     * @param indexCode
     * @return
     */
    public ClassRoomDto getClassRoomInfoByIndexCode(String indexCode);

    /**
     *
     * @return
     */
    public List<ClassRoomDto> getAllInfo();

    /**
     *
     * @param id
     */
    public void deleteById(Integer id);


    /**
     * 删除教室 通过indexcode
     * 除了这个之外还有删除教室设备表 教室课表
     * @param indexcode
     */
    public void deleteByIndexcode(String indexcode);

    public ClassRoom findBuyName(String name);

    public ClassRoom findBuyIdEntity(Integer id);
    public ClassRoom findBuyIndexCodeEntity(String indexcode);

    /**
     * todo 查找当前的楼宇中是否存在教室
     * @param buildingIndexcode
     * @return
     */
    public boolean BuidlingCountOfClasRoom(String  buildingIndexcode);



}
