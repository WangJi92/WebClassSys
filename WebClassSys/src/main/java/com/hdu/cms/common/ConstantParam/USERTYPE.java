package com.hdu.cms.common.ConstantParam;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by JetWang on 2016/11/20.
 */
public enum USERTYPE {
    ADM(1,"管理员"),
    STU(2,"学生"),
    TEATHER(4,"老师"),
    DUTY(8,"维修值班人员"),
    OTHER(16,"其他");
    public static Map<Integer,String> mapIndexValue = Maps.newHashMap();
    static {
        for(USERTYPE item : USERTYPE.values()){
            mapIndexValue.put(item.getIndex(),item.getValue());
        }
    }
    USERTYPE(Integer index, String value) {
        this.index = index;
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private Integer index;
    private String value;

}
