package com.hdu.cms.modules.Building.dao;

import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ArrayUtils;
import com.hdu.cms.modules.Building.entity.BuidingInfo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;
import java.util.List;

/**
 * Created by JetWang on 2016/10/11.
 * 楼宇的基本信息
 */
@Repository(value = "buildingDao")
public class BuildingDao extends HibernateEntityDao<BuidingInfo>{

    public void buildSaveORUpdate(BuidingInfo info){
        super.saveOrUpdate(info);
        super.flush();
    }
    public void buildDeleteById(Integer id){
        super.deleteById(id);
    }

    /**
     * 通过id 删除所有的信息
     * @param ids
     */
    public void buildDeleteByIds(List<Integer> ids){
        String idsStr = ArrayUtils.listToDotString(ids);
        super.deleteAll("id",idsStr);
    }

    /**
     * 根据idxCodes删除所有的信息
     * @param indexcodes
     */
    public void buildDeleteByIndexcode(List<String> indexcodes){
        String indexcodesStr = ArrayUtils.listToDotString(indexcodes);
        super.deleteAll("indexCode",indexcodesStr);
    }
    public void buildDeleteByIndexcode(String  indexcode){
        super.executeUpdateHql("delete  from BuidingInfo  where indexCode ='" + indexcode + "'");
    }
    public BuidingInfo buildFindBuyId(Integer id){
        return super.findById(id);
    }
    public BuidingInfo buildFindBuyIndexcode(String indexcode){
         return super.findSingleBy("indexCode",indexcode);
    }
    public BuidingInfo buildFindBuyName(String name){
        return super.findSingleBy("name",name);
    }

    /**
     * 查找到所有的信息
     * @return
     */
    public List<BuidingInfo> buildFindAll(){
        return super.findALl();
    }

    /**
     * 查找分页，根据信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean<BuidingInfo> buildPageBean(Integer pageNo,Integer pageSize,String search){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BuidingInfo.class);
        detachedCriteria.addOrder(org.hibernate.criterion.Order.asc("id"));
        if(StringUtils.isNotEmpty(search)){
            detachedCriteria.add(Restrictions.like("name","%"+search+"%"));
        }
        return super.findPageByCriteria(detachedCriteria,pageNo,pageSize);
    }
}
