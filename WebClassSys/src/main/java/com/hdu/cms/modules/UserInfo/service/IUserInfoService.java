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
    public PageBean  findPageInfo(Integer pageSize,Integer pageNo,String serarch,Integer userType);

    /**
     * 登录
     * @param passWord
     * @return
     */
    public boolean login(String loginAccount,String passWord);

    /**
     * 保存用户信息 或者修改用户信息；
     * @param userInfo
     */
    public void saveOrUpdate(UserInfo userInfo);

    /**
     * 通过登录名
     * @param loginAccount
     * @return
     */
    public UserInfo getUserByLgoinAccount(String loginAccount);


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
