package com.wq.crawler4j.controller;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wq.service.MovieService;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
public class MyCrawlController extends CrawlController {

	static final Logger logger = LoggerFactory.getLogger(MyCrawlController.class);

	private MovieService movieService;

	
	public MyCrawlController(CrawlConfig config, PageFetcher pageFetcher, RobotstxtServer robotstxtServer,
			MovieService movieService) throws Exception {
		super(config, pageFetcher, robotstxtServer);
		this.movieService = movieService;
	}

	@Override
	public <T extends WebCrawler> void startNonBlocking(Class<T> clazz, int numberOfCrawlers) {
		this.start(clazz, numberOfCrawlers, false);
	}

	public <T extends WebCrawler> void start(Class<T> clazz, final int numberOfCrawlers,
			boolean isBlocking) {

		try {
			finished = false;
			crawlersLocalData.clear();
			final List<Thread> threads = new ArrayList<>();
			final List<T> crawlers = new ArrayList<>();

			for (int i = 1; i <= numberOfCrawlers; i++) {
				Constructor<T> c = clazz.getConstructor(movieService.getClass());
				T crawler = (T) c.newInstance(movieService);
				Thread thread = new Thread(crawler, "Crawler " + i);
				crawler.setThread(thread);
				crawler.init(i, this);
				thread.start();
				crawlers.add(crawler);
				threads.add(thread);
				logger.info("Crawler {} started", i);
			}

			final CrawlController controller = this;
			final CrawlConfig config = this.getConfig();

			Thread monitorThread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						synchronized (waitingLock) {

							while (true) {
								sleep(config.getThreadMonitoringDelaySeconds());
								boolean someoneIsWorking = false;
								for (int i = 0; i < threads.size(); i++) {
									Thread thread = threads.get(i);
									if (!thread.isAlive()) {
										if (!shuttingDown) {
											logger.info("Thread {} was dead, I'll recreate it", i);
											Constructor<T> c = clazz.getConstructor(movieService.getClass());
											T crawler = (T) c.newInstance(movieService);
											thread = new Thread(crawler, "Crawler " + (i + 1));
											threads.remove(i);
											threads.add(i, thread);
											crawler.setThread(thread);
											crawler.init(i + 1, controller);
											thread.start();
											crawlers.remove(i);
											crawlers.add(i, crawler);
										}
									} else if (crawlers.get(i).isNotWaitingForNewURLs()) {
										someoneIsWorking = true;
									}
								}
								boolean shutOnEmpty = config.isShutdownOnEmptyQueue();
								if (!someoneIsWorking && shutOnEmpty) {
									// Make sure again that none of the threads
									// are
									// alive.
									logger.info("It looks like no thread is working, waiting for "
											+ config.getThreadShutdownDelaySeconds() + " seconds to make sure...");
									sleep(config.getThreadShutdownDelaySeconds());

									someoneIsWorking = false;
									for (int i = 0; i < threads.size(); i++) {
										Thread thread = threads.get(i);
										if (thread.isAlive() && crawlers.get(i).isNotWaitingForNewURLs()) {
											someoneIsWorking = true;
										}
									}
									if (!someoneIsWorking) {
										if (!shuttingDown) {
											long queueLength = frontier.getQueueLength();
											if (queueLength > 0) {
												continue;
											}
											logger.info("No thread is working and no more URLs are in "
													+ "queue waiting for another "
													+ config.getThreadShutdownDelaySeconds()
													+ " seconds to make sure...");
											sleep(config.getThreadShutdownDelaySeconds());
											queueLength = frontier.getQueueLength();
											if (queueLength > 0) {
												continue;
											}
										}

										logger.info("All of the crawlers are stopped. Finishing the " + "process...");
										// At this step, frontier notifies the
										// threads that were
										// waiting for new URLs and they should
										// stop
										frontier.finish();
										for (T crawler : crawlers) {
											crawler.onBeforeExit();
											crawlersLocalData.add(crawler.getMyLocalData());
										}

										logger.info("Waiting for " + config.getCleanupDelaySeconds()
												+ " seconds before final clean up...");
										sleep(config.getCleanupDelaySeconds());

										frontier.close();
										docIdServer.close();
										pageFetcher.shutDown();

										finished = true;
										waitingLock.notifyAll();
										env.close();

										return;
									}
								}
							}
						}
					} catch (Exception e) {
						logger.error("Unexpected Error", e);
					}
				}
			});

			monitorThread.start();

			if (isBlocking) {
				waitUntilFinish();
			}

		} catch (Exception e) {
			logger.error("Error happened", e);
		}

	}
}
