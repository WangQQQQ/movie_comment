package com.wq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wq.engine.MovieCollectEngine;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
@Controller
@RequestMapping(value = "/movieDescCrawler")
public class MovieCrawlerController {

	@Autowired
	MovieCollectEngine movieCollectEngine;
	
	@ResponseBody
	@RequestMapping(value = "/start", produces = { "application/json;charset=UTF-8" })
	public String startCraw() throws Exception {
		movieCollectEngine.start();
		return "success";
	}

}
