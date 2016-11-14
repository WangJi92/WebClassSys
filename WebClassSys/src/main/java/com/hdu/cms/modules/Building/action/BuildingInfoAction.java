package com.hdu.cms.modules.Building.action;

import com.google.common.collect.Lists;
import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.UNIQUETYPE;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.common.Utils.LogUtils;
import com.hdu.cms.common.Utils.RandomUtil;
import com.hdu.cms.modules.Building.dto.BuildingInfoDto;
import com.hdu.cms.modules.Building.entity.BuidingInfo;
import com.hdu.cms.modules.Building.service.IBuildingInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by JetWang on 2016/10/12.
 */
@Controller(value ="buildingInfoAction")
@RequestMapping("buildingInfoAction")
public class BuildingInfoAction extends BaseAction {

    @Resource
    private IBuildingInfoService iBuildingInfoService;

    /**
     * 通过Id来进行删除来着
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteByIds")
    @ResponseBody
    public ActionResult deleteBuildingByIds(List<Integer> ids){
        ActionResult result = new ActionResult(true);
        if(CollectionUtils.isNotEmpty(ids)){
            //删除教室
            try {
                iBuildingInfoService.deleteByIds(ids);
            } catch (Exception e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMessage("系统异常");
            }
        }else{
            result.setSuccess(false);
            result.setMessage("id不能为空");
        }
        return result;
    }
    @RequestMapping(value = "deleteByIndexCodes")
    @ResponseBody
    public ActionResult deleteBuildingByIndexCodes(List<String> indexCodes){
        ActionResult result = new ActionResult(true);
        if(CollectionUtils.isNotEmpty(indexCodes)){
            //删除教室
            try {
                iBuildingInfoService.deleteByIndexCodes(indexCodes);
            } catch (Exception e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMessage("系统异常");
            }
        }else{
            result.setSuccess(false);
            result.setMessage("indexCodes不能为空");
        }
        return result;
    }
    @RequestMapping(value = "deleteByIndexCode")
    @ResponseBody
    public ActionResult deleteBuildingByIndexCode(String buildingIndexcode){
        ActionResult result = new ActionResult(true);
        if(StringUtils.isNotEmpty(buildingIndexcode)){
            //删除教室
            try {
                iBuildingInfoService.deleteByIndexCode(buildingIndexcode);
            } catch (Exception e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMessage("系统异常");
            }
        }else{
            result.setSuccess(false);
            result.setMessage("indexCodes不能为空");
        }
        return result;
    }
    @RequestMapping(value = "findById")
     @ResponseBody
     public ActionResult findById(Integer id){
        ActionResult result = new ActionResult(true);
        if(id !=null){
            try {
                BuildingInfoDto dto = iBuildingInfoService.getBuildingInfoById(id);
                if(dto !=null){
                    result.setData(dto);
                }else{
                    result.setSuccess(false);
                    result.setMessage("信息不存在");
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMessage("系统异常");
            }

        }
        return result;
    }
    @RequestMapping(value = "findByIndexCode")
    @ResponseBody
    public ActionResult findByIndexCode(String  indexCode){
        ActionResult result = new ActionResult(true);
        if(StringUtils.isNotEmpty(indexCode)){
            try {
                BuildingInfoDto dto = iBuildingInfoService.getBuildingInfoByIndexCode(indexCode);
                if(dto !=null){
                    result.setData(dto);
                }else{
                    result.setSuccess(false);
                    result.setMessage("信息不存在");
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMessage("系统异常");
            }
        }
        return result;
    }
    @RequestMapping(value = "findAll")
      @ResponseBody
      public ActionResult findAll(){
        ActionResult result = new ActionResult(true);
        try {
            List<BuildingInfoDto> list = iBuildingInfoService.getAllInfo();
            if(CollectionUtils.isNotEmpty(list)){
                result.setData(list);
            }else{
                result.setMessage("没有楼宇信息记录");
                result.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统异常");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "findPage")
    @ResponseBody
    public ActionResult findPage(Integer pageSize,Integer pageNo,String search){
        ActionResult result = new ActionResult(true);
        try {
            if(pageNo ==null || pageSize ==null){
                pageNo =ConstantParam.DEFAULT_PAGE_NO;
                pageSize =ConstantParam.DEFAULT_PAGE_SIZE;
            }
            PageBean pageBean = iBuildingInfoService.findPageInfo(pageNo,pageSize,search);
            result.setData(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统异常");
            result.setSuccess(false);
        }
        return result;
    }
    @RequestMapping(value = "saveOrUpdate")
    @ResponseBody
    public ActionResult addOrSavaBuilding(BuidingInfo dto){
        ActionResult result = new ActionResult(true);
        if(dto!=null){
            try {
                if(dto.getId() == null) {//新增
                    BuidingInfo newName = iBuildingInfoService.getBuildInfoByName(dto.getName());
                    if(newName !=null){//存在相同的
                        result.setSuccess(false);
                        result.setMessage("楼宇名称重复");
                        return  result;
                    }
                    dto.setIndexCode(RandomUtil.getUniqueNubmerString(32, UNIQUETYPE.LOWERALPHABET));
                    iBuildingInfoService.saveOrUpdate(dto);
                }else{
                    BuidingInfo old = iBuildingInfoService.getBuildingInfoByIdEntity(dto.getId());
                    if(!old.getName().equals(dto.getName())) {
                      BuidingInfo newName = iBuildingInfoService.getBuildInfoByName(dto.getName());
                        if(newName !=null){//存在相同的
                            result.setSuccess(false);
                            result.setMessage("楼宇名称重复");
                            return  result;
                        }
                    }
                    dto.setIndexCode(old.getIndexCode());
                    BeanUtils.copyProperties(dto,old);
                    iBuildingInfoService.saveOrUpdate(old);
                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMessage("系统异常");
            }
        }
        return  result;
    }
    @RequestMapping(value = "upLoad")
    @ResponseBody
    public ActionResult upLoadFile(List<MultipartFile> file){
        ActionResult result = new ActionResult(true);
        List<String> picturs = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(file)){
            for(MultipartFile item :file){
                String filePath = getServletContext().getContextPath()+ ConstantParam.DEFAULT_IMAGE_PATH+File.separator+RandomUtil.getUUID32BIT()+ConstantParam.DEFAULT_IMAGE_TYPE;
                LogUtils.logInfo("file path :{}",filePath);
                picturs.add(filePath);
                File fileItem = new File(filePath);
                try {
                    item.transferTo(fileItem);
                } catch (IOException e) {
                    e.printStackTrace();
                    LogUtils.logException(e);
                    result.setSuccess(false);
                    result.setMessage("上传图片异常");
                    return result;
                }
            }
        }
        result.setData(picturs);
        return  result;
    }











}
