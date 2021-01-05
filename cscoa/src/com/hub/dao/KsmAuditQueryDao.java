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
public interface KsmAuditQueryDao {
	//���뽨��
	void insertSuggestKsm(String checkid,String date,String shr,String message,String message1);
	AuditLevelQuery getAuditLevelQueryKsm(String stepremarks , String systemcode);
	//����״̬,��ز���
	void disagreeKsm(String userLevel,String systemcode);
	//����״̬,ͬ�����
	void agreeKsm(String userLevel,String systemcode);
	//��ȡ������Լ�����
	ArrayList<UserAndSuggest> getAllPeopleSuggestKsm(String systemcode);
	//��������ˣ���ȡ��ǰ������״̬
	String getStatusKsm(String userLevel,String systemcode);
}
