package com.hdu.cms.modules.UserInfo.action;

import com.hdu.cms.common.BaseAction.BaseAction;
import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.STATE;
import com.hdu.cms.common.ConstantParam.USERTYPE;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.common.Utils.CookieUtils;
import com.hdu.cms.common.Utils.LogUtils;
import com.hdu.cms.common.Utils.RandomUtil;
import com.hdu.cms.modules.UserInfo.dto.UserType;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import com.hdu.cms.modules.UserInfo.service.IUserInfoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
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
                if(oldInfo.getLoginAccount()!=null && oldInfo.getLoginAccount().equals(userInfo.getLoginAccount())){
                    BeanUtils.copyProperties(userInfo,oldInfo);
                    iUserInfoService.saveOrUpdate(oldInfo);
                }else{
                    UserInfo info = iUserInfoService.getUserByLgoinAccount(userInfo.getLoginAccount());
                    if(info == null){
                        BeanUtils.copyProperties(userInfo,oldInfo);
                        iUserInfoService.saveOrUpdate(oldInfo);
                    }else{
                        actionResult.setSuccess(false);
                        actionResult.setMessage("登录名存在");
                        return  actionResult;
                    }
                }
            }else{
                UserInfo info = iUserInfoService.getUserByLgoinAccount(userInfo.getLoginAccount());
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
    public ActionResult getUserInfoPage(Integer pageNo,Integer pageSize,String search,Integer userType){
        ActionResult result = new ActionResult(true);
        if(pageSize == null || pageNo ==null){
            pageNo = ConstantParam.DEFAULT_PAGE_NO;//1
            pageSize = ConstantParam.DEFAULT_PAGE_SIZE;//15
        }
        PageBean pageBean = iUserInfoService.findPageInfo(pageSize,pageNo,search,userType);
        result.setData(pageBean);
        return  result;
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
    @RequestMapping("exportUserInfo")
    @ResponseBody
    public void exportUserInfo(){
        try {
            iUserInfoService.exportUserInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/login")
    @ResponseBody
    public ActionResult logIn(String  name,String password,Integer save){
        ActionResult result = new ActionResult(true);
        if(StringUtils.isNotEmpty(name)&&StringUtils.isNotEmpty(password)){
            UserInfo info = iUserInfoService.getUserByLgoinAccount(name);
            if(info!=null && info.getPassword().equals(password)){
                getServletRequest().getSession().setAttribute(ConstantParam.SESSION_USERNAME,info);
                if(save!=null  && save == STATE.YES.getValue()){
                    String passwordSalt = DigestUtils.md5Hex(info.getPassword()+ConstantParam.ADD_SALT);
                    String cookieLoginName = DigestUtils.md5Hex(ConstantParam.COOKIE_NAME+ConstantParam.ADD_SALT);
                    String cookiePasswordName =DigestUtils.md5Hex(ConstantParam.COOKIE_PASSWORD + ConstantParam.ADD_SALT);
                    CookieUtils.addCookie(getServletResponse(),cookiePasswordName,passwordSalt,30*24*60*60);
                    CookieUtils.addCookie(getServletResponse(),cookieLoginName,info.getLoginAccount(),30*24*60*60);
                }
                UserType  userType = new UserType();
                if(info.getUserType() !=null && info.getUserType().equals(USERTYPE.ADM.getIndex())){
                    userType.setType(STATE.YES.getValue());//是管理员
                }else{
                    userType.setType(STATE.NO.getValue());//非管理员
                }
                result.setData(userType);
            }else{
                result.setMessage("账户密码错误");
                result.setSuccess(false);
            }
        }else{
            result.setMessage("参数错误");
            result.setSuccess(false);
        }
      return result;
    }

    @ResponseBody
    @RequestMapping("/editpassword")
    public ActionResult updatePhoneAndPasswrod(String phone,String password,String newpassword){
        ActionResult result = new ActionResult(true);
        UserInfo userInfo = (UserInfo) getServletRequest().getSession().getAttribute(ConstantParam.SESSION_USERNAME);
        try {
            if(userInfo!=null){
                if(StringUtils.isNotEmpty(phone)){
                    userInfo.setPhone(phone);
                }
                if(StringUtils.isNotEmpty(password) &&StringUtils.isNotEmpty(newpassword)){
                    if(StringUtils.isNotEmpty(userInfo.getPassword()) && userInfo.getPassword().equals(password)){
                        userInfo.setPassword(newpassword);
                    }else{
                        result.setSuccess(false);
                        result.setMessage("原密码错误");
                        return  result;
                    }
                }
                iUserInfoService.saveOrUpdate(userInfo);
            }else{
                result.setSuccess(false);
                result.setMessage("系统错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("系统错误");
        }


        return  result;
    }
}
