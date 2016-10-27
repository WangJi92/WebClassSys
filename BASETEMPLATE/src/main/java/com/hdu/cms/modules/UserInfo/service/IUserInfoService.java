package com.hdu.cms.modules.UserInfo.service;

import com.hdu.cms.modules.UserInfo.entity.UserInfo;

import java.util.List;

/**
 * Created by JetWang on 2016/10/25.
 */
public interface IUserInfoService {

    /**
     * 登录
     * @param userName
     * @param passWord
     * @return
     */
    public boolean logIn(String userName,String passWord);

    /**
     * 保存用户信息 或者修改用户信息；
     * @param userInfo
     */
    public void saveOrUpdate(UserInfo userInfo);

    /**
     * 通过用户名获得用户的信息
     * @param userName
     * @return
     */
    public UserInfo getUserInfoByName(String userName);

    /**
     *
     * @param phone
     * @return
     */
    public  UserInfo getUserInfoByPhone(String phone);

    /**
     * 通过Id 获取用户的信息
     * @param id
     * @return
     */
    public UserInfo getUserInfoById(Integer id);



    /**
     *
     * @return
     */
    public List<UserInfo> getAllInfo();


}
