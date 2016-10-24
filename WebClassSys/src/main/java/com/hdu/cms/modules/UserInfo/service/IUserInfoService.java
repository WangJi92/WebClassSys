package com.hdu.cms.modules.UserInfo.service;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;

import java.util.List;

/**
 * Created by JetWang on 2016/10/6.
 * 用户信息类
 */
public interface IUserInfoService {

    /**
     * 分页查找用户的信息
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageBean  findPageInfo(Integer pageSize,Integer pageNo,String serarch);

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
     * 通过Id 获取用户的信息
     * @param id
     * @return
     */
    public UserInfo getUserInfoById(Integer id);

    /**
     *
     * @param indexCode
     * @return
     */
    public UserInfo getUserInfoByIndexCode(String indexCode);

    /**
     *
     * @return
     */
    public List<UserInfo> getAllInfo();

    /**
     *
     * @param ids
     */
    public void deleteByIds(List<Integer> ids);

    /**
     *
     * @param id
     */
    public void deleteById(Integer id);

    /**
     *
     * @param indexCode
     */
    public  void deleteByIndexCode(String indexCode);

    /**
     *
     * @param indexCodes
     */
    public void deleteByIndexCodes(List<String> indexCodes);

    /**
     * 导出excel
     */
    public  void exportUserInfo();

}
