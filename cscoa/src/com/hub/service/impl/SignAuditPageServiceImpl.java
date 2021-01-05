package com.hub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.SignAuditPageDao;
import com.hub.entity.SignPage;

@Service("SignAuditPageServiceImpl")
public class SignAuditPageServiceImpl {

	@Autowired(required = false) 
	private SignAuditPageDao signAuditPageDao;
	
	public List<SignPage> getAllAudit(String username,int pagenum){
		List<SignPage> allAudit = signAuditPageDao.getSignAllAudit(username,pagenum);
		return allAudit;
	}
	 public int getAuditSize(String username){
		 int auditSize = signAuditPageDao.getSignAuditSize(username);
		 return auditSize;
	 }
	
	public List<SignPage> getAuditBycondition(String username, String pddh, String pdrqstart,String pdrqend, String pdzt) {
		// TODO Auto-generated method stub
		List<SignPage> auditResult = signAuditPageDao.getSignAuditBycondition(username, pddh, pdrqstart,pdrqend, pdzt);
		return auditResult;
	}
}
