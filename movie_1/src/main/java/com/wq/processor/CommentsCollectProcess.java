package com.wq.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wq.model.UserComments;
import com.wq.service.UserCommentsService;
import com.wq.service.UserInfoService;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
@Resource
public class CommentsCollectProcess extends BaseProcess implements Callable<Integer> {
	private static final Logger logger = LoggerFactory.getLogger(CommentsCollectProcess.class);

	private List<HttpURLConnection> urlConnList;

	private int rowCount = 0;

	List<UserComments> userComments;
	// private URL url = new
	// URL("http://api-t.iqiyi.com/qx_api/comment/get_video_comments?albumid=761013000&categoryid=1&cb=fnsucc&need_subject=true&need_total=1&page=7&page_size=200&qitan_comment_type=1&qitancallback=fnsucc&qitanid=38215298&qypid=01010011010000000000&sort=hot&t=1524381676819&tvid=761013000");

	@Autowired
	UserCommentsService userCommentsService;

	@Autowired
	UserInfoService userInfoService;

	public CommentsCollectProcess(String tvid, int pageCount) {
		super(tvid, pageCount);
		urlConnList = new ArrayList<HttpURLConnection>();
		userComments = new ArrayList<UserComments>();
	}

	@Override
	public Integer call() {

		init();

		process();

		return rowCount;
	}

	@Override
	protected void init() {
		try {
			for (int i = 1; i <= pageCount; i++) {
				URL url = new URL(
						"http:api-t.iqiyi.com/qx_api/comment/get_video_comments?albumid=761013000&categoryid=1&cb=fnsucc&need_subject=true&need_total=1&page="
								+ i
								+ "&page_size=200&qitan_comment_type=1&qitancallback=fnsucc&qitanid=38215298&qypid=01010011010000000000&sort=hot&t=1524381676819&tvid="
								+ tvid);
				urlConn = (HttpURLConnection) url.openConnection();
				urlConn.setDoOutput(true);
				urlConn.setDoInput(true);
				urlConn.setUseCaches(false);
				urlConn.setRequestProperty("Content-type", "application/x-java-serialized-object");
				urlConn.setRequestMethod("GET");
				urlConn.setConnectTimeout(5000);
				urlConn.setReadTimeout(30000);
				urlConnList.add(urlConn);
			}
			logger.info("urlConnList ready, size : " + urlConnList.size());
		} catch (Exception e) {
			logger.error("error while init get_video_comments url connection: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void process() {
		int responseCode;
		try {
			for (HttpURLConnection urlConn : urlConnList) {
				urlConn.connect();
				responseCode = urlConn.getResponseCode();
				logger.info("responseCode is : " + responseCode);
				if (responseCode == HttpURLConnection.HTTP_OK) {
					// get inputStream
					InputStream inputStream = urlConn.getInputStream();

					String line = "";
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

					// !!!warning, aiqiyi get_video_comments api return one line
					// only;
					if ((line = reader.readLine()) != null) {
						line = line.substring(line.indexOf("\"comments\":") + 11, line.indexOf(",\"count\""));
						List<UserComments> comments = JSON.parseObject(line, new TypeReference<List<UserComments>>() {
						});
						userComments.addAll(comments);
					}
					reader.close();
					inputStream.close();
					// finish, close connection.
					urlConn.disconnect();
				}
			}
		} catch (IOException e) {
			logger.error("error while get response from aiqiyi get_video_comments api: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void finish() {
		logger.info("going to insert UserComments and UserInfo to database, movie id : " + tvid);
		for (UserComments userComment : userComments) {
			userCommentsService.addUserComments(userComment);
			userInfoService.addUserInfo(userComment.getUserInfo());
		}
		logger.info("insert finish...");
	}

}
