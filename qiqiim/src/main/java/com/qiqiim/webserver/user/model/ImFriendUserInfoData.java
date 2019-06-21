package com.qiqiim.webserver.user.model;

import org.apache.commons.lang.StringUtils;


public class ImFriendUserInfoData {
	public Integer id;//好友ID
	public String username;//好友昵称
	public String avatar;//好友头像
	public String sign;//签名
	public String status="offline"; //若值为offline代表离线，online或者不填为在线

	public ImFriendUserInfoData() {
	}

	public ImFriendUserInfoData(Integer id, String username, String avatar, String status) {
		this.id = id;
		this.username = username;
		this.avatar = avatar;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvatar() {
		
		return  StringUtils.isNotEmpty(avatar)?avatar:"layui/images/0.jpg";//avatar;
	}
	public void setAvatar(String avatar) {
		
		this.avatar = avatar;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
 
 
	
}
