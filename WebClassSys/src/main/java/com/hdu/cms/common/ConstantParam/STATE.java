package com.hdu.cms.common.ConstantParam;

/**
 * Created by JetWang on 2016/10/10.
 * 表示状态量，是否
 */
public enum STATE {
    YES(1),
    NO(0);
    private Integer value;
    STATE(Integer value) {
        this.value=value;
    }
    public Integer getValue(){
        return this.value;
    }
}
