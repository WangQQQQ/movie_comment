package com.wq.service;

import java.sql.SQLException;

import com.wq.model.UserInfo;

public interface UserInfoService {
	
	int addUserInfo(UserInfo userInfo) throws SQLException;
}
