package com.wq.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wq.mapper.MovieDescMapper;
import com.wq.model.MovieDesc;
import com.wq.service.MovieService;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	MovieDescMapper movieDescMapper;
	
	@Override
	public int addMovieDesc(MovieDesc movie) {
		return movieDescMapper.insert(movie);
	}

	@Override
	public Set<String> selectAllTvid() {
		return movieDescMapper.selectAllTvid();
	}

}
