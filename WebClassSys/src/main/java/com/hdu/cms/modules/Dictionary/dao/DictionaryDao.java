package com.hdu.cms.modules.Dictionary.dao;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hdu.cms.common.ConstantParam.STATE;
import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ArrayUtils;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by JetWang on 2016/10/10.
 */
@Repository(value="dictionaryDao")
@Transactional(readOnly = false)
public class DictionaryDao  extends HibernateEntityDao<Dictionary>{

    /**
     * 保存或者更新数据字典
     * @param dictionary
     */
    public void dicSaveOrUpdate(Dictionary dictionary){
        super.saveOrUpdate(dictionary);
        super.flush();
    }

    /**
     * 通过ID查找到Dic
     * @param id
     * @return
     */
    public Dictionary dicFindById(Integer id){
        return super.findById(id);
    }

    /**
     * 通过indexcode找到信息
     * @param indexcode
     * @return
     */
    public Dictionary dicFindByIndexcode(String indexcode){
        return super.findSingleBy("indexCode",indexcode);
    }

    /**
     * 通过Id 删除
     * @param id
     */
    public void deleteById(Integer id){
        super.deleteById(id);
    }

    /**
     * 通过indexcode删除Dic
     * @param indexcode
     */
    public void deleteByIndexcode(String indexcode){
        super.executeUpdateHql("delete  from Dictionary  where indexCode ='" + indexcode + "'");
    }

    /**
     * 通过Id 删除所有的字典
     * @param ids
     */
    public void dicDeleteByIds(List<Integer> ids){
        String idsArry = ArrayUtils.listToDotString(ids);
        super.deleteAll("id",idsArry);
    }

    /**
     * 通过indexcode删除数据字典
     * @param indexcodes
     */
    public void dicDeleteByIndexCode(List<String> indexcodes){
        String indexcodeArry = ArrayUtils.listToDotString(indexcodes);
        super.deleteAll("indexCode",indexcodeArry);
    }

    /**
     * 根据定义好的,类型的值去查找信息
     * @param classfiyType
     * @return
     */
    public List<Dictionary> dicGetAllClassfyByType(String classfiyType){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dictionary.class);
        detachedCriteria.add(Restrictions.eq("fatherState", STATE.NO.getValue()));
        detachedCriteria.add(Restrictions.eq("classfiyType",classfiyType));
        List<Dictionary> dictionaries = super.findAllByCriteria(detachedCriteria);
        if(CollectionUtils.isNotEmpty(dictionaries)){
            return dictionaries;
        }
        return Lists.newArrayList();
    }

    /**
     * 找到当前父节点的所有的信息
     * @return
     */
    public List<Dictionary> dicGetFatherClassFy(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dictionary.class);
        detachedCriteria.add(Restrictions.eq("fatherState",STATE.YES.getValue()));
        List<Dictionary> dictionaries = super.findAllByCriteria(detachedCriteria);
        if(CollectionUtils.isNotEmpty(dictionaries)){
            return dictionaries;
        }
        return Lists.newArrayList();
    }

    /**
     * 得到当前类型的Map数组
     * @param classfiyType
     * @return
     */
    public Map<String,Integer> dicGetAllMapKeyStringClassfyByType(String classfiyType){
        List<Dictionary> dictionaryList =dicGetAllClassfyByType(classfiyType);
        Map<String,Integer> map = Maps.newHashMap();
        if(CollectionUtils.isNotEmpty(dictionaryList)){
            for(Dictionary item : dictionaryList){
                map.put(item.getName(),item.getValue());
            }
        }
        return map;
    }
    /**
     * 得到当前类型的Map数组 rev
     * @param classfiyType
     * @return
     */
    public Map<Integer,String> dicGetAllMapKeyIntegerClassfyByType(String classfiyType){
        List<Dictionary> dictionaryList =dicGetAllClassfyByType(classfiyType);
        Map<Integer,String> map = Maps.newHashMap();
        if(CollectionUtils.isNotEmpty(dictionaryList)){
            for(Dictionary item : dictionaryList){
                map.put(item.getValue(),item.getName());
            }
        }
        return map;
    }
    /**
     * 分页查看当前的数据信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean<Dictionary> dicFindPageBean(Integer pageNo,Integer pageSize,String search){
        DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Dictionary.class);
        detachedCriteria.addOrder(Order.asc("classfiyType"));
        detachedCriteria.addOrder(Order.desc("fatherState"));
        detachedCriteria.addOrder(Order.desc("fixed"));
        detachedCriteria.addOrder(Order.asc("value"));
        if(StringUtils.isNotEmpty(search)){
            detachedCriteria.add(Restrictions.disjunction()
                    .add(Restrictions.like("name", "%" + search + "%"))
                    .add(Restrictions.like("classfiyType", "%" + search + "%")));
        }
        return  super.findPageByCriteria(detachedCriteria,pageNo,pageSize);
    }

    /**
     * 查找所有的数据信息
     * @return
     */
    public List<Dictionary> dicFindAll(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dictionary.class);
        detachedCriteria.addOrder(Order.asc("classfiyType"));
        detachedCriteria.addOrder(Order.desc("fatherState"));
        detachedCriteria.addOrder(Order.desc("fixed"));
        detachedCriteria.addOrder(Order.asc("value"));
        return  super.findAllByCriteria(detachedCriteria);
    }
}
