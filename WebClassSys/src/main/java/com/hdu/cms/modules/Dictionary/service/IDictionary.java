package com.hdu.cms.modules.Dictionary.service;

import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.SelectBean;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;

import java.util.List;

/**
 * Created by JetWang on 2016/10/10.
 */
public interface IDictionary {
    /**
     *
     * @param dictionary
     * @return
     */
    public List<SelectBean> getDicSelectBaeanByType(DICTIONARY dictionary);

    /**
     *
     * @param dictionary
     */
    public void saveOrUpdateDic(Dictionary dictionary);

    /**
     *
     * @param ids
     */
    public void deleteDicByIds(List<Integer> ids);

    /**
     *
     * @param indexcodes
     */
    public void delteDicByIndexcodes(List<String> indexcodes);

    /**
     *
     * @param indexcode
     * @return
     */
    public Dictionary findByIndexcode(String indexcode);

    /**
     *
     * @param id
     * @return
     */
    public Dictionary findById(Integer id);

    /**
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean findPageBean(Integer pageNo,Integer pageSize);





}
