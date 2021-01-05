package com.hub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.UserLevelDao;
import com.hub.entity.UserLevel;
import com.hub.service.UserService;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired()
	private UserLevelDao userLevelDao;

	public int login(UserLevel userLevel) {
//		int userCount=0;
//		try {
//			userCount = userLevelDao.getCountByMessage(userLevel);
//			System.out.println("执行了！！！！！！");
//		}catch (Exception e){
//			e.printStackTrace();
//		}
		int userCount = userLevelDao.getCountByMessage(userLevel);
		return userCount;
	}
	public void setPasswod(String username , String password){
		userLevelDao.setPassword(username, password);
	}

}
