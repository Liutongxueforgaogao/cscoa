package com.hub.service;

import java.util.List;

import com.hub.entity.AuditPage;

/**
 * 多部门审批页面:
 * @author winv87
 *
 */
public interface MultiAuditPageService {
	//多部门
	List<AuditPage> getAllAudit(String username,int pagenum);
	//多部门获取未审批个数
	int getAuditSize(String username);
	//多部门条件查询
	List<AuditPage> getAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
}
