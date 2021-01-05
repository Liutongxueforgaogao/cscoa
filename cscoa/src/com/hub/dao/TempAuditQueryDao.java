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
public interface TempAuditQueryDao {
	//���뽨��
	void insertSuggestTemp(String checkid,String date,String shr,String message,String message1);
	AuditLevelQuery getAuditLevelQueryTemp(String stepremarks , String systemcode);
	//����״̬,��ز���
	void disagreeTemp(String userLevel,String systemcode);
	//����״̬,ͬ�����
	void agreeTemp(String userLevel,String systemcode);
	//��ȡ������Լ�����
	ArrayList<UserAndSuggest> getAllPeopleSuggestTemp(String systemcode);
	//��������ˣ���ȡ��ǰ������״̬
	String getStatusTemp(String userLevel,String systemcode);
}
