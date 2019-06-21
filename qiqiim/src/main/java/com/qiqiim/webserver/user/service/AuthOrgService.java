package com.qiqiim.webserver.user.service;

import com.qiqiim.webserver.user.mapper.AuthOrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthOrgService{

    @Autowired
    private AuthOrgMapper authOrgMapper;


	public List<Map<String, Object>> selectAllList(String uuId) {
		return authOrgMapper.selectAllList(uuId);
	}

}