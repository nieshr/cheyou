package com.ynyes.cheyou.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class TdManagerPermissionList {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	 // 角色权限
    @OneToMany
    @JoinColumn(name="roleId")
    private List<TdManagerPermission> permissionlist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TdManagerPermission> getPermissionlist() {
		return permissionlist;
	}

	public void setPermissionlist(List<TdManagerPermission> permissionlist) {
		this.permissionlist = permissionlist;
	}
    
    
}
