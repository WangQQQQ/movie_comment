package com.wq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wq.mapper.UserCommentsMapper;
import com.wq.model.UserComments;
import com.wq.service.UserCommentsService;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
@Service(value = "userCommentsService")
public class UserCommentsServiceImpl implements UserCommentsService {

	
	@Autowired
	UserCommentsMapper userCommentsMapper;
	
	@Override
	public int addUserComments(UserComments userComments) {
		return userCommentsMapper.insert(userComments);
	}

}
