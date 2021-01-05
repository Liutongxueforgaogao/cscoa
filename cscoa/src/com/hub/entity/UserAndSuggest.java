package com.hub.entity;

public class UserAndSuggest {

	private String user;
	private String suggest;
	
	public UserAndSuggest() {
		super();
	}

	public UserAndSuggest(String user, String suggest) {
		super();
		this.user = user;
		this.suggest = suggest;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	@Override
	public String toString() {
		return "UserAndSuggest [user=" + user + ", suggest=" + suggest + "]";
	}
	
}
