package com.hub.service;

import java.util.List;

import com.hub.entity.AuditPage;

public interface TravelAuditPageService {
	
	//暂付款费用
	List<AuditPage> getAllAudit(String username,int pagenum);
	//暂付款费用获取未审批个数
	int getAuditSize(String username);
	//暂付款费用条件查询
	List<AuditPage> getAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
}
