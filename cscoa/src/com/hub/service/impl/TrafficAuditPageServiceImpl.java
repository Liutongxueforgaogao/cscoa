package com.hub.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.AuditPageDao;
import com.hub.dao.TrafficAuditPageDao;
import com.hub.entity.AuditPage;
import com.hub.service.AuditPageService;

@Service("TrafficAuditPageServiceImpl")
public class TrafficAuditPageServiceImpl implements AuditPageService {

	@Autowired(required = false) 
	private TrafficAuditPageDao trafficAuditPageDao;
	
	public List<AuditPage> getAllAudit(String username,int pagenum) {
		List<AuditPage> allAudit = trafficAuditPageDao.getAllTrafficAudit(username,pagenum);
		return allAudit;
	}	
	 public int getAuditSize(String username){
		 int auditSize = trafficAuditPageDao.getTrafficAuditSize(username);
		 return auditSize;
	 }
	
	public List<AuditPage> getAuditBycondition(String username, String pddh, String pdrqstart,String pdrqend, String pdzt) {
		// TODO Auto-generated method stub
		List<AuditPage> auditResult = trafficAuditPageDao.getTrafficAuditBycondition(username, pddh, pdrqstart,pdrqend, pdzt);
		return auditResult;
	}
}
