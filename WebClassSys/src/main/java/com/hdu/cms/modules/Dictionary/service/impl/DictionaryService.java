package com.hdu.cms.modules.Dictionary.service.impl;

import com.google.common.collect.Lists;
import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.DicSelectBeanUtils;
import com.hdu.cms.common.Utils.SelectBean;
import com.hdu.cms.modules.Dictionary.dao.DictionaryDao;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;
import com.hdu.cms.modules.Dictionary.service.IDictionary;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JetWang on 2016/10/10.
 * 数据字典工具类
 */
@Service(value = "dictionaryService")
public class DictionaryService implements IDictionary {

    @Resource
    private DictionaryDao dictionaryDao;


    /**
     * 根据类型查找所有的select
     * @param dictionary
     * @return
     */
    @Override
    public List<SelectBean> getDicSelectBaeanByType(DICTIONARY dictionary) {
        List<Dictionary> dictionaryList = dictionaryDao.dicGetAllClassfyByType(dictionary.getValue());
        return DicSelectBeanUtils.createSelectBean(dictionaryList);
    }

    /**
     * 添加或者更新结果
     * @param dictionary
     */
    @Override
    public void saveOrUpdateDic(Dictionary dictionary) {
         dictionaryDao.saveOrUpdate(dictionary);
    }

    /**
     * 通过idｓ删除所有的数据字典
     * @param ids
     */
    @Override
    public void deleteDicByIds(List<Integer> ids) {
      dictionaryDao.dicDeleteByIds(ids);
    }

    /**
     * 通过indexcodes 删除所有的数据字典
     * @param indexcodes
     */
    @Override
    public void delteDicByIndexcodes(List<String> indexcodes) {
       dictionaryDao.dicDeleteByIndexCode(indexcodes);
    }

    /**
     * 通过indexcode查看信息
     * @param indexcode
     * @return
     */
    @Override
    public Dictionary findByIndexcode(String indexcode) {
        return dictionaryDao.dicFindByIndexcode(indexcode);
    }

    /**
     * 通过Id查看当前的信息
     * @param id
     * @return
     */
    @Override
    public Dictionary findById(Integer id) {
        return dictionaryDao.dicFindById(id);
    }

    /**
     * 查看当前的分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageBean findPageBean(Integer pageNo, Integer pageSize) {
        return dictionaryDao.dicFindPageBean(pageNo,pageSize);
    }
}
