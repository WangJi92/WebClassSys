package com.hdu.cms.modules.Equipment.dao;

import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ArrayUtils;
import com.hdu.cms.modules.Equipment.entity.Equipment;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JetWang on 2016/10/11.
 */
@Repository(value = "equipmentDao")
public class EquipmentDao extends HibernateEntityDao<Equipment> {
    public void equipSaveORUpdate(Equipment info){
        super.saveOrUpdate(info);
        super.flush();
    }
    public void equipDeleteById(Integer id){
        super.deleteById(id);
    }

    /**
     * 通过id 删除所有的信息
     * @param ids
     */
    public void equipDeleteByIds(List<Integer> ids){
        String idsStr = ArrayUtils.listToDotString(ids);
        super.deleteAll("id",idsStr);
    }

    /**
     * 根据idxCodes删除所有的信息
     * @param indexcodes
     */
    public void equipDeleteByIndexcode(List<String> indexcodes){
        String indexcodesStr = ArrayUtils.listToDotString(indexcodes);
        super.deleteAll("indexCode",indexcodesStr);
    }
    public void equipDeleteByIndexcode(String  indexcode){
        super.executeUpdateSQL("delete  from Equipment  where indexCode ='" + indexcode + "'");
    }
    public Equipment equipFindBuyId(Integer id){
        return super.findById(id);
    }
    public Equipment equipFindBuyIndexcode(String indexcode){
        return super.findSingleBy("indexCode",indexcode);
    }

    /**
     * 查找到所有的信息
     * @return
     */
    public List<Equipment> equipFindAll(){
        return super.findALl();
    }

    /**
     * 查找分页，根据信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean<Equipment> equipPageBean(Integer pageNo,Integer pageSize,String search){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Equipment.class);
        detachedCriteria.addOrder(Order.asc("type"));
        if(StringUtils.isNotEmpty(search)){
            detachedCriteria.add(Restrictions.disjunction()
                            .add(Restrictions.like("name", "%" + search + "%"))
                            .add(Restrictions.like("brandName", "%" + search + "%"))
                            .add(Restrictions.like("introduce", "%" + search + "%"))

            );
        }
        return super.findPageByCriteria(detachedCriteria,pageNo,pageSize);
    }
}
