package com.wq.processor;

import java.net.HttpURLConnection;

import org.mybatis.spring.SqlSessionTemplate;

public abstract class BaseProcess{

	//aiqiyi side movie id
	protected String tvid;
	
	protected SqlSessionTemplate sqlSessionTemplate;
	
	public BaseProcess(String tvid, SqlSessionTemplate sqlSessionTemplate) {
		this.tvid=tvid;
	}
	protected abstract void init();
	
	protected abstract String process(HttpURLConnection urlConnList);
	
	protected abstract void finish();
}
