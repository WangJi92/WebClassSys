package com.hdu.cms.common.ConstantParam;

/**
 * Created by JetWang on 2016/10/17.
 */
public enum UNIQUETYPE {

    NUMBER(0),
    LOWERALPHABET(1),
    BIGALPHABET(2),
    NUMBERANDALP(3),
    OTHER(4);
    private int value;

    UNIQUETYPE(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
