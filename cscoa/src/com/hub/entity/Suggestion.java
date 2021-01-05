package com.hub.entity;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

public class Suggestion {

	private String userLevelname;
	private ArrayList<String> suggest;
	public Suggestion() {
		super();
	}

	public Suggestion(String userLevelname, ArrayList<String> suggest) {
		super();
		this.userLevelname = userLevelname;
		this.suggest = suggest;
	}

	public String getUserLevelname() {
		return userLevelname;
	}
	public void setUserLevelname(String userLevelname) {
		this.userLevelname = userLevelname;
	}

	public ArrayList<String> getSuggest() {
		return suggest;
	}

	public void setSuggest(ArrayList<String> suggest) {
		this.suggest = suggest;
	}


	@Override
	public String toString() {
		return "Suggestion [userLevelname=" + userLevelname + ", suggest="
				+ suggest +"]";
	}
	
	
}
