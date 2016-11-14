package com.hdu.cms.modules.ClassRoomEquipment.dto;

/**
 * Created by JetWang on 2016/11/9.
 */
public class ClassRoomEquipmentDto {

    private String classRoomIndexCode;
    private String equipmentIndexCode;
    private String typeStr;//equipment的typeStr
    private String name;//equipment的Name
    private String brandName;//equipment的 品牌名称
    private String introduce;//equipment的简介
    private  Integer id;

    public String getClassRoomIndexCode() {
        return classRoomIndexCode;
    }

    public void setClassRoomIndexCode(String classRoomIndexCode) {
        this.classRoomIndexCode = classRoomIndexCode;
    }

    public String getEquipmentIndexCode() {
        return equipmentIndexCode;
    }

    public void setEquipmentIndexCode(String equipmentIndexCode) {
        this.equipmentIndexCode = equipmentIndexCode;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
