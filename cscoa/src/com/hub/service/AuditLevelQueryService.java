package com.hub.service;

import java.util.ArrayList;

import com.hub.entity.AuditLevelQuery;
import com.hub.entity.Suggestion;
import com.hub.entity.UserAndSuggest;


public interface AuditLevelQueryService {
	//插入建议
	void insertSuggest(String checkid,String shr,String message,String message1);
	//未知，暂时没有用处
	AuditLevelQuery getAuditLevelQuery(String stepremarks , String systemcode);
	//获取所有的建议
	Suggestion getSuggest(String stepremarks , String systemcode);
	//更新状态,打回操作
	void disagree(String userLevel,String systemcode);
	//更新状态,同意操作
	void agree(String userLevel,String systemcode);
	//取出这个级别的所有人的流程审核信息，根据
	ArrayList<UserAndSuggest> getAllPeopleSuggest(String systemcode);
	//查看状态
	String getStatus(String username,String systemcode);
}
