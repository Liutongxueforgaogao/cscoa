package com.hub.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hub.entity.AuditPage;

/**
 * �������յ���DAO���û������ݿ��в��ҳ����еĸ������յ�
 * ���޸ġ�2018-01-11:��rtm001�ͱ�taskshd֮�м����checkid=systemcode�����ӵķ�ʽ��ϵ
 * @author hub
 *
 */
@Repository
public interface SdzfAuditPageDao {
	//�����û�����ҳ������ȡ���е�
	List<AuditPage> getAllAudit(@Param("username")String username,@Param("pagenum")int pagenum);
	void updateStatus(int id,String shr,String approvedtime);
	//�����Ÿ���������ѯ
	List<AuditPage> getAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
	//��ѯ������δ��˵ĸ���
	int getAuditSize(String username);
}
