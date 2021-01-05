package com.hub.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.AuditPageDao;
import com.hub.dao.RTyanshouAuditPageDao;
import com.hub.dao.RTzfAuditPageDao;
import com.hub.entity.AuditPage;
import com.hub.service.AuditPageService;

@Service("RTzfAuditPageServiceImpl")
public class RTzfAuditPageServiceImpl implements AuditPageService {

	@Autowired(required = false) 
	private RTzfAuditPageDao rTzfAuditPageDao;
	
	public List<AuditPage> getAllAudit(String username,int pagenum) {
		List<AuditPage> allAudit = rTzfAuditPageDao.getAllAudit(username,pagenum);
		return allAudit;
	}	
	 public int getAuditSize(String username){
		 int auditSize = rTzfAuditPageDao.getAuditSize(username);
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
		List<AuditPage> auditResult = rTzfAuditPageDao.getAuditBycondition(username, pddh, pdrqstart,pdrqend, pdzt);
		return auditResult;
	}
	
}
