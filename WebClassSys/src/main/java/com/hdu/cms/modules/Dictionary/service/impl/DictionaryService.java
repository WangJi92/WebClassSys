package com.hdu.cms.modules.Dictionary.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.DicSelectBeanUtils;
import com.hdu.cms.common.Utils.SelectBean;
import com.hdu.cms.modules.Dictionary.dao.DictionaryDao;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;
import com.hdu.cms.modules.Dictionary.service.IDictionary;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by JetWang on 2016/10/10.
 * 数据字典工具类
 */
@Service(value = "dictionaryService")
public class DictionaryService implements IDictionary, InitializingBean {

    @Resource
    private DictionaryDao dictionaryDao;

    /**
     *使用缓存处理信息，不用每次都去查找数据库信息，这样不好！
     */
    private static Map<DICTIONARY, Map<String, Integer>> mapString = Maps.newHashMap();
    private static Map<DICTIONARY, Map<Integer, String>> mapInteger = Maps.newHashMap();
    private static Map<DICTIONARY, List<SelectBean>> mapSelectBean = Maps.newHashMap();

    @Override
    public void afterPropertiesSet() throws Exception {
        reloadAll();
    }

    private void reloadItem(DICTIONARY dictionary) {
        mapString.put(dictionary, dictionaryDao.dicGetAllMapKeyStringClassfyByType(dictionary.getValue()));
        mapInteger.put(dictionary, dictionaryDao.dicGetAllMapKeyIntegerClassfyByType(dictionary.getValue()));
        mapSelectBean.put(dictionary, getSeletBean(dictionary));
    }

    private void reloadAll() {
        for (DICTIONARY item : DICTIONARY.values()) {
            mapString.put(item, dictionaryDao.dicGetAllMapKeyStringClassfyByType(item.getValue()));
            mapInteger.put(item, dictionaryDao.dicGetAllMapKeyIntegerClassfyByType(item.getValue()));
            mapSelectBean.put(item, getSeletBean(item));
        }
    }

    private List<SelectBean> getSeletBean(DICTIONARY dictionary) {
        List<Dictionary> dictionaryList = dictionaryDao.dicGetAllClassfyByType(dictionary.getValue());
        return DicSelectBeanUtils.createSelectBean(dictionaryList);
    }

    /**
     * 根据类型查找所有的select
     *
     * @param dictionary
     * @return
     */
    @Override
    public List<SelectBean> getDicSelectBaeanByType(DICTIONARY dictionary) {
        return mapSelectBean.get(dictionary);
    }

    @Override
    public Map<String, Integer> getDicMapByTypeKeyString(DICTIONARY dictionary) {
        return mapString.get(dictionary);
    }

    @Override
    public Map<Integer, String> getDicMapByTypeKeyInteger(DICTIONARY dictionary) {
        return mapInteger.get(dictionary);
    }

    /**
     * 添加或者更新结果
     *
     * @param dictionary
     */
    @Override
    public void saveOrUpdateDic(Dictionary dictionary) {
        dictionaryDao.saveOrUpdate(dictionary);
        // 根据当前数据字典的 classfyType 查找到
        DICTIONARY item = DICTIONARY.getDictionary(dictionary.getClassfiyType());
        //修改缓存！
        reloadItem(item);
    }

    /**
     * 通过idｓ删除所有的数据字典
     *
     * @param ids
     */
    @Override
    public void deleteDicByIds(List<Integer> ids) {
        dictionaryDao.dicDeleteByIds(ids);
        reloadAll();
    }

    /**
     * 通过indexcodes 删除所有的数据字典
     *
     * @param indexcodes
     */
    @Override
    public void delteDicByIndexcodes(List<String> indexcodes) {
        dictionaryDao.dicDeleteByIndexCode(indexcodes);
        reloadAll();
    }

    /**
     * 通过indexcode查看信息
     *
     * @param indexcode
     * @return
     */
    @Override
    public Dictionary findByIndexcode(String indexcode) {
        return dictionaryDao.dicFindByIndexcode(indexcode);
    }

    /**
     * 通过Id查看当前的信息
     *
     * @param id
     * @return
     */
    @Override
    public Dictionary findById(Integer id) {
        return dictionaryDao.dicFindById(id);
    }

    /**
     * 查看当前的分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageBean findPageBean(Integer pageNo, Integer pageSize) {
        return dictionaryDao.dicFindPageBean(pageNo, pageSize);
    }

}
