package com.qiqiim.webserver.user.service;

import com.qiqiim.webserver.user.mapper.CodeMapper;
import com.qiqiim.webserver.util.DataUtil;
import com.qiqiim.webserver.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

    @Autowired
    private CodeMapper codeMapper;


	public String[] getKeyword() {
		String keyword= RedisUtil.get("keyword");
		if(DataUtil.isNotBlank(keyword)){
			return keyword.split(",");
		}else{
			RedisUtil.set("keyword",codeMapper.getKeyword());
		}
		return RedisUtil.get("keyword").split(",");
	}

}