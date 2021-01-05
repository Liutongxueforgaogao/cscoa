package com.hub.entity;

import java.util.List;


/**
 * 等级用户审核显示对象，对象包括审核人，等级审核人的审核的状态，建议以及对应的时间
 * @author hub
 *
 */
public class AuditLevelQuery {

	private int id;
	private String stepremarks;
	private String zt;
	private String suggestionAndTime;
	public AuditLevelQuery() {
		super();
	}

	@Override
	public String toString() {
		return "AuditLevelQuery [id=" + id + ", stepremarks=" + stepremarks
				+ ", zt=" + zt + ", suggestionAndTime=" + suggestionAndTime
				+ "]";
	}




	public String getSuggestionAndTime() {
		return suggestionAndTime;
	}

	public void setSuggestionAndTime(String suggestionAndTime) {
		this.suggestionAndTime = suggestionAndTime;
	}

	public AuditLevelQuery(int id, String stepremarks, String zt,
			String suggestionAndTime) {
		super();
		this.id = id;
		this.stepremarks = stepremarks;
		this.zt = zt;
		this.suggestionAndTime = suggestionAndTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getStepremarks() {
		return stepremarks;
	}
	public void setStepremarks(String stepremarks) {
		this.stepremarks = stepremarks;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}

	
	
}
