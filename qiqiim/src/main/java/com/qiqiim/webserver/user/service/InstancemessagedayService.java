package com.qiqiim.webserver.user.service;

import com.qiqiim.constant.Constants;
import com.qiqiim.webserver.user.mapper.InstancemessagedayMapper;
import com.qiqiim.webserver.user.model.Instancemessageday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class InstancemessagedayService{

    @Autowired
    private InstancemessagedayMapper instancemessagedayMapper;

	//添加
	@Transactional
	public long insertSelective(Instancemessageday record) {
        return instancemessagedayMapper.insertSelective(record);
	}

	public int updateByExampleSelective(Instancemessageday entity) {
		return instancemessagedayMapper.updateByExampleSelective(entity);
	}


	public int updatemsgstate(String recUserId) {
		return instancemessagedayMapper.updatemsgstate(recUserId);
	}


	public List<Map<String, Object>> getOfflineMessageList(String recUserId,int type) {
		List<Map<String, Object>> list=instancemessagedayMapper.getOfflineMessageList(recUserId, type);
		if(list!=null&&list.size()>0){
			list.forEach(e->{
				e.put("avatar", Constants.Other.USER_AVATAR);
			});
			//更新状态为已读状态
			instancemessagedayMapper.updatemsgstate(recUserId);
			return list;
		}
		return null;
	}

	@Transactional
	public void insertList(List<Instancemessageday> entity){
		instancemessagedayMapper.insertList(entity);
	}
}