package com.hdu.cms.common.ConstantParam;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by JetWang on 2016/11/15.
 */
public enum MAINTAINSTATE {
    MATAIN_APPLY(1,"申请中"),
    MAIN_PROCESS(2,"处理中"),
    MAINTAIN_SUCESS(3,"处理完成");

    private Integer index;
    private String value;
    public static Map<Integer,String> MAP = Maps.newHashMap();
    static {
        for(MAINTAINSTATE maintainstate :MAINTAINSTATE.values()){
            MAP.put(maintainstate.getIndex(),maintainstate.getValue());
        }
    }

    MAINTAINSTATE(Integer index, String value) {
        this.index = index;
        this.value = value;
    }
    public static MAINTAINSTATE getStateValue(Integer value){
        for(MAINTAINSTATE maintainstate :MAINTAINSTATE.values()){
            if(maintainstate.getIndex().equals(value)){
                return maintainstate;
            }
        }
        return  MAINTAINSTATE.MATAIN_APPLY;
    }

    public Integer getIndex() {
        return index;
    }


    public String getValue() {
        return value;
    }

}
