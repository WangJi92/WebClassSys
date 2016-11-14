package com.hdu.cms.modules.ClassRoom.action;

import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.UNIQUETYPE;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.common.Utils.RandomUtil;
import com.hdu.cms.modules.ClassRoom.dto.ClassRoomDto;
import com.hdu.cms.modules.ClassRoom.entity.ClassRoom;
import com.hdu.cms.modules.ClassRoom.service.IClassRoomService;
import com.hdu.cms.modules.TimeTable.service.ITimeTableService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by JetWang on 2016/11/4.
 */
@Controller
@RequestMapping("classRoomAction")
public class ClassRoomAction extends BaseAction {


    @Resource
    private IClassRoomService iClassRoomService;

    @Resource
    private ITimeTableService iTimeTableService;
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


    @RequestMapping(value = "findById")
    @ResponseBody
    public ActionResult findById(Integer id) {
        ActionResult result = new ActionResult(true);
        if (id != null) {
            try {
                ClassRoomDto dto = iClassRoomService.getClassRoomInfoById(id);
                if (dto != null) {
                    result.setData(dto);
                } else {
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
    public ActionResult findByIndexCode(String indexCode) {
        ActionResult result = new ActionResult(true);
        if (StringUtils.isNotEmpty(indexCode)) {
            try {
                ClassRoomDto dto = iClassRoomService.getClassRoomInfoByIndexCode(indexCode);
                if (dto != null) {
                    result.setData(dto);
                } else {
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
    public ActionResult findAll() {
        ActionResult result = new ActionResult(true);
        try {
            List<ClassRoomDto> list = iClassRoomService.getAllInfo();
            if (CollectionUtils.isNotEmpty(list)) {
                result.setData(list);
            } else {
                result.setMessage("教室的信息记录");
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
    public ActionResult findPage(Integer pageSize, Integer pageNo, String name, String buildingIndexCode) {
        ActionResult result = new ActionResult(true);
        try {
            if (pageNo == null || pageSize == null) {
                pageNo = ConstantParam.DEFAULT_PAGE_NO;
                pageSize = ConstantParam.DEFAULT_PAGE_SIZE;
            }
            PageBean pageBean = iClassRoomService.findPageInfo(pageSize, pageNo, name, buildingIndexCode);
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
    public ActionResult addOrSavaClassRoom(ClassRoom info) {
        ActionResult result = new ActionResult(true);
        if (info != null) {
            try {
                if (info.getId() == null) {//新增
                    ClassRoom newName = iClassRoomService.findBuyName(info.getName());
                    if (newName != null) {//存在相同的
                        result.setSuccess(false);
                        result.setMessage("教室名称重复");
                        return result;
                    }
                    info.setIndexCode(RandomUtil.getUniqueNubmerString(32, UNIQUETYPE.LOWERALPHABET));
                    iTimeTableService.saveinitTimeTable(info.getIndexCode());//初始化课程表
                    iClassRoomService.saveOrUpdate(info);
                } else {
                    ClassRoom old = iClassRoomService.findBuyIdEntity(info.getId());
                    if (!old.getName().equals(info.getName())) {
                        ClassRoom newName = iClassRoomService.findBuyName(info.getName());
                        if (newName != null) {//存在相同的
                            result.setSuccess(false);
                            result.setMessage("教室名称重复");
                            return result;
                        }
                    }
                    info.setIndexCode(old.getIndexCode());
                    BeanUtils.copyProperties(info, old);
                    iClassRoomService.saveOrUpdate(old);
                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMessage("系统异常");
            }
        }
        return result;
    }

    @RequestMapping("/wheatherEx")
    @ResponseBody
    public ActionResult getBuildingClassRoomCount(String buildingIndexcode) {
        ActionResult result = new ActionResult(true);
        try {
            boolean isEx = iClassRoomService.BuidlingCountOfClasRoom(buildingIndexcode);
            /**
             * 存在 true ,否则 false
             */
            if (isEx == true) {
                result.setMessage("存在教室不能删除");
                result.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统错误");
            result.setSuccess(false);
        }
        return result;

    }
    @RequestMapping("/deleteByClassRoomIndexcode")
    @ResponseBody
    public ActionResult  deleteByClassRoomIndexcode(String classRoomIndexcode){
        ActionResult result = new ActionResult(true);
        try {
            iClassRoomService.deleteByIndexcode(classRoomIndexcode);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("删除失败");
        }
        return  result;
    }
}
