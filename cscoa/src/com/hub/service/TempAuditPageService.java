package com.hub.service;

import java.util.List;

import com.hub.entity.AuditPage;

public interface TempAuditPageService {
	
	//差旅费用
	List<AuditPage> getAllAudit(String username,int pagenum);
	//差旅费用获取未审批个数
	int getAuditSize(String username);
	//差旅费用条件查询
	List<AuditPage> getAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
}
