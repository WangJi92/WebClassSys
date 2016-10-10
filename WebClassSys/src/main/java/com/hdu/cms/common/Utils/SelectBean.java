package com.hdu.cms.common.Utils;

import com.google.common.collect.Lists;
import com.hdu.cms.modules.Dictionary.entity.Dictionary;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JetWang on 2016/10/10.
 * 下拉选择的工具类信息
 */
public class SelectBean implements Serializable {

    private String key;
    private Integer value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
