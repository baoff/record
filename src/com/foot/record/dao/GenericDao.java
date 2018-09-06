package com.foot.record.dao;

import java.io.Serializable;
import java.util.List;

import com.foot.record.page.PageBean;

public interface GenericDao<T> {
	void save(T t);
	T find(Serializable id);
	void update(T t);
	void delete(Serializable id);
	T getBy(String propertyName,Object value);
	List<T> queryList(String querycount,String queryString,Object[] params,PageBean page);
	int getCount(String sql);
	List getProperty(String sql);
}
