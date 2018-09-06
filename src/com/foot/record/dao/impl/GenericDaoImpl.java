package com.foot.record.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.foot.record.dao.GenericDao;
import com.foot.record.exception.GenericException;
import com.foot.record.page.PageBean;

public class GenericDaoImpl<T> extends HibernateDaoSupport implements GenericDao<T> {

	private Class<T> clazz;

	@Autowired
	public void setSessionFactoryOne(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public GenericDaoImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	@Transactional
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	@Transactional
	public T find(Serializable id) {
		if (id == null) {
			return null;
		}
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);

	}

	@Override
	public void delete(Serializable id) {
		if (id == null)
			throw new IllegalArgumentException("ID value can not be empty in removing...");
		T object = this.find(id);
		if (object != null) {
			try {
				this.getHibernateTemplate().delete(object);
			} catch (Exception e) {
				throw new GenericException();
			}
		}

	}

	@Override
	public T getBy(final String propertyName, final Object value) {
		if (propertyName == null || "".equals(propertyName) || value == null)
			throw new IllegalArgumentException(
					"Call parameter is not correct attribute names and values are not empty in getBy");
		List<T> set = (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						String clazzName = clazz.getName();
						StringBuffer buffer = new StringBuffer(
								" select obj from ");
						buffer.append(clazzName).append(" obj");
						Query query = null;
						if (propertyName != null && value != null) {
							buffer.append(" where obj.").append(propertyName)
									.append(" = :value ");
							query = session.createQuery(buffer.toString())
									.setParameter("value", value);
						} else {
							query = session.createQuery(buffer.toString());
						}
						return query.list();
					}
				});
		if (set != null && set.size() == 1) {
			return set.get(0);
		} else if (set != null && set.size() > 1) {
			throw new IllegalStateException("More than one object find!");
		} else {
			return null;
		}
	}

	@Override
	public List<T> queryList(final String queryCount,final String queryString, final Object[] params, PageBean page) {
		final int begin,max;
		Long count = 0l ;
		if(queryCount != null ){
			count = (Long) queryForObject(queryCount, params);
		}
		if(page != null){
			page.setRecordCount(count);
			begin=(page.getCurrentPage()-1)*page.getPageSize();
			max=page.getPageSize();
		}else{
			begin = 0 ;
			max = 0 ;
		}
		
		List set = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						String clazzName = clazz.getName();
						StringBuffer buffer = new StringBuffer(
								" select obj from ");
						buffer.append(clazzName).append(" obj").append(
								" where ").append(queryString);
						Query query = session.createQuery(buffer.toString());
						int parameterIndex = 0;
						if (params != null && params.length > 0) {
							for (Object object : params) {
								query.setParameter(parameterIndex++, object);
							}
						}
						if (begin >= 0 && max > 0) {
							query.setFirstResult(begin);
							query.setMaxResults(max);
						}
						return query.list();
					}
				});
		if (set != null && set.size() >= 0) {
			return set;
		} else {
			return new ArrayList();
		}
	}
	
	protected Object queryForObject(final String select, final Object[] values) {
		HibernateCallback selectCallback = new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(select);
				if (values != null) {
					for (int i = 0; i < values.length; i++)
						query.setParameter(i, values[i]);
				}
				return query.uniqueResult();
			}
		};
		return this.getHibernateTemplate().execute(selectCallback);
	}

	@Override
	public int getCount(final String sql) {
		
		List list = this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
			
			List result  = session.createSQLQuery(sql).list();
			return result;
			}
			});
		
		
		if(list != null && list.size()>0){
			return ((BigInteger)list.get(0)).intValue();
		}
		return 0;
	}

	@Override
	public List getProperty(final String sql) {
		List list = this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
			
			List result  = session.createSQLQuery(sql).list();
			return result;
			}
			});
		return list;
	}
	
	

}
