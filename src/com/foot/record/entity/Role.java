package com.foot.record.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.foot.record.utils.FrameworkBeansName;

@Entity
@Table(name = "to_role")
@Component(FrameworkBeansName.ROLE_INFO_MODEL_BEAN)
@Scope(FrameworkBeansName.BEAN_PROTOTYPE)
public class Role implements Serializable{
	private Long id;
	private String name;
	private List<User> users;
	private List<Permission> permissions;
	private String description;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 20)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Basic()
	@Column(name = "name", precision = 40)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Basic()
	@Column(name = "description", precision = 100)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@OneToMany(mappedBy="role",cascade = { javax.persistence.CascadeType.REMOVE })
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@OneToMany(mappedBy="role",cascade = { javax.persistence.CascadeType.REMOVE })
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
}
