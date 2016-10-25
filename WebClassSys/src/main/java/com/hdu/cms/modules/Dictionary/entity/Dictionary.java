package com.hdu.cms.modules.Dictionary.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by JetWang on 2016/10/10.
 */
@Entity
public class Dictionary implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private  Integer id;

    /**
     * 唯一标识符
     */
    @Column(name ="dic_indexcode",length = 64)
    private String indexCode;
    /**
     * 分类的类型
     */
    @Column(name="dic_classfiytype",length = 64)
    private String classfiyType;

    /**
     *数据字典的名字
     */
    @Column(name="dic_name",length = 64)
    private String name;

    /**
     * 数据字典的值
     */
    @Column(name="dic_value",length = 32)
    private Integer value;

    /**
     * 是否为不能改变的值
     */
    @Column(name = "dic_fixed",length = 2)
    private Integer fixed;

    /**
     * 是否为父节点
     */
    @Column(name = "dic_fatherstate",length = 2)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dictionary that = (Dictionary) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (indexCode != null ? !indexCode.equals(that.indexCode) : that.indexCode != null) return false;
        if (classfiyType != null ? !classfiyType.equals(that.classfiyType) : that.classfiyType != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (fixed != null ? !fixed.equals(that.fixed) : that.fixed != null) return false;
        return !(fatherState != null ? !fatherState.equals(that.fatherState) : that.fatherState != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (indexCode != null ? indexCode.hashCode() : 0);
        result = 31 * result + (classfiyType != null ? classfiyType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (fixed != null ? fixed.hashCode() : 0);
        result = 31 * result + (fatherState != null ? fatherState.hashCode() : 0);
        return result;
    }
}
