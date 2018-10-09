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
 * �嵥
 * @author baoff
 *
 */
@Entity()
@Table(name = "to_record")
@Component(FrameworkBeansName.RECORD_INFO_MODEL_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class Record implements Serializable{
	private Long id ;
	private String department;//����
	private String region;//����
	private String registAddress;//ע���ַ
	private String postalAddress;//ͨѶ��ַ
	private String contacts;//��ϵ��
	private String street;//�ֵ�--������
	private String addedTax;// ��ֵ˰
	private String incomeTax;//����˰
	private String manageTax;//�����
	private String returnTax;//��˰
	private String phone;//�绰
	private int customerType;// �ͻ����1,�ɽ��ͻ���2,���ֿͻ�
	private Date initDate;//����ʱ��
	private String wxNumber;//΢�ź�--��Լ
	private String orderNumber;//������
	private int status;  // 1,����ɣ�2,ע����
	private int star;
	private long operatorId;
	private int backType;
	private String nickname;
	private Date modifyDate;// �޸�ʱ��
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
