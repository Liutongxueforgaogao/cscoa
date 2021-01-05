package com.hub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.SdjkAuditPageDao;
import com.hub.entity.AuditPage;
import com.hub.service.AuditPageService;

@Service("SdjkAuditPageServiceImpl")
public class SdjkAuditPageServiceImpl implements AuditPageService {

	@Autowired(required = false) 
	private SdjkAuditPageDao sdjkAuditPageDao;
	
	public List<AuditPage> getAllAudit(String username,int pagenum) {
		List<AuditPage> allAudit = sdjkAuditPageDao.getAllAudit(username,pagenum);
		return allAudit;
	}	
	 public int getAuditSize(String username){
		 int auditSize = sdjkAuditPageDao.getAuditSize(username);
		 return auditSize;
	 }
	 //��ȡ�ಿ��δ��˸���
	 public int getMultiAuditSize(String username){
		 
		 return 0;
	 }
	 //��ͨδ��˸���
	 public int getTrafficAuditSize(String username){
		 
		 return 0;
	 }
	 //��ȡ����δ��˸���
	 public int getTravelAuditSize(String username){
		 
		 return 0;
	 }
	 //��ȡ�ݸ���δ��˸���
	 public int getTempAuditSize(String username){
		 
		 return 0;
	 }
	
	public List<AuditPage> getAuditBycondition(String username, String pddh, String pdrqstart,String pdrqend, String pdzt) {
		// TODO Auto-generated method stub
		List<AuditPage> auditResult = sdjkAuditPageDao.getAuditBycondition(username, pddh, pdrqstart,pdrqend, pdzt);
		return auditResult;
	}
	
}
