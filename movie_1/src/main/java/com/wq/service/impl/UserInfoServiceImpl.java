package com.wq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wq.mapper.UserInfoMapper;
import com.wq.model.UserInfo;
import com.wq.service.UserInfoService;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	
	@Autowired
	UserInfoMapper userInfoMapper;
	
	@Override
	public int addUserInfo(UserInfo userInfo) {
		return userInfoMapper.insert(userInfo);
	}

}
