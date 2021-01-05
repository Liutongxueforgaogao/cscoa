package com.hub.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hub.entity.AuditPage;

/**
 * 审批页面的DAO，用户在数据库中查找出所有的审批单，输出为AuditPage
 * 【待修改】2017-3-17:当前由于数据库中，表exm001和表taskshd之间的联系还没有建立起来，表之间的外键查询方式暂时不可用，待以后修改
 * 【修改】2017-04-08:表exm001和表taskshd之中间采用checkid=systemcode内连接的方式联系
 * @author hub
 *
 */
@Repository
public interface MultiAuditPageDao {
	List<AuditPage> getAllMultiAudit(@Param("username")String username,@Param("pagenum")int pagenum);
	//void updateStatus(int id,String shr,String approvedtime);
	//多部门根据条件查询
	List<AuditPage> getMultiAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
	//获取多部门未审核个数
	int getMultiAuditSize(String username);
}
