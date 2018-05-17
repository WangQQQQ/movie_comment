package com.wq.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wq.engine.CommentsCollectEngine;

@Controller
@RequestMapping(value = "/commentsController")
public class UserCommentsController {

	private static final Logger logger = LoggerFactory.getLogger(UserCommentsController.class);
	ExecutorService es = Executors.newSingleThreadExecutor();
	
	@Autowired
	private CommentsCollectEngine commentsCollectEngine;
	
	
	@RequestMapping(value = "/collectAll")
	@ResponseBody
	public String commentsCollect() throws Exception {
		int rowCounts = commentsCollectEngine.CollectCommentsAll();
		return "total insert comments: " + rowCounts;
	}

	@RequestMapping(value = "/collectBytvid")
	@ResponseBody
	public String commentsCollectBytvid(@RequestParam String tvid) throws Exception {
		int rowCounts = commentsCollectEngine.CollectCommentsById(tvid);
		return "total insert comments: " + rowCounts;
	}
}
