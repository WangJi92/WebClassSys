package com.hdu.cms.modules.ClassRoom.dao;

import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ArrayUtils;
import com.hdu.cms.modules.Building.entity.BuidingInfo;
import com.hdu.cms.modules.ClassRoom.entity.ClassRoom;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JetWang on 2016/10/11.
 * 教室信息的修改哦
 */
@Repository
public class ClassRoomDao extends HibernateEntityDao<ClassRoom> {

    public void cmsSaveORUpdate(ClassRoom info){
        super.saveOrUpdate(info);
        super.flush();
    }
    public void cmsDeleteById(Integer id){
        super.deleteById(id);
        super.flush();
    }

    /**
     * 通过id 删除所有的信息
     * @param ids
     */
    public void cmDeleteByIds(List<Integer> ids){
        String idsStr = ArrayUtils.listToDotString(ids);
        super.deleteAll("id",idsStr);
    }

    /**
     * 根据idxCodes删除所有的信息
     * @param indexcodes
     */
    public void cmDeleteByIndexcode(List<String> indexcodes){
        String indexcodesStr = ArrayUtils.listToDotString(indexcodes);
        super.deleteAll("indexCode",indexcodesStr);
    }
    public void cmDeleteByIndexcode(String  indexcode){
        super.executeUpdateHql("delete  from ClassRoom  where indexCode ='" + indexcode + "'");
    }
    public ClassRoom cmFindBuyId(Integer id){
        return super.findById(id);
    }
    public ClassRoom cmFindBuyIndexcode(String indexcode){
        return super.findSingleBy("indexCode",indexcode);
    }
    public ClassRoom cmFindBuyName(String name){
        return super.findSingleBy("name",name);
    }

    /**
     * 查找到所有的信息
     * @return
     */
    public List<ClassRoom> cmFindAll(){
        return super.findALl();
    }

    /**
     * 查找分页，根据信息
     * 分页进行查询信息，包括name和我们的builidingIndexCode;
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean<ClassRoom> cmPageBean(Integer pageNo,Integer pageSize,String search,String buildingIndexCode){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ClassRoom.class);
        detachedCriteria.addOrder(org.hibernate.criterion.Order.asc("id"));
        if(StringUtils.isNotEmpty(search)){
            detachedCriteria.add(Restrictions.like("name","%"+search+"%"));
        }
        if(StringUtils.isNotEmpty(buildingIndexCode)){
            detachedCriteria.add(Restrictions.eq("buildingIndexCode",buildingIndexCode));
        }
        return super.findPageByCriteria(detachedCriteria,pageNo,pageSize);
    }

    /**
     * todo 查找当前楼宇中教室的数量
     * @param buiildingIndexcode
     * @return
     */
    public int cmFindClassRoomCount(String buiildingIndexcode){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ClassRoom.class);
        detachedCriteria.add(Restrictions.eq("buildingIndexCode",buiildingIndexcode));
        return super.findAllByCriteria(detachedCriteria).size();
    }

}
