package com.hdu.cms.modules.UserInfo.action;

import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.common.Utils.LogUtils;
import com.hdu.cms.common.Utils.RandomUtil;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import com.hdu.cms.modules.UserInfo.service.IUserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JetWang on 2016/10/6.
 * 用户信息操作类
 */
@Controller(value = "userInfoAction")
@RequestMapping("/UserInfo")
public class UserInfoAction extends BaseAction{
    @Resource
    private IUserInfoService iUserInfoService;

    @RequestMapping("/saveOrupdate")
    @ResponseBody
    /**
     * 保存或者更新用户的信息
     * userName userType userPhone password
     */
    public ActionResult saveOrUpdate(UserInfo userInfo){
        ActionResult actionResult = new ActionResult(true);
        if(userInfo!=null){
            if(userInfo.getId() !=null){
                UserInfo oldInfo = iUserInfoService.getUserInfoById(userInfo.getId());
                if(oldInfo.getUserName().equals(userInfo.getUserName())){
                    BeanUtils.copyProperties(userInfo,oldInfo);
                    iUserInfoService.saveOrUpdate(oldInfo);
                }else{
                    UserInfo info = iUserInfoService.getUserInfoByName(userInfo.getUserName());
                    if(info == null){
                        BeanUtils.copyProperties(userInfo,oldInfo);
                        iUserInfoService.saveOrUpdate(oldInfo);
                    }else{
                        actionResult.setSuccess(false);
                        actionResult.setMessage("用户名存在");
                        return  actionResult;
                    }
                }
            }else{
                UserInfo info = iUserInfoService.getUserInfoByName(userInfo.getUserName());
                if(info ==null){
                    userInfo.setIndexCode(RandomUtil.getUUID32BIT());
                    iUserInfoService.saveOrUpdate(userInfo);
                }else{
                    actionResult.setSuccess(false);
                    actionResult.setMessage("用户名存在");
                    return  actionResult;
                }
            }
             actionResult.setMessage("all is Ok");
            return  actionResult;
        }else{
            actionResult.setSuccess(false);
            actionResult.setMessage("信息为空");
            return  actionResult;
        }
    }

    @RequestMapping("/userInfoPage")
    @ResponseBody
    /**
     * 根据用户pageSize 或者pageNo获取到用户的信息列表
     */
    public ActionResult getUserInfoPage(Integer pageNo,Integer pageSize,String search){
        ActionResult result = new ActionResult(true);
        if(pageSize == null || pageNo ==null){
            pageNo = ConstantParam.DEFAULT_PAGE_NO;//1
            pageSize = ConstantParam.DEFAULT_PAGE_SIZE;//15
        }
        PageBean pageBean = iUserInfoService.findPageInfo(pageSize,pageNo,search);
        result.setData(pageBean);
        return  result;
    }

    @RequestMapping("/getUserInfoByName")
    @ResponseBody
    /**
     * 通过用户名获取到用户的信息
     */
    public ActionResult getUserInfoByName(String userName){
        ActionResult result = new ActionResult(true);
        UserInfo userInfo = iUserInfoService.getUserInfoByName(userName);
        if(userInfo == null){
            result.setMessage("用户信息不存在");
            return  result;
        }
        result.setData(userInfo);
        return result;
    }

    @RequestMapping("/getUserInfoById")
    @ResponseBody
    /**
     * 通过用户的Id获取到用户信息！
     */
    public ActionResult getUserInfoById(Integer id){
        ActionResult result = new ActionResult(true);
        UserInfo userInfo = iUserInfoService.getUserInfoById(id);
        if(userInfo == null){
            result.setMessage("用户信息不存在");
            return  result;
        }
        result.setData(userInfo);
        return result;
    }

    @RequestMapping("/deleteUserByIds")
    @ResponseBody
    public ActionResult deleteByIds(@RequestParam("ids[]")List<Integer> ids){
       ActionResult result = new ActionResult(true);
        if(CollectionUtils.isNotEmpty(ids)){
            try {
                iUserInfoService.deleteByIds(ids);
            } catch (Exception e) {
                e.printStackTrace();
                result.setSuccess(false);
                result.setMessage("删除异常");
                return  result;
            }
        }
        return  result;
    }

    /**
     * 返回添加用户的对话View
     * @return
     */
    @RequestMapping("/userInfoAddView")
    public ModelAndView getUserInfoAddView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/usermanage/dialog/add/user_add");
        return modelAndView;
    }
    @RequestMapping("exportUserInfo")
    @ResponseBody
    public void exportUserInfo(){
       // ActionResult result = new ActionResult(true);
        try {
            iUserInfoService.exportUserInfo();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.logException(e);
         //   result.setSuccess(false);
           // result.setMessage("导出错误");
        }
      //  return result;
    }
}
