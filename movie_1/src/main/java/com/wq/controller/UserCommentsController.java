package com.wq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wq.service.UserCommentsService;

@Controller
@RequestMapping(value = "/commentsController")
public class UserCommentsController {

	private static final Logger logger = LoggerFactory.getLogger(UserCommentsController.class);

	@Autowired
	private UserCommentsService userCommentsService;
	
	@RequestMapping(value="/collectAll")
	public void commentsCollect(){
		
	}
	
	@RequestMapping(value="/collectBytvid")
	public void commentsCollectBytvid(String tvid){
		
	}
}
