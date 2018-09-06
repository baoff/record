package com.foot.record.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.foot.record.utils.FrameworkBeansName;

@Entity()
@Table(name = "record_logger")
@Component(FrameworkBeansName.LOGGER_INFO_MODEL_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class RecordLogger implements Serializable{
	private long id;
	private String orderNumber;
	private long recordId;
	private int origin;
	private int goal;
	private int type ; //1 创建;2 删除;3 修改保存;4初始导入
	private long operatorId;
	private Date createDate;
	private String description;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 20)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Basic()
	@Column(name = "orderNumber", precision = 20)
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@Basic()
	@Column(name = "recordId", precision = 20)
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	
	@Basic()
	@Column(name = "origin", precision = 20)
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	
	@Basic()
	@Column(name = "goal", precision = 20)
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	
	@Basic()
	@Column(name = "type", precision = 20)
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Basic()
	@Column(name = "operatorId", precision = 20)
	public long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}
	
	@Basic()
	@Column(name = "createDate", precision = 200)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Basic()
	@Column(name = "description", precision = 200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
