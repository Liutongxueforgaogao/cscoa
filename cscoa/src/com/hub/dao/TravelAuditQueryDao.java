package com.hub.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.hub.entity.AuditLevelQuery;
import com.hub.entity.Suggestion;
import com.hub.entity.UserAndSuggest;

/**
 * ��ͨ�Ѳ����������ز�����ͬ���������ȡ������Լ��������ȡ���״̬
 * 	 stepremarks; 		[stepremarks]
	 zt;				[isdeptapproved]
	 suggestionAndTime;	[remessage]
 * 	���ڲ��뽨��Ͳ�ѯ���еĽ���
 * @author hub
 *
 */
@Repository
public interface TravelAuditQueryDao {
	//���뽨��
	void insertSuggestTravel(String checkid,String date,String shr,String message,String message1);
	AuditLevelQuery getAuditLevelQueryTravel(String stepremarks , String systemcode);
	//����״̬,��ز���
	void disagreeTravel(String userLevel,String systemcode);
	//����״̬,ͬ�����
	void agreeTravel(String userLevel,String systemcode);
	//��ȡ������Լ�����
	ArrayList<UserAndSuggest> getAllPeopleSuggestTravel(String systemcode);
	//��������ˣ���ȡ��ǰ������״̬
	String getStatusTravel(String userLevel,String systemcode);
}
