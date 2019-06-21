package com.qiqiim.webserver.user.model;

import com.qiqiim.webserver.util.DataUtil;


public class Result {
	 public String code="0";
	 public String msg="操作成功";
	 public Object data;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = DataUtil.isNotBlank(code)?code:"0";
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = DataUtil.isNotBlank(msg)?msg:"操作成功";
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public static Result putValue(Object data) {
		Result result =new Result();
		result.setData(data);
		return result;
	}

	public static Result putValue(String code, String msg, Object data) {
		Result result =new Result();
		result.setData(data);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
