package com.qiqiim.webserver.user.model;

import java.util.Date;

/**
即时消息--日交流表（instanceMessageDay） (当天+未读)消息
*/
public class Instancemessageday {
	private Integer id; //
	private String sendUserId; // 发送人uuid
	private String recUserId; // 接收人uuid
	private Date sendTime; // 发送时间
	private Integer isRead; // 是否已读取（0-未读，1-已读）
	private String context; // 消息内容
	private Integer msgType; // 消息类型 0：文本 1：图片  2：文件
	private String Prepared1; // 预留字段
	private String Prepared2; //
	private String Prepared3; //

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setSendUserId(String sendUserId){
		this.sendUserId = sendUserId;
	}

	public String getSendUserId(){
		return this.sendUserId;
	}

	public void setRecUserId(String recUserId){
		this.recUserId = recUserId;
	}

	public String getRecUserId(){
		return this.recUserId;
	}

	public void setSendTime(Date sendTime){
		this.sendTime = sendTime;
	}

	public Date getSendTime(){
		return this.sendTime;
	}

	public void setIsRead(Integer isRead){
		this.isRead = isRead;
	}

	public Integer getIsRead(){
		return this.isRead;
	}

	public void setContext(String context){
		this.context = context;
	}

	public String getContext(){
		return this.context;
	}

	public void setMsgType(Integer msgType){
		this.msgType = msgType;
	}

	public Integer getMsgType(){
		return this.msgType;
	}

	public void setPrepared1(String Prepared1){
		this.Prepared1 = Prepared1;
	}

	public String getPrepared1(){
		return this.Prepared1;
	}

	public void setPrepared2(String Prepared2){
		this.Prepared2 = Prepared2;
	}

	public String getPrepared2(){
		return this.Prepared2;
	}

	public void setPrepared3(String Prepared3){
		this.Prepared3 = Prepared3;
	}

	public String getPrepared3(){
		return this.Prepared3;
	}
	
	//isRead  是否已读取（0-未读，1-已读）
	public static enum ISREAD_ENUM {
		NO(0), YES(1);
		private int isRead;

		private ISREAD_ENUM(int isRead) {
			this.isRead = isRead;
		}

		public int getIsRead() {
			return isRead;
		}
	}
	//msgType; // 消息类型 0：文本 1：图片  2：文件
	public static enum MSGTYPE_ENUM {
		TXT(0), IMG(1), FILE(2);
		private int msgType;

		private MSGTYPE_ENUM(int msgType) {
			this.msgType = msgType;
		}
		public int getMsgType() {
			return msgType;
		}
	}
}