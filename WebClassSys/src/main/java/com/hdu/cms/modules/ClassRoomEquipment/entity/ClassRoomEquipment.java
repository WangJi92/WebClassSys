package com.hdu.cms.modules.ClassRoomEquipment.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by JetWang on 2016/10/12.
 * 教室中的设备的中间表
 */
@Entity
public class ClassRoomEquipment  implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private  Integer id;

    @Column(name="ce_crindexcode",length = 64)
    private String classRoomIndexCode;

    @Column(name="ce_eqindexcode",length = 64)
    private String equipmentIndexCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
