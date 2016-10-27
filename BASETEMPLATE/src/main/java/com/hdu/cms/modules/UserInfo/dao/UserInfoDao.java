package com.hdu.cms.modules.UserInfo.dao;

import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JetWang on 2016/10/25.
 */
@Repository(value = "userInfoDao")
public class UserInfoDao extends HibernateEntityDao<UserInfo> {
    /**
     * 保存或者修改用户信息
     *
     * @param userInfo
     */
    public void saveorUpdate(UserInfo userInfo) {
        super.saveOrUpdate(userInfo);
        super.flush();
    }
    /**
     * 通过用户名查询到用户的信息
     *
     * @param id
     * @return
     */
    public UserInfo getUserById(Integer id) {
        return super.findById(id);
    }
    /**
     * 通过用户名查找到所有的用户的信息
     *
     * @param userName
     * @return
     */
    public UserInfo getUserByName(String userName) {

        UserInfo info = super.findSingleBy("name", userName);
        return info;
    }
    public UserInfo getUserByphone(String phone){
        UserInfo info = super.findSingleBy("phone", phone);
        return info;
    }
    /**
     * 查询到所有的用户的记录信息
     *
     * @return
     */
    public List<UserInfo> getAllUserInfo() {
        return super.findALl();
    }
}
