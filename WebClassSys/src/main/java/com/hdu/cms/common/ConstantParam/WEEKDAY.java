package com.hdu.cms.common.ConstantParam;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by JetWang on 2016/11/5.
 */
public enum WEEKDAY {
    WEEKDAYONE(1,"星期一"),
    WEEKDAYTWO(2,"星期二"),
    WEEKDAYTHREE(3,"星期三"),
    WEEKDAYFOUR(4,"星期四"),
    WEEKDAYFIVE(5,"星期五"),
    WEEKDAYSIX(6,"星期六"),
    WEEKDAYSEVEN(7,"星期天")
    ;
    private  Integer key;
    private String  value;
    public static WEEKDAY getString(Integer value){
        if(value !=null) {
            for (WEEKDAY item : WEEKDAY.values()) {
                if (item.getKey() == value) {
                    return item;
                }
            }
        }
        //最笨的方法
        return WEEKDAY.WEEKDAYONE;
    }
    WEEKDAY(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
