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
public interface SignAuditQueryDao {
	//���뽨��
	void insertSuggestSign(String checkid,String date,String shr,String message,String message1);
	AuditLevelQuery getAuditLevelQuerySign(String stepremarks , String systemcode);
	//����״̬,��ز���
	void disagreeSign(String userLevel,String systemcode);
	//����״̬,ͬ�����
	void agreeSign(String userLevel,String systemcode);
	//��ȡ������Լ�����
	ArrayList<UserAndSuggest> getAllPeopleSuggestSign(String systemcode);
	//��������ˣ���ȡ��ǰ������״̬
	String getStatusSign(String userLevel,String systemcode);
}
