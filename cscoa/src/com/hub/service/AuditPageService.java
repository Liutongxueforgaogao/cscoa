package com.hub.service;

import java.util.Collection;
import java.util.List;

import com.hub.entity.AuditPage;

public interface AuditPageService {
	//������
	List<AuditPage> getAllAudit(String username,int pagenum);
	//�����Ż�ȡδ��������
	int getAuditSize(String username);
	//������������ѯ
	List<AuditPage> getAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
}
