package com.hdu.cms.common.ConstantParam;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by JetWang on 2016/10/10.
 * 数据字典中查找信息哦
 */
public enum DICTIONARY {
    USERINFO("DIC_USER", 1, "用户类别"),//用户分类
    CLASSROOM("DIC_CLASSROOM", 2, "教室分类"),//
    CLASSSTATE("DIC_CLASSSTATE", 4, "教室状态分类"),//
    REPAIRCLASSFIY("DIC_REPAIRCLASSFIY", 8, "维修类别"),//
    URGENCYREPAIR("DIC_URGENCYREPAIR", 16, "紧急程度"),//，不同报修
    CLASSUSEPOPURSE("DIC_CLASSUSEPOPURSE", 32, "申请教室用途"),//楼宇
    EQUIPMENT("DIC_EQUIPMENT", 64, "设备信息");
    public static Map<String, String> MapTypeToName = Maps.newHashMap();//DIC__    w教室分类
    public static Map<Integer, String> MapIndexToType = Maps.newHashMap();// DC__  2
    public static Map<String, Integer> MapTypeToIndex = Maps.newHashMap();
    public static Map<String, String> MapNameToType = Maps.newHashMap();// 教室 DIC_
    public static Map<String, DICTIONARY> MapTypeToDictionary = Maps.newHashMap();//  DIC_  DICTIONARY

    static {
        for (DICTIONARY Item : DICTIONARY.values()) {
            MapTypeToName.put(Item.getValue(), Item.getName());
            MapIndexToType.put(Item.getKey(), Item.getValue());
            MapTypeToIndex.put(Item.getValue(), Item.getKey());
            MapNameToType.put(Item.getName(), Item.getValue());
            MapTypeToDictionary.put(Item.getValue(), Item);
        }
    }

    DICTIONARY(String value, Integer key, String name) {
        this.value = value;// type
        this.key = key;//key
        this.name = name;// name
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

    public String getValue() {
        return this.value;
    }

}
