package com.drive.googledriveapi.entity;

import org.springframework.beans.factory.annotation.Value;

public class UserDetailsEntity {
	
	
	private Integer id;
	
	@Value("${uasername}")
	private String userName;
	@Value("${password}")
	private String passWord;
	private String role;
	@Value("${fullname}")
	private String fullName;
	private String hiddenField;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getHiddenField() {
		return hiddenField;
	}
	public void setHiddenField(String hiddenField) {
		this.hiddenField = hiddenField;
	}
	@Override
	public String toString() {
		return "UserDetailsEntity [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", role=" + role
				+ ", fullName=" + fullName + ", hiddenField=" + hiddenField + "]";
	}
	
	
	
	
	
	
	
	
	

}
