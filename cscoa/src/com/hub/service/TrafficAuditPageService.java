package com.hub.service;

import java.util.List;

import com.hub.entity.AuditPage;

public interface TrafficAuditPageService {
	
	//��ͨ����
	List<AuditPage> getAllAudit(String username,int pagenum);
	//��ͨ���û�ȡδ��������
	int getAuditSize(String username);
	//��ͨ����������ѯ
	List<AuditPage> getAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
}
