package com.foot.record.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.foot.record.dao.RecordLoggerDao;
import com.foot.record.entity.RecordLogger;
import com.foot.record.utils.FrameworkBeansName;

@Service(FrameworkBeansName.LOGGER_INFO£ßSERVICE_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class RecordLoggerService {
	
	private RecordLoggerDao loggerDao;

	public RecordLoggerDao getLoggerDao() {
		return loggerDao;
	}
	
	@Autowired
	public void setLoggerDao(@Qualifier(FrameworkBeansName.LOGGER_INFO_DAO_BEAN)RecordLoggerDao loggerDao) {
		this.loggerDao = loggerDao;
	}
	
	public void addLogger(RecordLogger logger){
		if(logger.getOrderNumber() !=null ){
			loggerDao.save(logger);
		}
	}
}
