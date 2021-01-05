package com.hub.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hub.entity.AuditPage;

/**
 * ����ҳ���DAO���û������ݿ��в��ҳ����е������������ΪAuditPage
 * �����޸ġ�2017-3-17:��ǰ�������ݿ��У���exm001�ͱ�taskshd֮�����ϵ��û�н�����������֮��������ѯ��ʽ��ʱ�����ã����Ժ��޸�
 * ���޸ġ�2017-04-08:��exm001�ͱ�taskshd֮�м����checkid=systemcode�����ӵķ�ʽ��ϵ
 * @author hub
 *
 */
@Repository
public interface MultiAuditPageDao {
	List<AuditPage> getAllMultiAudit(@Param("username")String username,@Param("pagenum")int pagenum);
	//void updateStatus(int id,String shr,String approvedtime);
	//�ಿ�Ÿ���������ѯ
	List<AuditPage> getMultiAuditBycondition(String username,String pddh,String pdrqstart,String pdrqend,String pdzt);
	//��ȡ�ಿ��δ��˸���
	int getMultiAuditSize(String username);
}
