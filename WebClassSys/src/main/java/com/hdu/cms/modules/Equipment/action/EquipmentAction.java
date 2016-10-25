package com.hdu.cms.modules.Equipment.action;

import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.common.Utils.RandomUtil;
import com.hdu.cms.modules.Equipment.entity.Equipment;
import com.hdu.cms.modules.Equipment.service.IEquipmentService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JetWang on 2016/10/24.
 */
@Controller(value = "equipmentAction")
@RequestMapping("/equipment")
public class EquipmentAction  extends BaseAction {

    @Resource
    private IEquipmentService iEquipmentService;

    @RequestMapping("/findPage")
    @ResponseBody
    public ActionResult  getPageInfo(Integer pageNo,Integer pageSize,String search){
        ActionResult result = new ActionResult(true);
        try {
            if(pageNo == null || pageSize == null){
                pageNo = ConstantParam.DEFAULT_PAGE_NO;
                pageSize = ConstantParam.DEFAULT_PAGE_SIZE;
            }
            PageBean  pageBean = iEquipmentService.findPageInfo(pageSize,pageNo, search);
            result.setData(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("加载异常");
            result.setData(new PageBean());
        }
        return  result;
    }


    /**
     * 删除设备信息
     * @param ids
     * @return
     */
    @RequestMapping("/deleteByIds")
    @ResponseBody
    public ActionResult deleteByIds(@RequestParam("ids[]")List<Integer> ids){
        ActionResult result = new ActionResult(true);
        try {
            if(CollectionUtils.isNotEmpty(ids)){
                iEquipmentService.deleteByIds(ids);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("删除异常");
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 保存和更新 设备信息
      * @param equipment
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ActionResult  saveOrUpdate(Equipment equipment){
        ActionResult result = new ActionResult(true);
        if(equipment !=null){
            try {
                if(equipment.getId() != null){
                    Equipment equipmentOld = iEquipmentService.getEquipmentInfoById(equipment.getId());
                    BeanUtils.copyProperties(equipment,equipmentOld);
                    iEquipmentService.saveOrUpdate(equipmentOld);
                }else{
                    equipment.setIndexCode(RandomUtil.getUUID32BIT());
                    iEquipmentService.saveOrUpdate(equipment);
                }
            } catch (BeansException e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMessage("系统错误");
            }
        }
        return result;
    }

    /**
     * 导出excel 信息
     */
    @RequestMapping("/exportEquipment")
    @ResponseBody
    public  void  exportExport(){
        try {
            iEquipmentService.exportEquipment();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/equipmentView")
    public ModelAndView getEquipmentAddEditView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/equipmentmanage/dialog/add/equipment_add");
        return  modelAndView;
    }
}
