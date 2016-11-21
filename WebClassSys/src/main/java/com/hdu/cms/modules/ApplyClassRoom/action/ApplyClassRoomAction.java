package com.hdu.cms.modules.ApplyClassRoom.action;

import com.google.common.collect.Lists;
import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.ConstantParam.APPLYSTATE;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.common.Utils.SelectBean;
import com.hdu.cms.modules.ApplyClassRoom.entity.ApplyClassRoom;
import com.hdu.cms.modules.ApplyClassRoom.service.IApplyClassRoomService;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by JetWang on 2016/11/14.
 */
@Controller
@RequestMapping("/applyClassRoomAction")
public class ApplyClassRoomAction extends BaseAction {
   @Resource
   private IApplyClassRoomService iApplyClassRoomService;
    @RequestMapping("/applyClassRoom")
    @ResponseBody
    public ActionResult save(ApplyClassRoom applyClassRoom) {
        ActionResult result = new ActionResult(true);
        try {
            applyClassRoom.setState(APPLYSTATE.PROCESSING.getIndex());
            Date date = new Date();
            Timestamp nowTime = new Timestamp(date.getTime());
            applyClassRoom.setCreateTime(nowTime);
            UserInfo userInfo = (UserInfo) getServletRequest().getSession().getAttribute(ConstantParam.SESSION_USERNAME);
            applyClassRoom.setApplyIndexCode(userInfo!=null?userInfo.getIndexCode():"");
            iApplyClassRoomService.save(applyClassRoom,result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("申请失败");
        }
        return result;
    }

    @RequestMapping("/getPage")
    @ResponseBody
    public ActionResult pageBean(Integer pageNo,Integer pageSize,String classRoomIndexCode, String applyIndexCode,Integer  purpose,Integer state,String  search){
        ActionResult result = new ActionResult(true);
        try {
            if(pageNo == null || pageSize == null){
                pageNo = ConstantParam.DEFAULT_PAGE_NO;
                pageSize =ConstantParam.DEFAULT_PAGE_SIZE;
            }
            PageBean pageBean = iApplyClassRoomService.getPageBean(pageNo,pageSize,applyIndexCode,search,search,purpose,search,classRoomIndexCode,search,state,search,search);
            result.setData(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            result.setData(new PageBean());
        }
        return result;
    }

    /**
     * todo getPageByUser 保存在session中的信息
     * @param pageNo
     * @param pageSize
     * @param classRoomIndexCode
     * @param applyIndexCode
     * @param purpose
     * @param state
     * @param search
     * @return
     */
    @RequestMapping("/getPageByUser")
    @ResponseBody
    public ActionResult pageBeanByUser(Integer pageNo,Integer pageSize,String classRoomIndexCode, String applyIndexCode,Integer  purpose,Integer state,String  search){
        ActionResult result = new ActionResult(true);
        try {
            if(pageNo == null || pageSize == null){
                pageNo = ConstantParam.DEFAULT_PAGE_NO;
                pageSize =ConstantParam.DEFAULT_PAGE_SIZE;
            }
            UserInfo userInfo = (UserInfo) getServletRequest().getSession().getAttribute(ConstantParam.SESSION_USERNAME);
            applyIndexCode = userInfo!=null ?userInfo.getIndexCode():"";
            PageBean pageBean = iApplyClassRoomService.getPageBean(pageNo,pageSize,applyIndexCode,search,search,purpose,search,classRoomIndexCode,search,state,search,search);
            result.setData(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            result.setData(new PageBean());
        }
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ActionResult findById(Integer id){
        ActionResult result = new ActionResult(true);

        try {
            ApplyClassRoom applyClassRoom = iApplyClassRoomService.findById(id);
            result.setData(applyClassRoom);
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
           iApplyClassRoomService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统错误");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ActionResult update(Integer id, Integer state, String handleAddvice){
        ActionResult result = new ActionResult(true);
        try {
            iApplyClassRoomService.update(id,state,handleAddvice);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统错误");
        }
        return result;
    }

    /**
     * 撤销申请~ 不用了
     * @param id
     * @return
     */
    @RequestMapping("/cancel")
    @ResponseBody
    public ActionResult cancel(Integer id){
        ActionResult result = new ActionResult(true);
        try {
            iApplyClassRoomService.updateCanccelApply(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统错误");
        }
        return result;
    }
    @RequestMapping("/applystate")
    @ResponseBody
    public ActionResult applyState(Integer id){
        ActionResult result = new ActionResult(true);
        try {
            List<SelectBean> selectBeanList = Lists.newArrayList();
            for(APPLYSTATE item :APPLYSTATE.values()){
                SelectBean selectBean = new SelectBean();
                selectBean.setValue(item.getIndex());
                selectBean.setKey(item.getValue());
                selectBeanList.add(selectBean);
            }
            result.setData(selectBeanList);
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
    @RequestMapping("/exportExcell")
    @ResponseBody
    public  void  exportExport(Integer state,String search){
        try {
            iApplyClassRoomService.exportExcel(state,search);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
