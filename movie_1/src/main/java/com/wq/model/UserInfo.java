package com.wq.model;

public class UserInfo {
    private Integer id;

    private String uid;

    private Integer uidType;

    private String title;

    private String uname;

    private String gender;

    private String icon;

    private String profileUrl;

    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
	public String toString() {
		return "UserInfo [id=" + id + ", uid=" + uid + ", uidType=" + uidType + ", title=" + title + ", uname=" + uname
				+ ", gender=" + gender + ", icon=" + icon + ", profileUrl=" + profileUrl + ", location=" + location
				+ "]";
	}

	public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getUidType() {
        return uidType;
    }

    public void setUidType(Integer uidType) {
        this.uidType = uidType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl == null ? null : profileUrl.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}