package com.hub.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.hub.entity.AuditLevelQuery;
import com.hub.entity.Suggestion;
import com.hub.entity.UserAndSuggest;

/**
 * 交通费插入意见，打回操作，同意操作，获取审核人以及意见，获取审核状态
 * 	 stepremarks; 		[stepremarks]
	 zt;				[isdeptapproved]
	 suggestionAndTime;	[remessage]
 * 	用于插入建议和查询所有的建议
 * @author hub
 *
 */
@Repository
public interface TrafficAuditQueryDao {
	//插入建议
	void insertSuggestTraffic(String checkid,String date,String shr,String message,String message1);
	AuditLevelQuery getAuditLevelQueryTraffic(String stepremarks , String systemcode);
	//更新状态,打回操作
	void disagreeTraffic(String userLevel,String systemcode);
	//更新状态,同意操作
	void agreeTraffic(String userLevel,String systemcode);
	//获取审核人以及建议
	ArrayList<UserAndSuggest> getAllPeopleSuggestTraffic(String systemcode);
	//根据审核人，获取当前审批的状态
	String getStatusTraffic(String userLevel,String systemcode);
}
