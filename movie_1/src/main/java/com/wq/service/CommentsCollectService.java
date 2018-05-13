package com.wq.service;

import java.util.Set;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
public interface CommentsCollectService {

	public void CollectCommentsAll(Set<String> tvids);
	
	public void CollectCommentsById(String tvid);
}
