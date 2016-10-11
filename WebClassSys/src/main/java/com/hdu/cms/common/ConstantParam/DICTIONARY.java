package com.hdu.cms.common.ConstantParam;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by JetWang on 2016/10/10.
 * 数据字典中查找信息哦
 */
public enum DICTIONARY {
    USERINFO("DIC_USER"),//用户分类
    CLASSROOM("DIC_CLASSROOM"),//教室分类
    CLASSSTATE("DIC_CLASSSTATE"),//教室状态分类
    REPAIRCLASSFIY("DIC_REPAIRCLASSFIY"),//维修类别
    URGENCYREPAIR("DIC_URGENCYREPAIR"),//紧急报修，不同报修
    BUIDING("DIC_BUDING"),//楼宇
    EQUIPMENT("DIC_EQUIPMENT")//设备信息
    ;
    DICTIONARY(String value) {
        this.value = value;
    }
    public static DICTIONARY getDictionary(String name){
        if(StringUtils.isNotEmpty(name)){
            for(DICTIONARY item :DICTIONARY.values()){
                if(item.getValue().equals(name)){
                    return item;
                }
            }
        }
        //最笨的方法
        return DICTIONARY.USERINFO;
    }
    private String value;
    public String getValue(){
        return this.value;
    }

}
