package com.hdu.cms.common.Utils;

import com.google.common.collect.Lists;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by JetWang on 2016/10/10.
 */
public class DicSelectBeanUtils {

    /**
     * list转为select bean
     * @param dictionaryList
     * @return
     */
    public static List<SelectBean> createSelectBean(List<Dictionary> dictionaryList){
        List list = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(dictionaryList)){
            for(Dictionary Item : dictionaryList){
                SelectBean bean = new SelectBean();
                bean.setKey(Item.getName());
                bean.setValue(Item.getValue());
                list.add(bean);
            }
        }
        return list;
    }
}
