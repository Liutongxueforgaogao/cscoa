package com.hub.entity;

/**
 * 用户对象：该对象有用户名，密码，邮箱三个属性
 * 在数据库中，体现在s_employee表中
 * @author hub
 *
 */
public class UserLevel {
	private String username;//用户名
	private String password;//密码
	
	public UserLevel(){
			
	}
	public UserLevel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserLevel [username=" + username + ", password=" + password
				+ "]";
	}
	
}
