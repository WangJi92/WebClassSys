package com.hdu.cms.modules.Dictionary.service;

import com.hdu.cms.common.ConstantParam.DICTIONARY;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.SelectBean;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;

import java.util.List;
import java.util.Map;

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

    public List<SelectBean> getDicFatherSelectBean();

    /**
     * 得到当前Dic类型下的Map的值
     * @param dictionary
     * @return
     */
    public Map<String,Integer> getDicMapByTypeKeyString(DICTIONARY dictionary);

    /**
     * 得到当前Dic类型下的Map的值 反转
     * @param dictionary
     * @return
     */
    public Map<Integer,String> getDicMapByTypeKeyInteger(DICTIONARY dictionary);
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
    public void deleteDicByIndexcodes(List<String> indexcodes);

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
    public PageBean findPageBean(Integer pageNo,Integer pageSize,String serarch);


    public List<Dictionary> findAllDic();

    /**
     * 导出为excel
     */
    public void exprotDic();


}
