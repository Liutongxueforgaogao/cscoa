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
 * 	���ڲ��뽨��Ͳ�ѯ���еĽ���
 * @author hub
 *
 */
@Repository
public interface RTzfAuditLevelQueryDao {
	//���뽨��
	void insertSuggest(String checkid,String date,String shr,String message,String message1);
	AuditLevelQuery getAuditLevelQuery(String stepremarks , String systemcode);
	//����״̬,��ز���
	void disagree(String userLevel,String systemcode);
	//����״̬,ͬ�����
	void agree(String userLevel,String systemcode);
	//����������2
	ArrayList<UserAndSuggest> getAllPeopleSuggest(String systemcode);
	//��������ˣ���ȡ��ǰ������״̬
	String getStatus(String userLevel,String systemcode);
	
	
	//��������������[2018-01-23����]
	//�������뽨��
	void insertSuggestBatch(String checkids, String date, String username, String replyMessage, String replyMessage1);
	//��������״̬,��ز���
	void disagrees(String userLevel,String[] checkids);
	//��������״̬,ͬ�����
	void agrees(String userLevel,String[] checkids);
	
}
