package com.foot.record.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.foot.record.utils.FrameworkBeansName;

@Entity
@Table(name="to_permission")
@Component(FrameworkBeansName.PERMISSION_INFO_MODEL_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class Permission implements Serializable{
	private Long id;
	private String page;
	private String operate;
	private Role role;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Basic
	@Column(name="page",precision=10)
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	@Basic()
	@Column(name="operate",precision=200)
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	@ManyToOne
	@JoinColumn(name="role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
