package com.wq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wq.mapper.UserMapper;
import com.wq.model.User;
import com.wq.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public int addUser(User user) {
		return userMapper.insert(user);
	}

}
