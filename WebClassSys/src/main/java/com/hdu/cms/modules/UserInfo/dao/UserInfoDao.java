package com.hdu.cms.modules.UserInfo.dao;

import com.google.common.collect.Lists;
import com.hdu.cms.common.HibernateUtilExtentions.HibernateEntityDao;
import com.hdu.cms.common.HibernateUtilExtentions.PageBean;
import com.hdu.cms.common.Utils.ArrayUtils;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JetWang on 2016/10/6.
 * 用户信息操作Dao
 */
@Repository(value = "userInfo")
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

        UserInfo info = super.findSingleBy("userName", userName);
        return info;
    }

    /**
     * 根据Indexcode 取得用户的信息
     *
     * @param indexcode
     * @return
     */
    public UserInfo getUserByIndexCode(String indexcode) {
        return super.findSingleBy("indexCode", indexcode);
    }

    /**
     * 分页获得查询的结果
     *
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageBean<UserInfo> getPageUserInfo(Integer pageSize, Integer pageNo, String search) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserInfo.class);
        detachedCriteria.addOrder(Order.asc("userType"));
        if (StringUtils.isNotEmpty(search)) {
            detachedCriteria.add(Restrictions.disjunction()
                            .add(Restrictions.like("userName", "%" + search + "%"))
                            .add(Restrictions.like("phone", "%" + search + "%"))
                            .add(Restrictions.like("password", "%" + search + "%"))

            );
        }
        return super.findPageByCriteria(detachedCriteria, pageNo, pageSize);
    }

    /**
     * 查询到所有的用户的记录信息
     *
     * @return
     */
    public List<UserInfo> getAllUserInfo() {
        return super.findALl();
    }

    public void userDeleteById(Integer id) {
        super.deleteById(id);
    }

    /**
     * 通过id 删除所有的信息
     *
     * @param ids
     */
    public void userDeleteByIds(List<Integer> ids) {
        String idsStr = ArrayUtils.listToDotString(ids);
        super.deleteAll("id", idsStr);
    }

    /**
     * 根据idxCodes删除所有的信息
     *
     * @param indexcodes
     */
    public void userDeleteByIndexcode(List<String> indexcodes) {
        String indexcodesStr = ArrayUtils.listToDotString(indexcodes);
        super.deleteAll("indexCode", indexcodesStr);
    }

    public void userDeleteByIndexcode(String indexcode) {
        super.executeUpdateSQL("delete  from UserInfo  where indexCode ='" + indexcode + "'");
    }


}
