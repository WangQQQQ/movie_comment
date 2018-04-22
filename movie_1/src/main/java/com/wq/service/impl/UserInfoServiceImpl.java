package com.wq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wq.mapper.UserInfoMapper;
import com.wq.model.UserInfo;
import com.wq.service.UserInfoService;

@Service(value = "movieService")
public class UserInfoServiceImpl implements UserInfoService {

	
	@Autowired
	UserInfoMapper userInfoMapper;
	
	@Override
	public int adadUserInfo(UserInfo userInfo) {
		return userInfoMapper.insert(userInfo);
	}

}
