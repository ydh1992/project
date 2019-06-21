package com.qiqiim.webserver.user.model;

import java.util.Date;

public class AuthOrg{
	private Integer id; // 
	private Integer parentId; // 上级机构id
	private String uuid; // 唯一标识
	private String phone; // 机构电话--注册手机号
	private String password; //
	private String moneyPWD; // 支付密码
	private String businessLicense; // 营业执照照片存储地址
	private String regNum; // 统一社会信用代码
	private String name; // 机构名称
	private String type; // 公司类型
	private String legalPerson; // 法定代表人
	private String capital; // 注册资本
	private String establishDate; // 成立日期
	private String validPeriod; // 营业期限
	private String address; // 公司地址
	private String business; // 经营范围
	private String fax; // 传真
	private String remark; // 备注
	private String wxOpenId; // 唯一标识(微信唯一标记)
	private String level; // 机构级别
	private String label; // 标签
	private Integer integral; // 积分
	private Integer balance; // 可用余额
	private Integer frozenBalance; // 冻结金额
	private Integer createId; // 创建人id
	private Date createTime; // 创建时间
	private Integer updateId; // 修改人id
	private Date updateTime; // 修改时间
	private String introduce; // 公司介绍
	private String indusType; // 行业限定
	private String del; // 删除状态：0-删除，1-未删除
	private String enable; // 可用状态：0-不可用，1-可用
	private String prepared1; // 预留字段
	private String prepared2; // 预留字段
	private String prepared3; // 预留字段

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}

	public Integer getParentId(){
		return this.parentId;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return this.uuid;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setMoneyPWD(String moneyPWD){
		this.moneyPWD = moneyPWD;
	}

	public String getMoneyPWD(){
		return this.moneyPWD;
	}

	public void setBusinessLicense(String businessLicense){
		this.businessLicense = businessLicense;
	}

	public String getBusinessLicense(){
		return this.businessLicense;
	}

	public void setRegNum(String regNum){
		this.regNum = regNum;
	}

	public String getRegNum(){
		return this.regNum;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setLegalPerson(String legalPerson){
		this.legalPerson = legalPerson;
	}

	public String getLegalPerson(){
		return this.legalPerson;
	}

	public void setCapital(String capital){
		this.capital = capital;
	}

	public String getCapital(){
		return this.capital;
	}

	public void setEstablishDate(String establishDate){
		this.establishDate = establishDate;
	}

	public String getEstablishDate(){
		return this.establishDate;
	}

	public void setValidPeriod(String validPeriod){
		this.validPeriod = validPeriod;
	}

	public String getValidPeriod(){
		return this.validPeriod;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return this.address;
	}

	public void setBusiness(String business){
		this.business = business;
	}

	public String getBusiness(){
		return this.business;
	}

	public void setFax(String fax){
		this.fax = fax;
	}

	public String getFax(){
		return this.fax;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setWxOpenId(String wxOpenId){
		this.wxOpenId = wxOpenId;
	}

	public String getWxOpenId(){
		return this.wxOpenId;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return this.level;
	}

	public void setLabel(String label){
		this.label = label;
	}

	public String getLabel(){
		return this.label;
	}

	public void setIntegral(Integer integral){
		this.integral = integral;
	}

	public Integer getIntegral(){
		return this.integral;
	}

	public void setBalance(Integer balance){
		this.balance = balance;
	}

	public Integer getBalance(){
		return this.balance;
	}

	public void setFrozenBalance(Integer frozenBalance){
		this.frozenBalance = frozenBalance;
	}

	public Integer getFrozenBalance(){
		return this.frozenBalance;
	}

	public void setCreateId(Integer createId){
		this.createId = createId;
	}

	public Integer getCreateId(){
		return this.createId;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateId(Integer updateId){
		this.updateId = updateId;
	}

	public Integer getUpdateId(){
		return this.updateId;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setIntroduce(String introduce){
		this.introduce = introduce;
	}

	public String getIntroduce(){
		return this.introduce;
	}

	public void setIndusType(String indusType){
		this.indusType = indusType;
	}

	public String getIndusType(){
		return this.indusType;
	}

	public void setDel(String del){
		this.del = del;
	}

	public String getDel(){
		return this.del;
	}

	public void setEnable(String enable){
		this.enable = enable;
	}

	public String getEnable(){
		return this.enable;
	}

	public void setPrepared1(String prepared1){
		this.prepared1 = prepared1;
	}

	public String getPrepared1(){
		return this.prepared1;
	}

	public void setPrepared2(String prepared2){
		this.prepared2 = prepared2;
	}

	public String getPrepared2(){
		return this.prepared2;
	}

	public void setPrepared3(String prepared3){
		this.prepared3 = prepared3;
	}

	public String getPrepared3(){
		return this.prepared3;
	}

}