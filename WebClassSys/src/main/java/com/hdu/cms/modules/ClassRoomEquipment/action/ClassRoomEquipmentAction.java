package com.hdu.cms.modules.ClassRoomEquipment.action;

import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.modules.ClassRoomEquipment.dto.ClassRoomEquipmentDto;
import com.hdu.cms.modules.ClassRoomEquipment.entity.ClassRoomEquipment;
import com.hdu.cms.modules.ClassRoomEquipment.service.IClassRoomEquipmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JetWang on 2016/11/9.
 */
@Controller
@RequestMapping("/classroomequipment")
public class ClassRoomEquipmentAction extends BaseAction {

    @Resource
    private IClassRoomEquipmentService iClassRoomEquipmentService;

    @ResponseBody
    @RequestMapping("/saveOrUpdate")
    public ActionResult saveOrUpdate(ClassRoomEquipment classRoomEquipment) {
        ActionResult result = new ActionResult(true);
        try {
            if (classRoomEquipment != null
                    && StringUtils.isNotEmpty(classRoomEquipment.getClassRoomIndexCode())
                    && StringUtils.isNotEmpty(classRoomEquipment.getEquipmentIndexCode())) {
                   iClassRoomEquipmentService.saveOrUpdate(classRoomEquipment);
            } else {
                result.setMessage("数据信息错误");
                result.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统异常");
        }
        return result;
    }

    @RequestMapping("/findAllByClassRoomIndexcode")
    @ResponseBody
    public ActionResult findAllByClassRoomIndexCode(String classroomIndex) {
        ActionResult result = new ActionResult(true);
        try {
            if(StringUtils.isNotEmpty(classroomIndex)){
                List<ClassRoomEquipmentDto> classRoomEquipmentDtoList = iClassRoomEquipmentService.findAllByClassRoomIndexCode(classroomIndex);
                result.setData(classRoomEquipmentDtoList);
            }else{
                result.setSuccess(false);
                result.setMessage("参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统错误");
        }
        return result;
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public ActionResult deleteById(Integer id) {
        ActionResult result = new ActionResult(true);
        try {
            iClassRoomEquipmentService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统异常");
        }
        return result;
    }
}
