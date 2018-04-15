package com.wq.util;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.wq.service.MovieService;

@Component
public class Utils {

	public static Utils utils;
	
//	WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//	SqlSessionFactory sf = (SqlSessionFactory) wac.getBean("sqlSessionFactory");
//	SqlSession session = sf.openSession();
	public Utils() {
	}
	
//	public static Utils getUtils(){
//		return utils;
//	}

//	@Autowired
//	MovieService movieService;
//	
//	public MovieService getMovieService(){
//		return movieService;
//	}
	
}
