package com.wq.service;

import java.sql.SQLException;

import com.wq.model.UserComments;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
public interface UserCommentsService {
	
	int addUserComments(UserComments userComments) throws SQLException;
}
