package com.qiqiim.webserver.user.mapper;

import com.qiqiim.webserver.user.model.Instancemessageday;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InstancemessagedayMapper {

	long insertSelective(Instancemessageday entity);

	int updateByExampleSelective(Instancemessageday entity);

	void insertList(List<Instancemessageday> entity);

	int updatemsgstate(String recUserId);

	List<Map<String,Object>>  getOfflineMessageList(@Param("recUserId")String recUserId,@Param("types")int type);
}