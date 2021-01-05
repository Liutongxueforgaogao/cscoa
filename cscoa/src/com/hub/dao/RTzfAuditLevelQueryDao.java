package com.hub.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.hub.entity.AuditLevelQuery;
import com.hub.entity.Suggestion;
import com.hub.entity.UserAndSuggest;

/**
 * 	 stepremarks; 		[stepremarks]
	 zt;				[isdeptapproved]
	 suggestionAndTime;	[remessage]
 * 	用于插入建议和查询所有的建议
 * @author hub
 *
 */
@Repository
public interface RTzfAuditLevelQueryDao {
	//插入建议
	void insertSuggest(String checkid,String date,String shr,String message,String message1);
	AuditLevelQuery getAuditLevelQuery(String stepremarks , String systemcode);
	//更新状态,打回操作
	void disagree(String userLevel,String systemcode);
	//更新状态,同意操作
	void agree(String userLevel,String systemcode);
	//报销单输入2
	ArrayList<UserAndSuggest> getAllPeopleSuggest(String systemcode);
	//根据审核人，获取当前审批的状态
	String getStatus(String userLevel,String systemcode);
	
	
	//以下是批量操作[2018-01-23新增]
	//批量插入建议
	void insertSuggestBatch(String checkids, String date, String username, String replyMessage, String replyMessage1);
	//批量更新状态,打回操作
	void disagrees(String userLevel,String[] checkids);
	//批量更新状态,同意操作
	void agrees(String userLevel,String[] checkids);
	
}
