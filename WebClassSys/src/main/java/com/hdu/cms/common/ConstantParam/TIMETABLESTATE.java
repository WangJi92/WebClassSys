package com.hdu.cms.common.ConstantParam;

/**
 * Created by JetWang on 2016/11/5.
 * 教室的占用状态
 */
public enum TIMETABLESTATE {
    FREE(1,"空闲"),
    REVIEW(2,"自习室"),
    APPLYING(4,"申请中"),
    OCUPPLY(8,"占用");

    TIMETABLESTATE(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
    public static TIMETABLESTATE getString(Integer value){
        if(value !=null) {
            for (TIMETABLESTATE item : TIMETABLESTATE.values()) {
                if (item.getKey() == value) {
                    return item;
                }
            }
        }
        //最笨的方法
        return TIMETABLESTATE.FREE;
    }

    private Integer key;
    private String value;

    public String getValue() {
        return value;
    }

    public Integer getKey() {
        return key;
    }


}
