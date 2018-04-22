package com.wq.crawler4j.crawler;

import java.util.Set;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wq.model.MovieDesc;
import com.wq.service.MovieService;
import com.wq.service.impl.MovieServiceImpl;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MovieCrawler extends WebCrawler {
	private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

	private MovieService movieService;
	
	public MovieCrawler(MovieServiceImpl movieService) {
		this.movieService = movieService;
	}


	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
//		if (IMAGE_EXTENSIONS.matcher(href).matches()) {
//			return false;
//		}
		
		//only visit the provided page.
		return false;
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {

		
//		movieDescMapper = session.getMapper(MovieDescMapper.class);
		
		
		int docid = page.getWebURL().getDocid();
		String url = page.getWebURL().getURL();
		String domain = page.getWebURL().getDomain();
		String path = page.getWebURL().getPath();
		String subDomain = page.getWebURL().getSubDomain();
		String parentUrl = page.getWebURL().getParentUrl();
		String anchor = page.getWebURL().getAnchor();

		// logger.debug("Docid: {}", docid);
		// logger.info("URL: {}", url);
		// logger.debug("Domain: '{}'", domain);
		// logger.debug("Sub-domain: '{}'", subDomain);
		// logger.debug("Path: '{}'", path);
		// logger.debug("Parent page: {}", parentUrl);
		// logger.debug("Anchor text: {}", anchor);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String html = htmlParseData.getHtml();
			Document doc = Jsoup.parse(html);
			Set<WebURL> links = htmlParseData.getOutgoingUrls();
			Elements elm = doc.getElementsByClass("site-piclist_pic");
			for (Element element : elm) {
				MovieDesc movieDesc = new MovieDesc();
				movieDesc.setCrawlerJobUrl(url);
				movieDesc.setMovieHref(element.getElementsByClass("site-piclist_pic_link").attr("href"));
				movieDesc.setMovieName(element.getElementsByClass("site-piclist_pic_link").attr("title"));
				movieDesc.setMovieTvid(element.getElementsByClass("site-piclist_pic_link").attr("data-qidanadd-tvid"));
				movieDesc.setMovieImg(element.getElementsByAttribute("height").attr("src"));
				// TODO actors
				// StringBuilder actors = new StringBuilder();
				// for (Element element2 : element.getElementsByAttribute("em"))
				// {
				// if(!element2.text().contains("主演")){
				// actors.append(element2.text()).append(",");
				// }
				// }
				// movieDesc.setMovieActors(actors.toString());
				movieService.adadMovieDesc(movieDesc);
			}
			// logger.info(movieDetail.getElementsByAttribute("data-searchpingback-elem").attr("href"));;

			// logger.debug("Text length: {}", text.length());
			// logger.debug("Html length: {}", html.length());
			// logger.debug("Number of outgoing links: {}", links.size());
		}

		Header[] responseHeaders = page.getFetchResponseHeaders();
		if (responseHeaders != null) {
			logger.debug("Response headers:");
			for (Header header : responseHeaders) {
				logger.debug("\t{}: {}", header.getName(), header.getValue());
			}
		}

		logger.debug("=============");
	}
}
