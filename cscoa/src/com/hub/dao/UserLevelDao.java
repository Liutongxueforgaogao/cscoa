package com.hub.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hub.entity.UserLevel;

/**
 * �û�DAO�ӿڣ��ýӿ���Ҫ�����ǲ�ѯ�û����˺�����
 * @author hub
 *
 */
@Repository
public interface UserLevelDao {

	//����������˺����룬��װ�ɶ������Ϊ�������ݽ�������ѯ�����ݿ�s_employee�еĸ���
	//select COUNT(empname) from s_employee where empid = '00101' and password = '00101'
	int getCountByMessage(UserLevel userLevel);
	void setPassword(String username , String password);
	
}
