package com.wq.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wq.crawler4j.controller.MyCrawlController;
import com.wq.crawler4j.crawler.MovieCrawler;
import com.wq.service.MovieService;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
@Component
public class MovieCollectEngine{
	
	private static final Logger logger = LoggerFactory.getLogger(MovieCollectEngine.class);

	@Autowired
	private MovieService movieService;
	
	public void start() throws Exception {
		/*
		 * crawlStorageFolder is a folder where intermediate crawl data is
		 * stored.
		 */
		CrawlConfig config1 = new CrawlConfig();
		CrawlConfig config2 = new CrawlConfig();

		/*
		 * The two crawlers should have different storage folders for their
		 * intermediate data
		 */
		config1.setCrawlStorageFolder("crawler1" + "/crawler1");
		config2.setCrawlStorageFolder("crawler2" + "/crawler2");

		config1.setPolitenessDelay(1000);
		config2.setPolitenessDelay(2000);

		config1.setMaxPagesToFetch(50);
		config2.setMaxPagesToFetch(100);
		
		config1.setIncludeHttpsPages(true);
		config2.setIncludeHttpsPages(true);

		/*
		 * We will use different PageFetchers for the two crawlers.
		 */
		PageFetcher pageFetcher1 = new PageFetcher(config1);
		PageFetcher pageFetcher2 = new PageFetcher(config2);

		/*
		 * We will use the same RobotstxtServer for both of the crawlers.
		 */
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher1);

		MyCrawlController controller1 = new MyCrawlController(config1, pageFetcher1, robotstxtServer, movieService);
		MyCrawlController controller2 = new MyCrawlController(config2, pageFetcher2, robotstxtServer, movieService);

		String[] crawler1Domains = { "http://list.iqiyi.com/" };

		controller1.setCustomData(crawler1Domains);
		controller2.setCustomData(crawler1Domains);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		for (int i = 1; i <= 17; i++) {
			controller1.addSeed("http://list.iqiyi.com/www/1/-----------2018--11-" + i + "-1-iqiyi--.html");
		}
		for (int i = 1; i <= 30; i++) {
//			controller1.addSeed("http://list.iqiyi.com/www/1/-----------2017--11-" + i + "-1-iqiyi--.html");
//			controller1.addSeed("http://list.iqiyi.com/www/1/-----------2016--11-" + i + "-1-iqiyi--.html");
//			controller1.addSeed("http://list.iqiyi.com/www/1/-----------2011_2015--11-" + i + "-1-iqiyi--.html");
//			controller2.addSeed("http://list.iqiyi.com/www/1/-----------2000_2010--11-" + i + "-1-iqiyi--.html");
//			controller2.addSeed("http://list.iqiyi.com/www/1/-----------1990_1999--11-" + i + "-1-iqiyi--.html");
//			controller2.addSeed("http://list.iqiyi.com/www/1/-----------1980_1989--11-" + i + "-1-iqiyi--.html");
		}
		for (int i = 1; i <= 16; i++) {
//			controller2.addSeed("http://list.iqiyi.com/www/1/-----------1964_1979--11-" + i + "-1-iqiyi--.html");
		}

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		logger.info("Crawler1 Seeds counts: " + controller1.getDocIdServer().getDocCount());
		logger.info("Crawler2 Seeds counts: " + controller2.getDocIdServer().getDocCount());

		controller1.startNonBlocking(MovieCrawler.class, 3);
		controller2.startNonBlocking(MovieCrawler.class, 2);

		controller1.waitUntilFinish();
		logger.info("Crawler 1 is fincished.");

		controller2.waitUntilFinish();
		logger.info("Crawler 2 is finished.");
	}

}
