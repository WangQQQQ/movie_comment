package com.wq.processor;

import java.net.HttpURLConnection;

public abstract class BaseProcess{

	//aiqiyi side movie id
	protected String tvid;
	
	protected int pageCount;
	
	protected HttpURLConnection urlConn;
	
	public BaseProcess(String tvid, int pageCount) {
		this.tvid=tvid;
		this.pageCount=pageCount;
	}
	protected abstract void init();
	
	protected abstract void process();
	
	protected abstract void finish();
}
