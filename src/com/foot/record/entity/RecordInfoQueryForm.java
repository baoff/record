package com.foot.record.entity;

import java.util.Date;

public class RecordInfoQueryForm {
	
	private String department;
	private String region;
	private String registAddress;
	private String postalAddress;
	private String contacts;
	private String street;
	private String addedTax;
	private String incomeTax;
	private String manageTax;
	private String returnTax;
	private String phone;
	private int customerType=-1;
	private String initDateStart;
	private String initDateEnd;
	private int isexport;
	private String wxNumber;
	private String orderNumber;
	private int star=-1;
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	private int status =-1;
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegistAddress() {
		return registAddress;
	}
	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAddedTax() {
		return addedTax;
	}
	public void setAddedTax(String addedTax) {
		this.addedTax = addedTax;
	}
	public String getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(String incomeTax) {
		this.incomeTax = incomeTax;
	}
	public String getManageTax() {
		return manageTax;
	}
	public void setManageTax(String manageTax) {
		this.manageTax = manageTax;
	}
	public String getReturnTax() {
		return returnTax;
	}
	public void setReturnTax(String returnTax) {
		this.returnTax = returnTax;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getCustomerType() {
		return customerType;
	}
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}
	public String getInitDateStart() {
		return initDateStart;
	}
	public void setInitDateStart(String initDateStart) {
		this.initDateStart = initDateStart;
	}
	public String getInitDateEnd() {
		return initDateEnd;
	}
	public void setInitDateEnd(String initDateEnd) {
		this.initDateEnd = initDateEnd;
	}
	public int getIsexport() {
		return isexport;
	}
	public void setIsexport(int isexport) {
		this.isexport = isexport;
	}
	public String getWxNumber() {
		return wxNumber;
	}
	public void setWxNumber(String wxNumber) {
		this.wxNumber = wxNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
