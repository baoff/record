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
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.foot.record.utils.FrameworkBeansName;
/**
 * 清单
 * @author baoff
 *
 */
@Entity()
@Table(name = "to_record")
@Component(FrameworkBeansName.RECORD_INFO_MODEL_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class Record implements Serializable{
	private Long id ;
	private String department;//部门
	private String region;//区域
	private String registAddress;//注册地址
	private String postalAddress;//通讯地址
	private String contacts;//联系人
	private String street;//街道--招商人
	private String addedTax;// 增值税
	private String incomeTax;//所得税
	private String manageTax;//管理费
	private String returnTax;//返税
	private String phone;//电话
	private int customerType;// 客户类别：1,成交客户，2,商讨客户
	private Date initDate;//创建时间
	private String wxNumber;//微信号--邀约
	private String orderNumber;//订单号
	private int status;  // 1,已完成，2,注册中
	private int star;
	private long operatorId;
	private int backType;
	private String nickname;
	private Date modifyDate;// 修改时间
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 20)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Basic()
	@Column(name = "department", precision = 20)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Basic()
	@Column(name = "region", precision = 40)
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	@Basic()
	@Column(name = "registAddress", precision = 100)
	public String getRegistAddress() {
		return registAddress;
	}
	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}
	
	@Basic()
	@Column(name = "postalAddress", precision = 200)
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	
	@Basic()
	@Column(name = "contacts", precision = 400)
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	@Basic()
	@Column(name = "street",precision = 200)
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Basic()
	@Column(name = "addedTax", precision = 20)
	public String getAddedTax() {
		return addedTax;
	}
	public void setAddedTax(String addedTax) {
		this.addedTax = addedTax;
	}
	
	@Basic()
	@Column(name = "incomeTax", precision = 20)
	public String getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(String incomeTax) {
		this.incomeTax = incomeTax;
	}
	
	@Basic()
	@Column(name = "manageTax", precision = 20)
	public String getManageTax() {
		return manageTax;
	}
	public void setManageTax(String manageTax) {
		this.manageTax = manageTax;
	}
	
	@Basic()
	@Column(name = "returnTax", precision = 20)
	public String getReturnTax() {
		return returnTax;
	}
	public void setReturnTax(String returnTax) {
		this.returnTax = returnTax;
	}
	
	@Basic()
	@Column(name = "phone", precision = 20)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Basic()
	@Column(name = "customerType", precision = 2)
	public int getCustomerType() {
		return customerType;
	}
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}
	
	@Basic()
	@Column(name = "wxNumber", precision = 40)
	public String getWxNumber() {
		return wxNumber;
	}
	public void setWxNumber(String wxNumber) {
		this.wxNumber = wxNumber;
	}
	
	@Basic()
	@Column(name = "orderNumber", precision = 40)
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	@Basic()
	@Column(name = "status", precision = 2)
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Basic()
	@Column(name = "initDate", precision = 200)
	public Date getInitDate() {
		return initDate;
	}
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	@Basic()
	@Column(name="operatorId",precision=20)
	public long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}
	@Transient
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Basic()
	@Column(name = "modifyDate", precision = 200)
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Transient
	public int getBackType() {
		return backType;
	}
	public void setBackType(int backType) {
		this.backType = backType;
	}
	@Basic()
	@Column(name="star",precision=2)
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	
}
