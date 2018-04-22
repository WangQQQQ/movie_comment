package com.wq.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wq.model.UserComments;

@Component
public class Utils {

	public static Utils utils;

//	private static String STR_USER = "[            {                \"contentId\":\"84967506948\",                \"type\":1,                \"title\":\"\",                \"content\":\"以前不懂，看帖总是不回。一直没升级和增加经验，现在我明白了，反正回帖15字就可以升级，还可以有经验。升级又需要经验，于是我把这话复制下来，遇帖就回，捞完就闪 我只是来赚积分的[发呆]我也试试[愉快][愉快]以前不懂，看帖总是不回。一直没升级和增加经验，现在我明白了，反正回帖15字\",                \"addTime\":1524291307,                \"hot\":182102,                \"playTime\":-1,                \"userInfo\":{                    \"uid\":\"2081835261\",                    \"uidType\":0,                    \"suid\":\"2081835261\",                    \"uname\":\"kidd3166\",                    \"gender\":\"1\",                    \"icon\":\"https://img7.qiyipic.com/passport/20180109/59/7c/passport_2081835261_151550396510184_130_130.jpg\",                    \"profileUrl\":\"http://www.iqiyi.com/u/2081835261\",                    \"location\":null,                    \"qiyiVipInfo\":{                        \"level\":\"4\",                        \"vipType\":\"1\",                        \"status\":\"1\",                        \"type\":\"1\"                    },                    \"verifyInfo\":{                        \"spaceShowTemplate\":\"1\"                    },                    \"pps_vip_info\":{                        \"vip_type\":0                    },                    \"subAccount\":false                },                \"targetInfo\":null,                \"sourceInfo\":{                    \"text\":\"通过 爱奇艺\",                    \"icon\":\"\",                    \"link\":\"http://www.iqiyi.com/\"                },                \"counterList\":{                    \"forwards\":0,                    \"replies\":1,                    \"likes\":5,                    \"downs\":0,                    \"reads\":0,                    \"praises\":0,                    \"shares\":0,                    \"recommends\":0                },                \"status\":0,                \"replyContentId\":0,                \"appScore\":0,                \"resourceInfo\":{                    \"tvId\":761013000,                    \"qitanId\":38215298,                    \"categoryId\":1,                    \"imageInfo\":null,                    \"imageInfos\":null,                    \"videoInfo\":null,                    \"roleInfo\":null,                    \"mark\":null,                    \"atUsers\":null,                    \"detailUrl\":\"http://t.iqiyi.com/t/38215298/tweet/0\"                },                \"voteInfo\":null,                \"ppsResourceInfo\":null,                \"location\":null,                \"customExt\":\"\",                \"voiceUrl\":\"\",                \"ugcVideoInfo\":null,                \"ecScore\":null,                \"keywords\":\"\",                \"starInfo\":\"\",                \"relatedStar\":\"\",                \"filmInfo\":\"\",                \"highlight\":null,                \"floor\":0,                \"agree\":false,                \"replySourceComment\":null,                \"atNickNameUids\":null            }        ] ";

