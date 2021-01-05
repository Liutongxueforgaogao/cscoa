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
public interface TrafficAuditQueryDao {
	//���뽨��
	void insertSuggestTraffic(String checkid,String date,String shr,String message,String message1);
	AuditLevelQuery getAuditLevelQueryTraffic(String stepremarks , String systemcode);
	//����״̬,��ز���
	void disagreeTraffic(String userLevel,String systemcode);
	//����״̬,ͬ�����
	void agreeTraffic(String userLevel,String systemcode);
	//��ȡ������Լ�����
	ArrayList<UserAndSuggest> getAllPeopleSuggestTraffic(String systemcode);
	//��������ˣ���ȡ��ǰ������״̬
	String getStatusTraffic(String userLevel,String systemcode);
}
