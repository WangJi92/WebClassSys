package com.hdu.cms.common.ConstantParam;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by JetWang on 2016/10/12.
 * 申请教室的状态
 */
public enum APPLYSTATE {
    PROCESSING(0,"申请中"),
    APPLYSUCESS(1,"申请成功"),
    APPLYERROR(2,"申请失败"),
    CANCEL(4,"取消申请")
    ;

    public static Map<Integer,String> MAP = Maps.newHashMap();
    static {
        for(APPLYSTATE item : APPLYSTATE.values()) {
            MAP.put(item.getIndex(),item.getValue());
        }
    }

    APPLYSTATE(Integer index,String value) {
        this.index=index;
        this.value=value;
    }
    public static APPLYSTATE getIndexValue(int index){
        for(APPLYSTATE item : APPLYSTATE.values()){
            if(item.getIndex().equals(index)){
                return  item;
            }
        }
        return APPLYSTATE.APPLYERROR;
    }
    public static  APPLYSTATE getValueOfIndex(String value){
        for(APPLYSTATE item : APPLYSTATE.values()){
            if(item.getValue().equals(value)){
                return  item;
            }
        }
        return PROCESSING;
    }
    private Integer index;
    private String value;

    public Integer getIndex() {
        return index;
    }


    public String getValue() {
        return value;
    }

}
