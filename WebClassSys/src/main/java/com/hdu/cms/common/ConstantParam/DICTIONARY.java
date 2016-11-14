package com.hdu.cms.common.ConstantParam;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by JetWang on 2016/10/10.
 * 数据字典中查找信息哦
 */
public enum DICTIONARY {
    USERINFO("DIC_USER",1,"用户分类"),//用户分类
    CLASSROOM("DIC_CLASSROOM",2,"教室分类"),//
    CLASSSTATE("DIC_CLASSSTATE",4,"教室状态分类"),//
    REPAIRCLASSFIY("DIC_REPAIRCLASSFIY",8,"维修类别"),//
    URGENCYREPAIR("DIC_URGENCYREPAIR",16,"紧急程度"),//，不同报修
    BUIDING("DIC_BUDING",32,"楼宇类型"),//楼宇
    EQUIPMENT("DIC_EQUIPMENT",64,"设备信息")
    ;
    DICTIONARY(String value,Integer key,String name) {
        this.value = value;// type
        this.key=key;//key
        this.name=name;// name
    }
    public static DICTIONARY getDictionary(String value){
        if(StringUtils.isNotEmpty(value)){
            for(DICTIONARY item :DICTIONARY.values()){
                if(item.getValue().equals(value)){
                    return item;
                }
            }
        }
        //最笨的方法
        return DICTIONARY.USERINFO;
    }
    public static DICTIONARY getDictionaryByKey(Integer key){
        if(key != null){
            for(DICTIONARY item :DICTIONARY.values()){
                if(item.getKey() == key){
                    return item;
                }
            }
        }
        //最笨的方法
        return DICTIONARY.USERINFO;
    }
    public static DICTIONARY getDictionaryByName(String  name){
        if(StringUtils.isNotEmpty(name)){
            for(DICTIONARY item :DICTIONARY.values()){
                if(item.getKey().equals(name)){
                    return item;
                }
            }
        }
        //最笨的方法
        return DICTIONARY.USERINFO;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String name;
    private Integer key;
    private String value;
    public String getValue(){
        return this.value;
    }

}
