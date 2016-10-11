package com.hdu.cms.modules.Dictionary.dao;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hdu.cms.common.ConstantParam.STATE;
import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ArrayUtils;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by JetWang on 2016/10/10.
 */
@Repository(value="dictionaryDao")
public class DictionaryDao  extends HibernateEntityDao<Dictionary>{

    /**
     * 保存或者更新数据字典
     * @param dictionary
     */
    public void dicSaveOrUpdate(Dictionary dictionary){
        super.saveOrUpdate(dictionary);
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
        super.executeUpdateSQL("delete  from Dictionary  where indexCode ='"+indexcode+"'");
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
    public PageBean<Dictionary> dicFindPageBean(Integer pageNo,Integer pageSize){
        DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Dictionary.class);
        detachedCriteria.addOrder(Order.asc("id"));
        return  super.findPageByCriteria(detachedCriteria,pageNo,pageSize);
    }
}
