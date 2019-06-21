package com.qiqiim.webserver.user.model;

/**
  
*/
public class AuthUser {
	private Integer id; //
	private String uuid; //
	private String name; // 唯一标识(微信唯一标记)
	private String unionid; // 唯一标识(微信唯一标记)

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return this.uuid;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setUnionid(String unionid){
		this.unionid = unionid;
	}

	public String getUnionid(){
		return this.unionid;
	}

	//扩展字段
	private Integer type; // 账号类型：10-个人账号，20-公司账号，90-管理员

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}