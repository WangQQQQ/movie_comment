package com.wq.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wq.processor.CommentsCollectProcess;
import com.wq.service.MovieService;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
@Component
public class CommentsCollectEngine{

	private static final Logger logger = LoggerFactory.getLogger(CommentsCollectEngine.class);
	ExecutorService es = Executors.newSingleThreadExecutor();
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int CollectCommentsAll() {
		Set<String> allTvids = movieService.selectAllTvid();
		int rowCounts = 0;
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		for (String tvid : allTvids) {
			Future<Integer> f = es
					.submit(new CommentsCollectProcess(tvid, sqlSessionTemplate));
			futures.add(f);
			try {
				for (Future<Integer> future : futures) {
					rowCounts = rowCounts + (int) future.get();
				}
			} catch (InterruptedException e) {
				logger.error("Thread been interrupted when collect conmment all");
				e.printStackTrace();
			} catch (ExecutionException e) {
				logger.error("Execution error when collect conmment all");
				e.printStackTrace();
			} finally {
//				es.shutdown();
			}
		}
		return rowCounts;
	}

	public int CollectCommentsById(String tvid) {
		Future<Integer> f = es
				.submit(new CommentsCollectProcess(tvid, sqlSessionTemplate));
		int rowCounts = 0;
		try {
			rowCounts = (int) f.get();
		} catch (InterruptedException e) {
			logger.error("Thread been interrupted when collect conmment by id");
			e.printStackTrace();
		} catch (ExecutionException e) {
			logger.error("Execution error when collect conmment by id");
			e.printStackTrace();
		} finally {
//			es.shutdown();
		}
		return rowCounts;
	}
}
