package com.qiqiim.webserver.user.mapper;

import java.util.List;
import java.util.Map;

public interface AuthUserMapper {

	List<Map<String, Object>> selectAllList(String uuId);

}