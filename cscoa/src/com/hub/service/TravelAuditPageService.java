package com.hub.service;

import java.util.List;

import com.hub.entity.AuditPage;

public interface TravelAuditPageService {
	
	//�ݸ������
	List<AuditPage> getAllAudit(String username,int pagenum);
	//�ݸ�����û�ȡδ��������
	int getAuditSize(String username);
	//�ݸ������������ѯ
	List<AuditPage> getAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
}
