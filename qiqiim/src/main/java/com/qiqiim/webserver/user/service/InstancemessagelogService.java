package com.qiqiim.webserver.user.service;

import com.qiqiim.constant.Constants;
import com.qiqiim.webserver.user.mapper.InstancemessagelogMapper;
import com.qiqiim.webserver.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InstancemessagelogService {

    @Autowired
    private InstancemessagelogMapper instancemessagelogMapper;


	public List<Map<String, Object>> selectLogList(Map<String, Object> param) {
		List<Map<String, Object>> list=instancemessagelogMapper.selectLogList(param);
		if(list!=null&&list.size()>0){
			list.forEach(e->{
				e.put("avatar", Constants.Other.USER_AVATAR);
			});
			return list;
		}
		return null;
	}


	public int countLog(Map<String, Object> param) {
		return instancemessagelogMapper.countLog(param);
	}
}