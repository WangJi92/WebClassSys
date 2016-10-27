package com.hdu.cms.modules.UserInfo.service.impl;

import com.hdu.cms.modules.UserInfo.dao.UserInfoDao;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import com.hdu.cms.modules.UserInfo.service.IUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JetWang on 2016/10/25.
 */
@Service
public class UserInfoService implements IUserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public boolean logIn(String userName, String passWord) {
        UserInfo userInfo = getUserInfoByName(userName);
        if(userInfo !=null && userInfo.getPassword().equals(passWord)){
            return true;
        }
        return false ;
    }

    @Override
    public void saveOrUpdate(UserInfo userInfo) {
        userInfoDao.saveorUpdate(userInfo);
    }

    @Override
    public UserInfo getUserInfoByName(String userName) {
        return userInfoDao.getUserByName(userName);
    }

    @Override
    public UserInfo getUserInfoByPhone(String phone) {
        return userInfoDao.getUserByphone(phone);
    }

    @Override
    public UserInfo getUserInfoById(Integer id) {
        return userInfoDao.getUserById(id);
    }

    @Override
    public List<UserInfo> getAllInfo() {
        return userInfoDao.getAllUserInfo();
    }
}
