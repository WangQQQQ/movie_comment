package com.wq.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wq.mapper.UserCommentsMapper;
import com.wq.mapper.UserInfoMapper;
import com.wq.model.UserComments;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
public class CommentsCollectProcess extends BaseProcess implements Callable<Integer> {
	private static final Logger logger = LoggerFactory.getLogger(CommentsCollectProcess.class);

	private List<HttpURLConnection> urlConnList;
	
	private List<HttpURLConnection> errorConnList;

	private int rowCount = 0;
	
	private int commentsCount = 200;

	List<UserComments> userComments;

	private UserCommentsMapper commentMapper;
	
	private UserInfoMapper userInfoMapper;

	private SqlSession session;

	public CommentsCollectProcess(String tvid, SqlSessionTemplate sqlSessionTemplate) {
		super(tvid,sqlSessionTemplate);
		this.sqlSessionTemplate=sqlSessionTemplate;
	}

	@Override
	public Integer call() {

		init();

//		process(urlConnList);
		
		finish();

		return rowCount;
	}

	@Override
	protected void init() {
		urlConnList = new ArrayList<HttpURLConnection>();
		errorConnList = new ArrayList<HttpURLConnection>();
		userComments = new ArrayList<UserComments>();
		session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);

		commentMapper = session.getMapper(UserCommentsMapper.class);
		userInfoMapper = session.getMapper(UserInfoMapper.class);
		int retryNum = 0;
		try {
		initPageCount();
			for (int i = 1; i <= Math.floor(commentsCount/200); i++) {
				HttpURLConnection urlConn = getUrlConn(i, 200);
				String status = process(urlConn);
				if("B00003".equals(status)) {
					logger.info("B00003 error ,retry: " + ++retryNum);
					retry(urlConn, i, 200);
				}
			}
			if(commentsCount%200 != 0) {
				HttpURLConnection urlConn = getUrlConn((int) (Math.floor(commentsCount/200) + 1), commentsCount%200);
				String status = process(urlConn);
				if("B00003".equals(status)) {
					logger.info("B00003 error ,retry: " + ++retryNum);
					retry(urlConn, (int) (Math.floor(commentsCount/200) + 1), 200);
				}
			}
			
			logger.info("urlConnList ready, size : " + urlConnList.size());
		} catch (Exception e) {
			logger.error("error while init get_video_comments url connection: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void retry(HttpURLConnection urlConn, int page, int pageSize) throws Exception {
		urlConn = getUrlConn(page, 200);
		String status = process(urlConn);
		if("B00003".equals(status)) {
			retry(urlConn, page, 200);
		}
		logger.info("retry success... ");
	}
	
	private HttpURLConnection getUrlConn(int page, int pageSize) throws Exception {
		URL url = new URL(
				"http://api-t.iqiyi.com/qx_api/comment/get_video_comments?albumid=761013000&categoryid=1&cb=fnsucc&need_subject=true&need_total=1&page="
						+ page
						+ "&page_size=" + pageSize + "&qitan_comment_type=1&qitancallback=fnsucc&qitanid=38215298&qypid=01010011010000000000&sort=hot&t=1524381676819&tvid="
						+ tvid);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setDoOutput(true);
		urlConn.setDoInput(true);
		urlConn.setUseCaches(false);
		urlConn.setRequestProperty("Content-type", "application/x-java-serialized-object");
		urlConn.setRequestMethod("GET");
		urlConn.setConnectTimeout(5000);
		urlConn.setReadTimeout(30000);
		return urlConn;
	}
	
	private void initPageCount() throws Exception  {
		String line = "";
//		String count = "";//total comment
		HttpURLConnection urlConn = getUrlConn(1, 0);
		urlConn.connect();
		int responseCode = urlConn.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			// get inputStream
			InputStream inputStream = urlConn.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String count = "";
			
			if ((line = reader.readLine()) != null) {
				try {
					count = line.substring(line.indexOf("\"count\":") + 8, line.indexOf(",\"seeMoreUrl\""));
					commentsCount = Integer.parseInt(count);
					logger.info("*****total comments: " + commentsCount);
				} catch (NumberFormatException e) {
					logger.error("*****error when parse page count: " + count);
				}
			}
			reader.close();
			inputStream.close();
			// finish, close connection.
			urlConn.disconnect();
		}
	}

	@Override
	protected String process(HttpURLConnection urlConn) {
		int responseCode;
		try {
				urlConn.connect();
				responseCode = urlConn.getResponseCode();
				logger.info("responseCode is : " + responseCode);
				if (responseCode == HttpURLConnection.HTTP_OK) {
					// get inputStream
					InputStream inputStream = urlConn.getInputStream();

					String line = "";
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

					/**
					 * !!!aiqiyi get_video_comments api return one line only;
					 */
					if ((line = reader.readLine()) != null) {
						if ("{\"code\":\"B00003\",\"data\":\"\",\"msg\":\"\"}".equals(line)) {
//							logger.info("Error B00003............");
							//
//							errorConnList.add(getUrlConn());
							return "B00003";
						}
						line = line.substring(line.indexOf("\"comments\":") + 11, line.indexOf(",\"count\""));
						
						//replace emoji with #
						List<UserComments> comments = JSON.parseObject(line.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "#"), new TypeReference<List<UserComments>>() {
						});
						for (UserComments userCmomment : comments) {
							try {
								userCmomment.setUid(userCmomment.getUserInfo().getUid());
								commentMapper.insert(userCmomment);
								userInfoMapper.insert(userCmomment.getUserInfo());
								rowCount++;
							} catch (SQLException e) {
								logger.error("error while insert comment/userInfo into databse: " + e.getMessage());
							}
							//per 1000 rows commit.
							if ((rowCount > 0 && rowCount % 1000 == 0) || rowCount == commentsCount) {
								session.commit();
							}
//							userCommentsService.addUserComments(userCmomment);
//							userInfoService.addUserInfo(userCmomment.getUserInfo());
						}
					}
					reader.close();
					inputStream.close();
					// finish, close connection.
					urlConn.disconnect();
			}
		} catch (IOException e) {
			session.rollback();
			logger.error("error while get response from aiqiyi get_video_comments api: " + e.getMessage());
			e.printStackTrace();
		}
		return "success";
	}

	@Override
	protected void finish() {
		urlConnList = null;
		userComments = null;
		session = null;
		
		logger.info("comments/userInfo insert finish...");
	}

}
