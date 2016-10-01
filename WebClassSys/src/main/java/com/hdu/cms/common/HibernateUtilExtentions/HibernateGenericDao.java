package com.hdu.cms.common.HibernateUtilExtentions;

import org.hibernate.*;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.Collection;
import java.util.List;

public class HibernateGenericDao extends HibernateDaoSupport {
	@Autowired
	public void setSessionFactoryEx(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

   //批量提交阀值
	protected static final int BATCH_COMMINT_THRESHOLD = 1000;
	 
	//批量提交默认值
	protected static final int BATCH_COMMIT_DEFAULT_VALUE = 100;
	
	protected static final int OPER_TYPE_SAVE = 1;
	
	protected static final int OPER_TYPE_UPDATE = 2;
	
	protected static final int OPER_TYPE_SAVEORUPDATE = 3;
	
	protected static final int OPER_TYPE_MEGER = 4;
	public void update(Object object){
		getHibernateTemplate().save(object);
	}
	public void update(List<?> list){
		this.update(list,BATCH_COMMIT_DEFAULT_VALUE);
	}
	public void update(List<?> list,int batchCommitSize){
		this.doOperationList(list, batchCommitSize, OPER_TYPE_UPDATE);
	}
	public void save(Object object){
		getHibernateTemplate().save(object);
	}
	public void save(List<?> list){
		this.save(list,BATCH_COMMIT_DEFAULT_VALUE);
	}
	public void save(List<?> list ,int batchCommitSize){
		this.doOperationList(list, batchCommitSize, OPER_TYPE_SAVE);
	}
	public void saveOrUpdate(Object object){
		getHibernateTemplate().saveOrUpdate(object);
	}
	public void saveOrUpdate(List<?> list){
		this.saveOrUpdate(list,BATCH_COMMIT_DEFAULT_VALUE);
	}
	public void saveOrUpdate(List<?> list,int batchCommitSize){
		this.doOperationList(list, batchCommitSize, OPER_TYPE_SAVEORUPDATE);
	}
	
	public void clear() {
		getHibernateTemplate().clear();
	}
	public void merge(Object object){
		getHibernateTemplate().merge(object);
	}
 
	public void flush() {
		getHibernateTemplate().flush();
	}
	public void delete(Object object){
		getHibernateTemplate().delete(object);
	}
	
	public void deleteAll(Collection<?> object){
		getHibernateTemplate().deleteAll(object);
	}
	public void mergeList(List<?> list){
		this.mergeList(list, BATCH_COMMIT_DEFAULT_VALUE);
	}
	public void mergeList(List<?> list,int batchCommitSize){
		this.doOperationList(list,batchCommitSize,OPER_TYPE_MEGER);
	}
	
	/**
	 * 根据hql占位符查询结果
	 * @param hql
	 * @param values
	 * @return
	 *@author wangji
	 * @date 2016年5月19日-下午4:19:22
	 */
	public Query createQuery(String hql,Object...values){
		Query query =currentSession().createQuery(hql);
		for(int i=0;i<values.length;i++){
			query.setParameter(i, values[i]);
		}
		return query;
	}

	/**
	 * 返回一个结果
	 * 
	 * @param detachCriteria
	 * @return
	 * @author wangji
	 * @date 2016年5月19日-下午4:04:59
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T findSingleCriteria(final DetachedCriteria detachCriteria) {
		return (T) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<T>() {
					@Override
					public T doInHibernate(Session session)
							throws HibernateException {
						// TODO Auto-generated method stub
						Criteria criteria = detachCriteria
								.getExecutableCriteria(session);
						detachCriteria
								.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
						return (T) criteria.uniqueResult();

					}

				});

	}
	/**
	 * 按照条件查询list
	 * @param detachCriteria
	 * @return
	 *@author wangji
	 * @date 2016年5月19日-下午4:13:20
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> findAllByCriteria(final DetachedCriteria detachCriteria){
		return (List<T>)getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				Criteria criteria = detachCriteria
						.getExecutableCriteria(session);
				detachCriteria
						.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				return  (T) criteria.list();
				
			}
		});
	}
	@SuppressWarnings({"unchecked", "rawtypes"})
    public void executeUpdateSQL(final String sql){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException {
				if (sql == null || sql.trim().length()==0) {
				}else{
					Query query = session.createSQLQuery(sql);
					try{
						query.executeUpdate();
					}catch (Exception e) {
					}
				}
				return null;
			}
		});
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void executeUpdateHql(final String hql){
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				if (hql == null || hql.trim().length()==0) {
				}else{
					Query query = session.createQuery(hql);
					try{
						query.executeUpdate();
					}catch (Exception e) {
					}
				}
				return null;
			}
		});
	}
	protected int limitBatchCommitSize(int batchCommitSize){
		if (batchCommitSize <=0 || batchCommitSize > BATCH_COMMINT_THRESHOLD){
			return BATCH_COMMINT_THRESHOLD;
		}
		
		return batchCommitSize;
	}
	private void doOperationList(List<?> list, int batchCommitSize, int operType){
		if (list == null || list.isEmpty()){
			return;
		}
		batchCommitSize = limitBatchCommitSize(batchCommitSize);
		if (list.size() <= batchCommitSize){
			//1 stand for save
			if (operType == OPER_TYPE_SAVE){
				for (int i = 0; i < list.size(); i++){
					getHibernateTemplate().save(list.get(i));
				}
			} else if (operType == OPER_TYPE_UPDATE){ // 2 stand for update
				for (int i = 0; i < list.size(); i++){
					getHibernateTemplate().update(list.get(i));
				}
			} else if (operType == OPER_TYPE_SAVEORUPDATE){ // 3 stand for saveOrUpdate
				for (int i = 0; i < list.size(); i++){
					getHibernateTemplate().saveOrUpdate(list.get(i));
				}
			} else if (operType == OPER_TYPE_MEGER){ // 4 stand for merge
				for (int i = 0; i < list.size(); i++){
					getHibernateTemplate().merge(list.get(i));
				}
			} else {
				throw new IllegalArgumentException("Unknow operType:" + operType);
			}
			getHibernateTemplate().flush();
		} else {
			//1 stand for save
			if (operType == OPER_TYPE_SAVE){
				for (int i = 0; i < list.size(); i++){
					getHibernateTemplate().save(list.get(i));
					if ( i != 0 && i % batchCommitSize == 0){
						getHibernateTemplate().flush();
					}
				}
			} else if (operType == OPER_TYPE_UPDATE){ // 2 stand for update
				for (int i = 0; i < list.size(); i++){
					getHibernateTemplate().update(list.get(i));
					if ( i != 0 && i % batchCommitSize == 0){
						getHibernateTemplate().flush();
					}
				}
			} else if (operType == OPER_TYPE_SAVEORUPDATE){ // 3 stand for saveOrUpdate
				for (int i = 0; i < list.size(); i++){
					getHibernateTemplate().saveOrUpdate(list.get(i));
					if ( i != 0 && i % batchCommitSize == 0){
						getHibernateTemplate().flush();
					}
				}
			} else if (operType == OPER_TYPE_MEGER){ // 4 stand for merge
				for (int i = 0; i < list.size(); i++){
					getHibernateTemplate().merge(list.get(i));
					if ( i != 0 && i % batchCommitSize == 0){
						getHibernateTemplate().flush();
					}
				}
			} else {
				throw new IllegalArgumentException("Unknow operType:" + operType);
			}
			
			getHibernateTemplate().flush();
		}
	}


}
