package com.wq.model;

public class UserComments {
    private Integer id;

    private String contentId;

    private Integer type;

    private String title;

    private String content;

    private Integer addTime;

    private Integer hot;

    private String playtime;

    private String keywords;

    private String starInfo;

    private String uid;

    private String suid;

    private String uname;
    
    private UserInfo userInfo;
    
    @Override
	public String toString() {
		return "UserComments [id=" + id + ", contentId=" + contentId + ", type=" + type + ", title=" + title
				+ ", content=" + content + ", addTime=" + addTime + ", hot=" + hot + ", playtime=" + playtime
				+ ", keywords=" + keywords + ", starInfo=" + starInfo + ", uid=" + uid + ", suid=" + suid + ", uname="
				+ uname + ", userInfo=" + userInfo + "]";
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId == null ? null : contentId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime == null ? null : playtime.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getStarInfo() {
        return starInfo;
    }

    public void setStarInfo(String starInfo) {
        this.starInfo = starInfo == null ? null : starInfo.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid == null ? null : suid.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }
}