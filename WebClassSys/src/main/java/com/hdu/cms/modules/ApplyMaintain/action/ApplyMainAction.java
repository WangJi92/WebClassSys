package com.hdu.cms.modules.ApplyMaintain.action;

import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.MAINTAINSTATE;
import com.hdu.cms.common.ConstantParam.STATE;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.modules.ApplyMaintain.dto.ApplyMaintainDto;
import com.hdu.cms.modules.ApplyMaintain.entity.ApplyMaintain;
import com.hdu.cms.modules.ApplyMaintain.service.IApplyMaintainService;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by JetWang on 2016/11/15.
 */
@Controller
@RequestMapping("/applyMaintainAction")
public class ApplyMainAction extends BaseAction {

    @Resource
    private IApplyMaintainService iApplyMaintainService;

    /**
     * todo 这里没有处理 当前用户的信息！
     * @param applyMaintain
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ActionResult save(ApplyMaintain applyMaintain) {
        ActionResult result = new ActionResult(true);
        try {
            applyMaintain.setState(MAINTAINSTATE.MATAIN_APPLY.getIndex());
            Date date = new Date();
            Timestamp nowTime = new Timestamp(date.getTime());
            applyMaintain.setCreateTime(nowTime);
            UserInfo userInfo = (UserInfo) getServletRequest().getSession().getAttribute(ConstantParam.SESSION_USERNAME);
            applyMaintain.setApplyIndexCode(userInfo!=null?userInfo.getIndexCode():"");
            iApplyMaintainService.save(applyMaintain);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("申请失败");
        }
        return result;
    }

    /**
     *
     * @param pageNo
     * @param pageSize
     * @param type
     * @param state
     * @param uergencyState
     * @param search
     * @param applyPeople  下拉框选择自己 或者其他人 自己为 1
     * @return
     */
    @RequestMapping("/getpage")
    @ResponseBody
    public ActionResult pageBean(Integer pageNo,Integer pageSize,Integer type,Integer state,Integer uergencyState,String search,Integer applyPeople){
        ActionResult result = new ActionResult(true);
        try {
            /**
             * 时候是选择查看本人的提交记录
             */
            String applyIndexCode ="";
            if(applyPeople!=null && applyPeople.equals(STATE.YES.getValue())){
                UserInfo userInfo = (UserInfo) getServletRequest().getSession().getAttribute(ConstantParam.SESSION_USERNAME);
                applyIndexCode= userInfo!=null?userInfo.getIndexCode():"";
            }
            PageBean pageBean = iApplyMaintainService.getPageBean(
                    pageNo,
                    pageSize,
                    type,
                    state,
                    uergencyState,
                    search, search, search, search, search, search,search,applyIndexCode);
            result.setData(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(true);
            result.setData(new PageBean());
        }
        return result;
    }
    @RequestMapping("/findEntityById")
    @ResponseBody
    public ActionResult findById(Integer id){
        ActionResult result = new ActionResult(true);

        try {
            ApplyMaintain applyMaintain = iApplyMaintainService.findByEnetityId(id);
            result.setData(applyMaintain);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统错误");
        }

        return result;
    }
    @RequestMapping("/findDtoById")
    @ResponseBody
    public ActionResult findDtoById(Integer id){
        ActionResult result = new ActionResult(true);

        try {
            ApplyMaintainDto applyMaintainDto = iApplyMaintainService.findByDtoId(id);
            result.setData(applyMaintainDto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统错误");
        }

        return result;
    }
    @RequestMapping("/deleteById")
    @ResponseBody
    public ActionResult deleteById(Integer id){
        ActionResult result = new ActionResult(true);
        try {
            iApplyMaintainService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统错误");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ActionResult update(Integer id, Integer state,String adminAddrice,String maintainIndexCode,String maintainPeople,String maintainPeoplePhone,String message){
        ActionResult result = new ActionResult(true);
        try {
            iApplyMaintainService.update(id,state,adminAddrice,maintainIndexCode,maintainPeople,maintainPeoplePhone,message);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统错误");
        }
        return result;
    }
    /**
     * 导出excel 信息
     */
    @RequestMapping("/exportMainTain")
    @ResponseBody
    public  void  exportExport(Integer state,Integer type,Integer uergencyState,String search){
        try {
            iApplyMaintainService.ExportApplyMainInfo(state,type,uergencyState,search);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
