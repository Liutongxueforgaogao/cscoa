package com.hub.service;

import java.util.Collection;
import java.util.List;

import com.hub.entity.AuditPage;

public interface AuditPageService {
	//单部门
	List<AuditPage> getAllAudit(String username,int pagenum);
	//单部门获取未审批个数
	int getAuditSize(String username);
	//单部门条件查询
	List<AuditPage> getAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
}
