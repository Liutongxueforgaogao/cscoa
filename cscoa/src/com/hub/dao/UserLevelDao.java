package com.hub.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hub.entity.UserLevel;

/**
 * 用户DAO接口，该接口主要作用是查询用户的账号密码
 * @author hub
 *
 */
@Repository
public interface UserLevelDao {

	//根据输入的账号密码，封装成对象后作为参数传递进来，查询在数据库s_employee中的个数
	//select COUNT(empname) from s_employee where empid = '00101' and password = '00101'
	int getCountByMessage(UserLevel userLevel);
	void setPassword(String username , String password);
	
}
