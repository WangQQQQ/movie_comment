package com.wq.processor;

import java.net.HttpURLConnection;

import org.mybatis.spring.SqlSessionTemplate;

public abstract class BaseProcess{

	//aiqiyi side movie id
	protected String tvid;
	
	protected int pageCount;
	
	protected HttpURLConnection urlConn;
	
	protected SqlSessionTemplate sqlSessionTemplate;
	
	public BaseProcess(String tvid, int pageCount, SqlSessionTemplate sqlSessionTemplate) {
		this.tvid=tvid;
		this.pageCount=pageCount;
	}
	protected abstract void init();
	
	protected abstract void process();
	
	protected abstract void finish();
}
