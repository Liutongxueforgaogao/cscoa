package com.hub.entity;

/**
 * �û����󣺸ö������û��������룬������������
 * �����ݿ��У�������s_employee����
 * @author hub
 *
 */
public class UserLevel {
	private String username;//�û���
	private String password;//����
	
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
