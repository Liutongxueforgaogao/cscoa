package com.hub.service;

import java.util.List;

import com.hub.entity.AuditPage;

/**
 * �ಿ������ҳ��:
 * @author winv87
 *
 */
public interface MultiAuditPageService {
	//�ಿ��
	List<AuditPage> getAllAudit(String username,int pagenum);
	//�ಿ�Ż�ȡδ��������
	int getAuditSize(String username);
	//�ಿ��������ѯ
	List<AuditPage> getAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
}