	private static String STR_USER = "[{\"contentId\":\"85062694948\",\"type\":1,\"title\":\"\",\"content\":\"超级无敌爆炸喜欢喜欢喜欢喜欢喜欢喜欢喜欢\",\"addTime\":1524386695,\"hot\":171701,\"playTime\":-1,\"userInfo\":{\"uid\":\"1370031908\",\"uidType\":0,\"suid\":\"1370031908\",\"uname\":\"爱吹口琴的尉迟飞绿\",\"gender\":\"0\",\"icon\":\"http://www.qiyipic.com/common/fix/headicons/male-130.png\",\"profileUrl\":\"http://www.iqiyi.com/u/1370031908\",\"location\":null,\"qiyiVipInfo\":{\"level\":\"4\",\"vipType\":\"1\",\"status\":\"1\",\"type\":\"1\"},\"verifyInfo\":{\"spaceShowTemplate\":\"1\"},\"pps_vip_info\":{\"vip_type\":0},\"subAccount\":false},\"targetInfo\":null,\"sourceInfo\":{\"text\":\"通过 爱奇艺\",\"icon\":\"\",\"link\":\"http://www.iqiyi.com/\"},\"counterList\":{\"forwards\":0,\"replies\":0,\"likes\":0,\"downs\":0,\"reads\":0,\"praises\":0,\"shares\":0,\"recommends\":0},\"status\":0,\"replyContentId\":0,\"appScore\":0.0,\"resourceInfo\":{\"tvId\":761013000,\"qitanId\":38215298,\"categoryId\":1,\"imageInfo\":null,\"imageInfos\":null,\"videoInfo\":null,\"roleInfo\":null,\"mark\":null,\"atUsers\":null,\"detailUrl\":\"http://t.iqiyi.com/t/38215298/tweet/0\"},\"voteInfo\":null,\"ppsResourceInfo\":null,\"location\":null,\"customExt\":\"\",\"voiceUrl\":\"\",\"ugcVideoInfo\":null,\"ecScore\":null,\"keywords\":\"\",\"starInfo\":\"\",\"relatedStar\":\"\",\"filmInfo\":\"\",\"highlight\":null,\"floor\":0,\"agree\":false,\"replySourceComment\":null,\"atNickNameUids\":null},{\"contentId\":\"85053641848\",\"type\":1,\"title\":\"\",\"content\":\"喜欢喜欢，完全符合喜欢喜剧的我，喜欢喜欢\",\"addTime\":1524379925,\"hot\":171646,\"playTime\":-1,\"userInfo\":{\"uid\":\"2268195342\",\"uidType\":0,\"suid\":\"2268195342\",\"uname\":\"rainbow虹语\",\"gender\":\"0\",\"icon\":\"http://img7.qiyipic.com/passport/20170704/a1/0b/passport_2268195342_149918080613135_130_130.jpg\",\"profileUrl\":\"http://www.iqiyi.com/u/2268195342\",\"location\":null,\"qiyiVipInfo\":{\"level\":\"2\",\"vipType\":\"1\",\"status\":\"1\",\"type\":\"1\"},\"verifyInfo\":{\"spaceShowTemplate\":\"1\"},\"pps_vip_info\":{\"vip_type\":0},\"subAccount\":false},\"targetInfo\":null,\"sourceInfo\":{\"text\":\"通过 爱奇艺视频 Android Phone\",\"icon\":\"http://www.qiyipic.com/common/fix/images_v6/icon/icon_from_phone.png\",\"link\":\"http://app.iqiyi.com/mobile/player/index.html#mobile\"},\"counterList\":{\"forwards\":0,\"replies\":0,\"likes\":4,\"downs\":0,\"reads\":0,\"praises\":0,\"shares\":0,\"recommends\":0},\"status\":0,\"replyContentId\":0,\"appScore\":0.0,\"resourceInfo\":{\"tvId\":761013000,\"qitanId\":761013000,\"categoryId\":1,\"imageInfo\":null,\"imageInfos\":null,\"videoInfo\":null,\"roleInfo\":null,\"mark\":null,\"atUsers\":null,\"detailUrl\":\"http://t.iqiyi.com/m/761013000/tweet/0\"},\"voteInfo\":null,\"ppsResourceInfo\":null,\"location\":null,\"customExt\":null,\"voiceUrl\":null,\"ugcVideoInfo\":null,\"ecScore\":null,\"keywords\":\"\",\"starInfo\":\"\",\"relatedStar\":\"\",\"filmInfo\":\"\",\"highlight\":null,\"floor\":0,\"agree\":false,\"replySourceComment\":null,\"atNickNameUids\":null}]";
	// WebApplicationContext wac =
	// ContextLoader.getCurrentWebApplicationContext();
	// SqlSessionFactory sf = (SqlSessionFactory)
	// wac.getBean("sqlSessionFactory");
	// SqlSession session = sf.openSession();
	public Utils() {
	}

	// public static Utils getUtils(){
	// return utils;
	// }

	// @Autowired
	// MovieService movieService;
	//
	// public MovieService getMovieService(){
	// return movieService;
	// }
	public static void main(String[] args) throws Exception {
		 List<UserComments> user = JSON.parseObject(STR_USER,new TypeReference<List<UserComments>>() {});
//		 for (UserInfo u : user) {
//		 }
		
//		UserInfo user = JSON.parseObject(STR_USER,UserInfo.class);
		 System.out.println(user.get(0).toString());

//		URL url = new URL(
//				"http://api-t.iqiyi.com/qx_api/comment/get_video_comments?albumid=761013000&categoryid=1&cb=fnsucc&need_subject=true&need_total=1&page=7&page_size=200&qitan_comment_type=1&qitancallback=fnsucc&qitanid=38215298&qypid=01010011010000000000&sort=hot&t=1524381676819&tvid=761013000");
//
//		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//		urlConn.setDoOutput(true);
//		urlConn.setDoInput(true);
//		urlConn.setUseCaches(false);
//		urlConn.setRequestProperty("Content-type", "application/x-java-serialized-object");
//		urlConn.setRequestMethod("GET");
//		urlConn.setConnectTimeout(5000);
//		urlConn.setReadTimeout(30000);
//		urlConn.connect();
//		int responseCode = urlConn.getResponseCode();
//		if (responseCode == HttpURLConnection.HTTP_OK) {
//			// get inputStream
//			InputStream inputStream = urlConn.getInputStream();
//
//			StringBuilder totleString = new StringBuilder();
//			String line = "";
//			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//			while ((line = reader.readLine()) != null) {
//				System.out.println(line.substring(line.indexOf("\"comments\":"), line.indexOf(",\"count\"")));
//			}
//			reader.close();
//			inputStream.close();
//			// finish, close connection.
//			urlConn.disconnect();
//		}
	}
}
