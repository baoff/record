package com.foot.record.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.foot.record.utils.FrameworkBeansName;

@Entity()
@Table(name = "to_user")
@Component(FrameworkBeansName.USER_INFO_MODEL_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class User implements Serializable{
	private Long id;
	private String loginName;
	private String nickname;
	private String password;
	private String email;
	private String phoneNum;
	private String orgName;
	private Date regesiterTime;
	private Date lastLoginTime;
	private Role role;
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
	@Column(name = "loginName", precision = 200)
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Basic()
	@Column(name = "NickName", precision = 200)
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Basic()
	@Column(name = "Password", precision = 200)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Basic()
	@Column(name = "Email", precision = 200)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Basic()
	@Column(name = "phoneNum", precision = 20)
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Basic()
	@Column(name = "RegesiterTime", precision = 200)
	public Date getRegesiterTime() {
		return regesiterTime;
	}
	public void setRegesiterTime(Date regesiterTime) {
		this.regesiterTime = regesiterTime;
	}
	@Basic()
	@Column(name = "LastLoginTime", precision = 200)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Basic()
	@Column(name = "orgName", precision = 200)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@ManyToOne
    @JoinColumn(name="role_id" )
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
