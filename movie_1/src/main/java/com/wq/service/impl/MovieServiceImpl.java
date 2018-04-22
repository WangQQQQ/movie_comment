package com.wq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wq.mapper.MovieDescMapper;
import com.wq.model.MovieDesc;
import com.wq.service.MovieService;

@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {

	
	@Autowired
	MovieDescMapper movieDescMapper;
	
	@Override
	public int addMovieDesc(MovieDesc movie) {
		return movieDescMapper.insert(movie);
	}

}
