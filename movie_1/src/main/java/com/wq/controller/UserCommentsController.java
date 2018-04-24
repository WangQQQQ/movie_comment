package com.wq.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wq.processor.CommentsCollectProcess;

@Controller
@RequestMapping(value = "/commentsController")
public class UserCommentsController {

	private static final Logger logger = LoggerFactory.getLogger(UserCommentsController.class);
	ExecutorService es = Executors.newSingleThreadExecutor();

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@RequestMapping(value = "/collectAll")
	@ResponseBody
	public String commentsCollect() throws Exception {
		Future f = es.submit(
				new CommentsCollectProcess("761013000", 2, sqlSessionTemplate));
		int rowCount = (int) f.get();
		return "total insert comments: " + rowCount;
	}

	@RequestMapping(value = "/collectBytvid")
	@ResponseBody
	public String commentsCollectBytvid(@RequestParam String tvid) throws Exception {
		Future f = es
				.submit(new CommentsCollectProcess(tvid, 1, sqlSessionTemplate));
		int rowCount = (int) f.get();
		return "total insert comments: " + rowCount;
	}
}
