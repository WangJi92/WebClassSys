package com.hdu.cms.modules.UserInfo.action;

import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import com.hdu.cms.modules.UserInfo.service.IUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by JetWang on 2016/10/25.
 */
@RequestMapping("/user")
@Controller
public class UserInfoAction extends BaseAction {

    @Resource
    private IUserInfoService iUserInfoService;

    @RequestMapping("/getUserInfoByName")
    @ResponseBody
    /**
     * 通过用户名获取到用户的信息
     */
    public ActionResult getUserInfoByName(String name){
        ActionResult result = new ActionResult(true);
        try {
            if(StringUtils.isNotEmpty(name)){
                UserInfo userInfo = iUserInfoService.getUserInfoByName(name);
                if(userInfo == null){
                    result.setMessage("用户信息不存在");
                    result.setSuccess(false);
                    return  result;
                }
                result.setData(userInfo);
            }else{
                result.setMessage("参数错误");
                result.setSuccess(false);
            }
        } catch (Exception e) {
            result.setMessage("参数错误");
            result.setSuccess(false);
        }

        return result;
    }

    @RequestMapping("/getUserInfoById")
    @ResponseBody
    /**
     * 通过用户的Id获取到用户信息！
     */
    public ActionResult getUserInfoById(Integer id){
        ActionResult result = new ActionResult(true);
        try {
            if(id !=null){
                UserInfo userInfo = iUserInfoService.getUserInfoById(id);
                if(userInfo == null){
                    result.setMessage("用户信息不存在");
                    result.setSuccess(false);
                    return  result;
                }
                result.setData(userInfo);
            }else{
                result.setMessage("参数错误");
                result.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("参数错误");
            result.setSuccess(false);
        }

        return result;
    }

    @RequestMapping("/saveOrupdate")
    @ResponseBody
    /**
     * 保存或者更新用户的信息
     * userName userType userPhone password
     */
    public ActionResult saveOrUpdate(UserInfo userInfo){
        ActionResult actionResult = new ActionResult(true);
        try {
            if(userInfo!=null){
                if(userInfo.getId() !=null){
                    UserInfo oldInfo = iUserInfoService.getUserInfoById(userInfo.getId());
                    if(oldInfo.getName().equals(userInfo.getName())){
                        BeanUtils.copyProperties(userInfo, oldInfo);
                        iUserInfoService.saveOrUpdate(oldInfo);
                    }else{
                        UserInfo info = iUserInfoService.getUserInfoByName(userInfo.getName());
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
                    UserInfo info = iUserInfoService.getUserInfoByName(userInfo.getName());
                    if(info ==null){
                        iUserInfoService.saveOrUpdate(userInfo);
                    }else{
                        actionResult.setSuccess(false);
                        actionResult.setMessage("用户名存在");
                        return  actionResult;
                    }
                }
                actionResult.setMessage("成功注册");
                return  actionResult;
            }else{
                actionResult.setSuccess(false);
                actionResult.setMessage("信息为空");
                return  actionResult;
            }
        } catch (BeansException e) {
            e.printStackTrace();
            actionResult.setSuccess(false);
            actionResult.setMessage("信息为空");
            return  actionResult;
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public  ActionResult login(String name,String password){
        ActionResult result = new ActionResult(true);
        try {
            if(StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(password)){
                boolean login = iUserInfoService.logIn(name,password);
                if(login == false){
                    result.setSuccess(false);
                    result.setMessage("用户名或密码错误");
                }else{
                    result.setMessage("登录成功");
                }
            }else{
                result.setSuccess(false);
                result.setMessage("参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统异常");
        }
        return result;
    }
}
