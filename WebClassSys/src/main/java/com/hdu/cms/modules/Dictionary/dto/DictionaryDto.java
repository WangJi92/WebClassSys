package com.hdu.cms.modules.Dictionary.dto;

/**
 * Created by JetWang on 2016/11/19.
 */
public class DictionaryDto {

    private  Integer id;

    /**
     * 唯一标识符
     */
    private String indexCode;
    /**
     * 分类的类型
     */
    private String classfiyType;//DIC_XXX
    private String classfiyTypeName;//教室分类
    private  Integer classfiyTypeIndexValue;// 1

    /**
     *数据字典的名字
     */
    private String name;

    /**
     * 数据字典的值
     */
    private Integer value;

    /**
     * 是否为不能改变的值
     */
    private Integer fixed;

    /**
     * 是否为父节点
     */
    private Integer fatherState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getClassfiyType() {
        return classfiyType;
    }

    public void setClassfiyType(String classfiyType) {
        this.classfiyType = classfiyType;
    }

    public String getClassfiyTypeName() {
        return classfiyTypeName;
    }

    public void setClassfiyTypeName(String classfiyTypeName) {
        this.classfiyTypeName = classfiyTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getFixed() {
        return fixed;
    }

    public void setFixed(Integer fixed) {
        this.fixed = fixed;
    }

    public Integer getFatherState() {
        return fatherState;
    }

    public void setFatherState(Integer fatherState) {
        this.fatherState = fatherState;
    }

    public Integer getClassfiyTypeIndexValue() {
        return classfiyTypeIndexValue;
    }

    public void setClassfiyTypeIndexValue(Integer classfiyTypeIndexValue) {
        this.classfiyTypeIndexValue = classfiyTypeIndexValue;
    }
}
