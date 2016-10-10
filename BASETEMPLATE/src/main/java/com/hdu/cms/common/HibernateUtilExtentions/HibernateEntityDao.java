package com.hdu.cms.common.HibernateUtilExtentions;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HibernateEntityDao<T> extends HibernatePageSupportDao {
	protected  Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public HibernateEntityDao() {
		super();
		Type type =this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) type).getActualTypeArguments();
		this.entityClass = (Class) params[0];//设置 t.class;	
	}
	/**
	 * 清除某个个体的缓存
	 * @param object
	 *@author wangji
	 * @date 2016年5月21日-下午1:48:16
	 */
	public void evict(Object object){
		getHibernateTemplate().evict(object);
	}
	/**
	 * 根据实体返回查询的条件
	 * @param inputEntity
	 * @param criterions
	 * @return
	 *@author wangji
	 * @date 2016年5月21日-下午2:00:21
	 */
	public Criteria createCriteria(Class<T> inputEntity,Criterion...criterions){
		Assert.notNull(inputEntity, "this is not null");
		Session session = currentSession();
		//xxx.class
		Criteria criteria = session.createCriteria(inputEntity);
		for(Criterion criterion: criterions){
			criteria.add(criterion);
		}
		return criteria;
		
	}
	/**
	 * 根据实体返回查询条件 进行封装
	 * @param criterions
	 * @return
	 *@author wangji
	 * @date 2016年5月21日-下午2:00:40
	 */
	public Criteria createCriteria(Criterion...criterions){
		return createCriteria(this.entityClass, criterions);
	}
	/**
	 * 根据实体返回查询的条件
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 *@author wangji
	 * @date 2016年5月21日-下午2:03:17
	 */
	public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
		Assert.hasText(orderBy);
		Criteria criteria = createCriteria(criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}

		return criteria;
	}
	/**
	 * 通过ID查找实体
	 * @param id
	 * @return
	 *@author wangji
	 * @date 2016年5月21日-下午2:08:05
	 */
	public T findById(Serializable id){
		return findById(this.entityClass, id);
	}
	/**
	 * 通过ID查找实体
	 * @return
	 *@author wangji
	 * @date 2016年5月21日-下午2:08:19
	 */
	public T findById(Class<T> inputEntity,Serializable id){
		Assert.notNull(inputEntity, "is not null");
		return getHibernateTemplate().get(inputEntity, id);
	}
	/**
	 * 查询所有的  load延迟加载
	 * @param inputEntity
	 * @return
	 * @author wangji
	 * @date 2016年5月21日-下午2:17:14
	 */
	public List<T> findAll(Class<T> inputEntity){
		Assert.notNull(inputEntity, "is not null");
		return getHibernateTemplate().loadAll(inputEntity);
		
	}
	/**
	 * 查询所有的  load延迟加载
	 * @return
	 * @author wangji
	 * @date 2016年5月21日-下午2:17:14
	 */
	public List<T> findALl(){
		return findAll(this.entityClass);
	}
	/**
	 * 通过某个属性找到唯一的值
	 * @param inputEntity
	 * @param propertyName
	 * @param value
	 * @return
	 *@author wangji
	 * @date 2016年5月21日-下午2:30:52
	 */
	@SuppressWarnings("unchecked")
	public T findSingleBy(Class<T> inputEntity,String propertyName, Object value){
		Criteria criteria = createCriteria(inputEntity,Restrictions.eq(propertyName, value));
		return (T) criteria.uniqueResult();
	}
	/**
	 * 通过某个属性找到唯一的值
	 * @param propertyName
	 * @param value
	 * @return
	 * @author wangji
	 * @date 2016年5月21日-下午2:33:03
	 */
	public <T> T findSingleBy(String propertyName, Object value){
		return (T) findSingleBy(this.entityClass, propertyName, value);
	}
	/**
	 * 通过某个属性返回一个列表
	 * @param inputEntity
	 * @param propertyName
	 * @param value
	 * @return
	 *@author wangji
	 * @date 2016年5月21日-下午2:35:34
	 */
	@SuppressWarnings("unchecked")
	public List<T> findListBy(Class<T> inputEntity,String propertyName, Object value){
		Criteria criteria = createCriteria(inputEntity,Restrictions.eq(propertyName, value));
		return  criteria.list();
	}
	/**
	 * 通过某个属性返回一个list
	 * @param propertyName
	 * @param value
	 * @return
	 *@author wangji
	 * @date 2016年5月21日-下午2:35:21
	 */
	public List<T> findListBy(String propertyName, Object value) {
		return findListBy(entityClass, propertyName, value);
	}
	/**
	 * 返回一个列表，按照某个排序
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @return
	 *@author wangji
	 * @date 2016年5月21日-下午2:36:56
	 */
	public List<T> findListBy(String propertyName, Object value, String orderBy, boolean isAsc) {
		
		return createCriteria(orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
	}
	/**
	 * 使用getHibernateTemplate().find 查询
	 * 使用占位符哦
	 * @param hql
	 * @param values
	 * @return
	 *@author wangji
	 * @date 2016年5月21日-下午2:42:45
	 */
	public <T> List<T> findListByHql(String hql, Object... values) {
		Assert.hasText(hql);
		return (List<T>) getHibernateTemplate().find(hql, values);
	}
	/**
	 * 通过Id来删除对象
	 * @param id
	 *@author wangji
	 * @date 2016年5月21日-下午2:44:07
	 */
	public void deleteById(Serializable id) {
		T t = findById(id);
		if (t != null){
			delete(t);
		}
	}
	/**
	 * in(a,b,c,d)
	 * @param propertyName
	 * @param propertyValue
	 *@author wangji
	 * @date 2016年5月21日-下午2:48:06
	 */
	public void deleteAll(final String propertyName, final String propertyValue) {
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				StringBuffer hql = new StringBuffer(50);
				hql.append("delete from ");
				hql.append(entityClass.getSimpleName());
				hql.append(" where ");
				hql.append(propertyName);
				hql.append(" in (");
				hql.append(propertyValue);
				hql.append(")");
				Query query = session.createQuery(hql.toString());
				query.executeUpdate();
				return null;
			}
		});
	}
	public void deleteAll(final String propertyName, final String propertyValue, final Class cls) {
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				StringBuffer hql = new StringBuffer(50);
				hql.append("delete from ");
				hql.append(cls.getSimpleName());
				hql.append(" where ");
				hql.append(propertyName);
				hql.append(" in (");
				hql.append(propertyValue);
				hql.append(")");
				Query query = session.createQuery(hql.toString());
				query.executeUpdate();
				return null;
			}
		});
	}
	/**
	 * 多个属性对应
	 * @param propertyNames
	 * @param propertyValues
	 *@author wangji
	 * @date 2016年5月21日-下午2:51:20  
	 */
	public void deleteAll(final List<String> propertyNames, final List<Object> propertyValues) {
		if (propertyNames.size() != propertyValues.size()) {
			throw new ArrayStoreException("数组长度不匹配");
		}
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				StringBuffer hql = new StringBuffer(50);
				hql.append("delete from ");
				hql.append(entityClass.getSimpleName());
				hql.append(" where ");
				hql.append(" 1=1 ");
				for (int i = 0, l = propertyNames.size(); i < l; i++) {
					hql.append(" and  ");
					hql.append(propertyNames.get(i));
					if (propertyValues.get(i) instanceof Collection) {
						hql.append(" in (");
						hql.append(prdIds((Collection) propertyValues.get(i)));
						hql.append(")");
					} else {
						hql.append("=");
						hql.append(propertyValues.get(i));
					}
				}
				Query query = session.createQuery(hql.toString());
				query.executeUpdate();
				return null;
			}
		});
	}
   /**
    * 删除小于等于的
    * @param property
    * @param date
    *@author wangji
    * @date 2016年5月21日-下午2:53:31
    */
	public void deleteAllByDate(final String property, final Date date) {
		super.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				StringBuffer hql = new StringBuffer(50);
				hql.append("delete from ");
				hql.append(entityClass.getName());
				hql.append(" where ");
				hql.append(property);
				hql.append("<=:dt");
				Query query = session.createQuery(hql.toString());
				query.setTimestamp("dt", date);
				query.executeUpdate();
				return null;
			}
		});
	}
	/**
	 * //String hqlS = "from Student where id in :valueList";
     * String hqlS = "from Student where id in (:valueList)";
     *  Query queryS = session.createQuery(hqlS);	 
     *  queryS.setParameterList("valueList", params);
	 * Map<String, Object> paramsMap 这个就是查询的条件吧
	 * @param hqlStr
	 * @param paramsMap
	 * @author wangji
	 * @date 2016年5月21日-下午3:00:40
	 */
	public void deleteAllWithHql(final String hqlStr, final Map<String, Object> paramsMap){
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(hqlStr);
				for (Entry<String, Object> entry : paramsMap.entrySet()) {
					if (entry.getValue() instanceof Collection) {
						//这里好像就是 in之类的东西吧！
						query.setParameterList(entry.getKey(), (Collection) entry.getValue());
					} else {
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				query.executeUpdate();
				return null;				
			}			
		});
	} 
	public void deleteAllWithSql(final String sqlStr, final Map<String, Object> paramsMap) {
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = session.createSQLQuery(sqlStr);
				for (Entry<String, Object> entry : paramsMap.entrySet()) {
					if (entry.getValue() instanceof Collection) {
						query.setParameterList(entry.getKey(), (Collection) entry.getValue());
					} else {
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				query.executeUpdate();
				return null;				
			}			
		});
	}
	public void updateAllWithHql(final String hqlStr, final Map<String, Object> paramsMap){
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(hqlStr);
				for (Entry<String, Object> entry : paramsMap.entrySet()) {
					if (entry.getValue() instanceof Collection) {
						//这里好像就是 in之类的东西吧！
						query.setParameterList(entry.getKey(), (Collection) entry.getValue());
					} else {
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				query.executeUpdate();
				return null;				
			}			
		});
	}
	public void updateAllWithSql(final String sqlStr, final Map<String, Object> paramsMap) {
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = session.createSQLQuery(sqlStr);
				for (Entry<String, Object> entry : paramsMap.entrySet()) {
					if (entry.getValue() instanceof Collection) {
						query.setParameterList(entry.getKey(), (Collection) entry.getValue());
					} else {
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				query.executeUpdate();
				return null;				
			}			
		});
	}
	
	private String prdIds(Collection propertyValue) {
		StringBuffer ids = new StringBuffer();
		for (Object value : propertyValue) {
			ids.append(value).append(",");
		}
		String result = ids.substring(0, ids.length() - 1);
		return result;
	}
	
	

}
