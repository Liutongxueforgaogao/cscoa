package com.hub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.ZjzfAuditPageDao;
import com.hub.entity.AuditPage;
import com.hub.service.AuditPageService;

@Service("ZjzfAuditPageServiceImpl")
public class ZjzfAuditPageServiceImpl implements AuditPageService {

	@Autowired(required = false) 
	private ZjzfAuditPageDao zjzfAuditPageDao;
	
	public List<AuditPage> getAllAudit(String username,int pagenum) {
		List<AuditPage> allAudit = zjzfAuditPageDao.getAllAudit(username,pagenum);
		return allAudit;
	}	
	 public int getAuditSize(String username){
		 int auditSize = zjzfAuditPageDao.getAuditSize(username);
		 return auditSize;
	 }
	 //获取多部门未审核个数
	 public int getMultiAuditSize(String username){
		 
		 return 0;
	 }
	 //获交通未审核个数
	 public int getTrafficAuditSize(String username){
		 
		 return 0;
	 }
	 //获取差旅未审核个数
	 public int getTravelAuditSize(String username){
		 
		 return 0;
	 }
	 //获取暂付款未审核个数
	 public int getTempAuditSize(String username){
		 
		 return 0;
	 }
	
	public List<AuditPage> getAuditBycondition(String username, String pddh, String pdrqstart,String pdrqend, String pdzt) {
		// TODO Auto-generated method stub
		List<AuditPage> auditResult = zjzfAuditPageDao.getAuditBycondition(username, pddh, pdrqstart,pdrqend, pdzt);
		return auditResult;
	}
	
}
