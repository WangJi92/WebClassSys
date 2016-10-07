package com.hdu.cms.common.HibernateUtilExtentions;

import com.hdu.cms.common.Utils.LogUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.orm.hibernate4.HibernateCallback;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class HibernatePageSupportDao extends HibernateGenericDao {
	/**
	 * 
	 * @param countHql
	 *            数量Hql;
	 * @param selectHql
	 *            选择hql;
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 * @author wangji
	 * @date 2016年5月19日-下午5:08:55
	 */
	@SuppressWarnings("rawtypes")
	public PageBean findPageByHql(String countHql, String selectHql,
			int pageNo, int pageSize, Object... values) {

		if (pageNo == 0) {
			pageNo = 1;
		}
		List countList = getHibernateTemplate().find(countHql, values);
		int totalCount = 0;
		if (null != countList && countList.size() >= 0) {
			totalCount = ((Long) countList.get(0)).intValue();
		}
		if (totalCount < 0) {
			return new PageBean();
		} else {
			// 实际查询返回分页对象
			int startIndex = PageBean.countOffset(pageSize, pageNo);
			if (startIndex >= totalCount) {
				pageNo = (totalCount + pageSize - 1) / pageSize;
				startIndex = (pageNo - 1) * pageSize;
			}
			Query query = createQuery(selectHql, values);
			List rows = query.setFirstResult(startIndex)
					.setMaxResults(pageSize).list();
			return new PageBean(totalCount, pageNo, pageSize, rows);
		}
	}
	 /**
     * 1.Criteria设置Order对象，有语法问题 
	 * 		通过CriteriaImpl+orderEntrys+reflect解决 已实现
	 * 2.Criteria设置Projection，会使items和totalCount值不一致
	 * 		通过先获取items再获取totalCount解决 已实现
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public PageBean findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageNo, final int pageSize){
		return (PageBean) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);


				//先去掉Order部分
				List orderEntrys = null;
				Field field = null;
				CriteriaImpl impl = (CriteriaImpl) criteria;
				try {
					field = CriteriaImpl.class.getDeclaredField("orderEntries");
					field.setAccessible(true);
					orderEntrys = (List) field.get(impl);
					field.set(criteria, new ArrayList());
				} catch (SecurityException e) {
				} catch (IllegalArgumentException e) {
				} catch (NoSuchFieldException e) {
				} catch (IllegalAccessException e) {
				}

				//获取总记录数
				int totalCount = 0;
				try {
					totalCount = ((Number) criteria.setProjection(Projections.rowCount())
							.uniqueResult()).intValue();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//TODO totalCount和items换位置后这部分是不是可以不要了
				
				/*
				 * Criteria中默认使用的 ResultTransformer实现策略是 ROOT_ENTITY；
				 * 但是当调用了方法 setProjection后，会隐式地将策略设置为 PROJECTION。
				 */
				criteria.setProjection(null);
				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);//不希望返回数组，返回根对象

				//再恢复Order部分
				try {
					List innerOrderEntries = (List) field.get(criteria);
					for (int i = 0; i < orderEntrys.size(); i++) {
						innerOrderEntries.add(orderEntrys.get(i));
					}
					field.set(criteria, innerOrderEntries);
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				}
				// 返回分页对象
				if (totalCount < 1)
					return new PageBean(0, 0, pageSize, new ArrayList());

				int tempPageNo = pageNo;
				int offset = PageBean.countOffset(pageSize, tempPageNo);
				if (offset >= totalCount) {
					tempPageNo = (totalCount + pageSize - 1) / pageSize;
					offset = (tempPageNo - 1) * pageSize;
				}
				//获取结果集
				List items = criteria.setFirstResult(offset).setMaxResults(pageSize).list();
				PageBean ps = new PageBean(totalCount, tempPageNo, pageSize, items);

				return ps;
			}
		});
	}
    @SuppressWarnings("rawtypes")
    public int findCountByCriteria(final DetachedCriteria detachedCriteria){
    	@SuppressWarnings("unchecked")
		Integer count = (Integer) getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException{
    			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
    			//去掉Order部分
    			Field field = null;
				try {
	                field = CriteriaImpl.class.getDeclaredField("orderEntries");
	                field.setAccessible(true);
	                field.set(criteria, new ArrayList());
                } catch (SecurityException e) {
                } catch (IllegalArgumentException e) {
                } catch (NoSuchFieldException e) {
                } catch (IllegalAccessException e) {
                }
                
    			return criteria.setProjection(Projections.rowCount()).uniqueResult();
    		}
    	});
    	return count.intValue();
    }

}