package com.hub.service;

import java.util.ArrayList;

import com.hub.entity.AuditLevelQuery;
import com.hub.entity.Suggestion;
import com.hub.entity.UserAndSuggest;


public interface AuditLevelQueryService {
	//���뽨��
	void insertSuggest(String checkid,String shr,String message,String message1);
	//δ֪����ʱû���ô�
	AuditLevelQuery getAuditLevelQuery(String stepremarks , String systemcode);
	//��ȡ���еĽ���
	Suggestion getSuggest(String stepremarks , String systemcode);
	//����״̬,��ز���
	void disagree(String userLevel,String systemcode);
	//����״̬,ͬ�����
	void agree(String userLevel,String systemcode);
	//ȡ���������������˵����������Ϣ������
	ArrayList<UserAndSuggest> getAllPeopleSuggest(String systemcode);
	//�鿴״̬
	String getStatus(String username,String systemcode);
}
