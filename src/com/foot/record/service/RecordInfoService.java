package com.foot.record.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.foot.record.dao.RecordDao;
import com.foot.record.dao.RecordLoggerDao;
import com.foot.record.entity.RecordInfoQueryForm;
import com.foot.record.entity.Record;
import com.foot.record.entity.RecordLogger;
import com.foot.record.page.PageBean;
import com.foot.record.utils.FrameworkBeansName;
import com.foot.record.utils.ReadExcel;

@Service(FrameworkBeansName.RECORD_INFO_SERVICE_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class RecordInfoService {
	private RecordDao recordDao;
	private RecordLoggerDao loggerDao;

	public RecordDao getRecordDao() {
		return recordDao;
	}

	@Autowired
	public void setOrderDao(@Qualifier(FrameworkBeansName.RECORD_INFO_DAO_BEAN) RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	@Autowired
	public void setLoggerDao(@Qualifier(FrameworkBeansName.LOGGER_INFO_DAO_BEAN) RecordLoggerDao loggerDao) {
		this.loggerDao = loggerDao;
	}

	public void addRecord(Record record) {
		this.recordDao.save(record);
	}

	public void updateRecord(Record record) {
		this.recordDao.update(record);
	}

	public void deleteRecord(long id) {
		this.recordDao.delete(id);
	}

	public Record getRecordById(long id) {
		return this.recordDao.find(id);
	}

	

	public List<Record> getRecordList(RecordInfoQueryForm recordForm,PageBean page) {
		String department = recordForm.getDepartment();
		String region = recordForm.getRegion();
		String registAddress = recordForm.getRegistAddress();
		String postalAddress = recordForm.getPostalAddress();
		String contacts = recordForm.getContacts();
		String street = recordForm.getStreet();
		String addedTax = recordForm.getAddedTax();
		String incomeTax = recordForm.getIncomeTax();
		String manageTax = recordForm.getManageTax();
		String returnTax = recordForm.getReturnTax();
		String phone = recordForm.getPhone();
		int customerType = recordForm.getCustomerType();
		String initDateStart = recordForm.getInitDateStart();
		String initDateEnd = recordForm.getInitDateEnd();
		int isexport = recordForm.getIsexport();
		String wxNumber = recordForm.getWxNumber();
		String orderNumber = recordForm.getOrderNumber();
		int status = recordForm.getStatus();
		int star = recordForm.getStar();
		int returnTaxPercentage = recordForm.getReturnTaxPercentage();// 返税提成
		int orderPercentage = recordForm.getOrderPercentage(); //成单提成
		String queryCount = "select count(*) from Record where 1=1";
		String queryList = " 1 = 1";
		StringBuffer sb = new StringBuffer();
		if (department != null && department.trim().length() > 0) {
			sb.append(" and department like '%").append(department).append("%'");
		}
		if (region != null && region.trim().length() > 0) {
			sb.append(" and region like '%").append(region).append("%'");
		}
		if (registAddress != null && registAddress.trim().length() > 0) {
			sb.append(" and registAddress like '%").append(registAddress).append("%'");
		}
		if (postalAddress != null && postalAddress.trim().length() > 0) {
			sb.append(" and postalAddress like '%").append(postalAddress).append("%'");
		}
		if (contacts != null && contacts.trim().length() > 0) {
			sb.append(" and contacts like '%").append(contacts).append("%'");
		}
		if (street != null && street.trim().length() > 0) {
			sb.append(" and street like '%").append(street).append("%'");
		}
		if (phone != null && phone.trim().length() > 0) {
			sb.append(" and phone like '%").append(phone).append("%'");
		}
		if (wxNumber != null && wxNumber.trim().length() > 0) {
			sb.append(" and wxNumber like '%").append(wxNumber).append("%'");
		}
		if (addedTax!=null && addedTax.trim().length()>0) {
			sb.append(" and addedTax='").append(addedTax).append("'");
		}
		if (incomeTax != null&& incomeTax.trim().length()>0) {
			sb.append(" and incomeTax='").append(incomeTax).append("'");
		}
		if (manageTax != null&& manageTax.trim().length()>0) {
			sb.append(" and manageTax='").append(manageTax).append("'");
		}
		if (returnTax != null&& returnTax.trim().length()>0) {
			sb.append(" and returnTax='").append(returnTax).append("'");
		}
		if(customerType >0){
			sb.append(" and customerType=").append(customerType);
		}
		if (orderNumber != null && orderNumber.trim().length() > 0) {
			sb.append(" and orderNumber like '%").append(orderNumber).append("%'");
		}
		if (initDateStart != null && initDateStart.trim().length() > 0) {
			sb.append(" and initDate >=str_to_date('" + initDateStart + "','%Y-%m-%d %H:%I:%S')");
		}
		if (initDateEnd != null && initDateEnd.trim().length() > 0) {
			sb.append(" and initDate <=str_to_date('" + initDateEnd + "','%Y-%m-%d %H:%I:%S')");
		}
		if (status > -1) {
			sb.append(" and status = ").append(status);
		}
		if(star >-1 ){
			sb.append(" and star =").append(star);
		}
		if(returnTaxPercentage >-1 ){
			sb.append(" and returnTaxPercentage =").append(returnTaxPercentage);
		}
		if(orderPercentage >-1 ){
			sb.append(" and orderPercentage =").append(orderPercentage);
		}
		sb.append(" order by initDate desc");
		List<Record> orders = recordDao.queryList(queryCount + sb.toString(), queryList + sb.toString(), null, page);
		return orders;
	}
	/**
	 * 初始清单导入
	 * 
	 * @param file
	 * @param userId
	 * @return
	 */
	public String readExcelFile(MultipartFile file, long userId) {
		String result = "";
		ReadExcel readExcel = new ReadExcel();
		List<Record> recordList = readExcel.getExcelInfo(file);
		// 保存到数据库
		if (recordList == null || recordList.size() == 0) {
			result = "error";
			return result;
		}
		for (int i = 0; i < recordList.size(); i++) {
			// 检查资产标签号是否重复
			Record nrecord = recordList.get(i);
			Record crecord = recordDao.getBy("department", nrecord.getDepartment());
			if (crecord != null) {
				result += (i + 2) + ",";
				continue;
			}else {
			synchronized (this) {
					nrecord.setOperatorId(userId);
					nrecord.setModifyDate(new Date());
					recordDao.save(nrecord);
					// 添加日志
					try {
						RecordLogger logger = new RecordLogger();
						logger.setOrderNumber(nrecord.getOrderNumber());
						logger.setRecordId(nrecord.getId());
						logger.setOrigin(-1);
						logger.setGoal(nrecord.getStatus());
						logger.setType(4);
						logger.setOperatorId(userId);
						logger.setCreateDate(new Date());
						logger.setDescription("初始导入");
						loggerDao.save(logger);
					} catch (Exception e) {

					}
			}	
			}
		}
		if (!result.equals("")) {
			result = result.substring(0, result.length() - 1);
			result = "[" + result + "]";
		} else {
			result = "success";
		}
		return result;
	}
	public Record getRecordByProperty(String propertyName, String value) {
		Record order = recordDao.getBy(propertyName, value);
		return order;
	}

	public int getCount(String sql) {
		return this.recordDao.getCount(sql);
	}
	public List getProperty(String sql) {
		return this.recordDao.getProperty(sql);
	}
}
