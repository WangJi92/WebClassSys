package com.hdu.cms.common.ConstantParam;

/**
 * Created by JetWang on 2016/11/22.
 */
public enum UPDATEWEEKTYPE {
    SIGLEWEEK(1,"单周"),
    DOUBLEWEEK(2,"双周"),
    ALLWEEK(4,"所有的");


    private Integer index;
    private String value;

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

    UPDATEWEEKTYPE(Integer index, String value) {
        this.index = index;
        this.value = value;
    }
}
