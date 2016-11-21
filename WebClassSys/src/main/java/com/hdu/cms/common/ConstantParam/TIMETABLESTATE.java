package com.hdu.cms.common.ConstantParam;

/**
 * Created by JetWang on 2016/11/5.
 * 教室的占用状态
 */
public enum TIMETABLESTATE {
    FREE(1,"空闲"),
    APPLYING(2,"申请中"),
    OCUPPLY(4,"占用"),
    REVIEW(8,"自习"),
    HAVECLASS(16,"上课");

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
