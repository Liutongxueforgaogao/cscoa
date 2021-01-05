package com.hub.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hub.entity.AuditPage;

/**
 * 付款验收单的DAO，用户在数据库中查找出所有的付款验收单
 * 【修改】2018-01-11:表rtm001和表taskshd之中间采用checkid=systemcode内连接的方式联系
 * @author hub
 *
 */
@Repository
public interface SdzfAuditPageDao {
	//根据用户名，页码数获取所有的
	List<AuditPage> getAllAudit(@Param("username")String username,@Param("pagenum")int pagenum);
	void updateStatus(int id,String shr,String approvedtime);
	//单部门根据条件查询
	List<AuditPage> getAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
	//查询单部门未审核的个数
	int getAuditSize(String username);
}
