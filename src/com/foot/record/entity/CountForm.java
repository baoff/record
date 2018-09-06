package com.foot.record.entity;

public class CountForm {
	private String department;
	private String region;
	private int customerType;
	private String startDate;
	private String endDate;
	private String wxNumber;
	private int status=-1 ;
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
	public int getCustomerType() {
		return customerType;
	}
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWxNumber() {
		return wxNumber;
	}
	public void setWxNumber(String wxNumber) {
		this.wxNumber = wxNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
