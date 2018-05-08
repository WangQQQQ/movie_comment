package com.wq.service;

import java.util.Set;

import com.wq.model.MovieDesc;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
public interface MovieService {
	
	int addMovieDesc(MovieDesc movie);
	
	Set<String> selectAllTvid();
}
