package com.qiqiim.webserver.user.controller;

import com.qiqiim.constant.Constants;
import com.qiqiim.server.connertor.ImConnertor;
import com.qiqiim.server.model.MessageWrapper;
import com.qiqiim.server.model.Session;
import com.qiqiim.server.model.proto.MessageBodyProto;
import com.qiqiim.server.model.proto.MessageProto;
import com.qiqiim.server.session.SessionManager;
import com.qiqiim.server.session.impl.SessionManagerImpl;
import com.qiqiim.webserver.base.controller.BaseController;
import com.qiqiim.webserver.user.model.AuthUser;
import com.qiqiim.webserver.user.model.ImFriendUserInfoData;
import com.qiqiim.webserver.user.model.Result;
import com.qiqiim.webserver.user.service.*;
import com.qiqiim.webserver.util.DataUtil;
import com.qiqiim.webserver.util.Pager;
import com.qiqiim.webserver.util.Query;
import com.qiqiim.webserver.util.RedisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ImController extends BaseController{
	@Autowired
	private ImConnertor connertor;
	@Autowired
	private CodeService codeService;
	@Autowired
	private SessionManager sessionManager;
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private AuthOrgService authOrgService;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private InstancemessagedayService instancemessagedayService;
	@Autowired
	private InstancemessagelogService instancemessagelogService;

	/**
	 * 单聊
	 */
	@RequestMapping("/chat")
	public String chat(@RequestParam Map<String, Object> params,HttpServletRequest request){
		request.setAttribute("allsession", sessionManager.getSessions());
		return "chat";
	}

	/**
	 * 登录IM
	 */
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public String login(@RequestParam String uuid,@RequestParam String unionid,@RequestParam String ownerOrCompany,HttpServletRequest request){
		if(StringUtils.isBlank(uuid)&&StringUtils.isBlank(unionid)&&StringUtils.isBlank(ownerOrCompany)){
			return null;
		}
		//判断是否登录
		String key=String.format(Constants.Other.REDIS_USER_KEY,uuid,unionid);
		String str=RedisUtil.get(key);
		if(StringUtils.isBlank(str)){
			return "layim";
		}
		JSONObject json=JSONObject.fromObject(str);
		setLoginUser(json,ownerOrCompany);
		return "layim";
	}
	
	
	/** 
	 *  取得所有聊天用户
	 */
	@RequestMapping(value = "/getusers", produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object getAllUser(HttpServletResponse response,HttpServletRequest request) throws Exception{
		    // 数据格式请参考文档  http://www.layui.com/doc/modules/layim.html
		AuthUser  user = getLoginUser();
		if(getLoginUser()==null) return null;
		//获取用户分组 及用户
		JSONArray array=JSONArray.fromObject(Constants.Other.GROUP);
		List<Map<String, Object>> userList=authUserService.selectAllList(user.getUuid());//个人
		List<Map<String, Object>> orgList=authOrgService.selectAllList(user.getUuid());//公司
		//获取已登录用户，添加登录状态
		Map<String, Session> sessions=SessionManagerImpl.sessions;
		sessions.keySet().stream().forEach(o->{
			userList.stream().forEach(e->{
				e.put("avatar",Constants.Other.USER_AVATAR);
				if(o.equals(e.get("id").toString())){
					e.put("status",Constants.Other.USER_ONLINE);
				}else{
					e.put("status",Constants.Other.USER_OFFLINE);
				}
			});
			orgList.stream().forEach(e->{
				e.put("avatar",Constants.Other.USER_AVATAR);
				if(o.equals(e.get("id").toString())){
					e.put("status",Constants.Other.USER_ONLINE);
				}else{
					e.put("status",Constants.Other.USER_OFFLINE);
				}
			});
		});
		array.forEach(e->{
			JSONObject jsonObject=(JSONObject)e;
			// 0-个人，1-企业，2-管理员
			if(jsonObject.getInt("id")==Constants.Other.COMPANY_TYPE&&orgList.size()>0){
				jsonObject.put("list",orgList);
				jsonObject.getJSONArray("list").add(orgList);
			}
			if(jsonObject.getInt("id")==Constants.Other.PERSONAL_TYPE&&userList.size()>0){
				jsonObject.put("list", userList);
			}
		});
		//暂时没有群组
		return JSONObject.fromObject(Result.putValue(DataUtil.mapOf("mine", new ImFriendUserInfoData(user.getId(), user.getName(), Constants.Other.USER_AVATAR, "online"),
				"friend", array)));
	}
	
	
	/** 
	 * 图片上传
	 */
	@RequestMapping(value = "/imgupload", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadImgFile(@RequestParam MultipartFile  file, HttpServletRequest request) throws Exception {
		AuthUser  user = getLoginUser();
		if(getLoginUser()==null) return null;
		Session session=sessionManager.getSession(user.getUuid().toString());
		if(session==null) return null;
		return  JSONObject.fromObject(fileUploadService.uploadFileToService(file, request,1));
	}
	
	
	/** 
	 * 文件上传
	 */
	@RequestMapping(value = "/fileupload", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadAllFile(@RequestParam MultipartFile  file, HttpServletRequest request) throws Exception {
		AuthUser  user = getLoginUser();
		if(getLoginUser()==null) return null;
		Session session=sessionManager.getSession(user.getUuid().toString());
		if(session==null) return null;
		return  JSONObject.fromObject(fileUploadService.uploadFileToService(file,request,2));
	}

	/**
	 * 取得离线消息
	 */
	@RequestMapping(value = "/getofflinemsg", produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object userMessageCount(HttpServletRequest request) throws Exception{
		List<Map<String, Object>> list=null;
		if(getLoginUser()!=null){
			list=instancemessagedayService.getOfflineMessageList(getLoginUser().getId().toString(),getLoginUser().getType());
			if(list!=null){
				return JSONArray.fromObject(list);
			}
		}
		return null;
	} 
	
	/**
	 * 聊天记录
	 */
	@RequestMapping(value = "/historymessageajax", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object userHistoryMessages(HttpServletRequest request) throws Exception{
		if(getLoginUser()!=null){
			Map<String,Object> map =DataUtil.mapOf("page", getSkipToPage(),"limit", getPageSize(),"types", getLoginUser().getType(),"sendUserId", getLoginUser().getId(),"recUserId", Long.parseLong(request.getParameter("id")));
			Map<String, List<Map<String, Object>>> resultMap = new HashMap();
			resultMap.put("data", instancemessagelogService.selectLogList(new Query(map)));
			return JSONObject.fromObject(resultMap);
		}
		return null;
	}
	
	/**
	 * 聊天记录页面
	 */
	@RequestMapping(value = "/historymessage", method = RequestMethod.GET)
	public String userHistoryMessagesPage(HttpServletRequest request) throws Exception{
		if(getLoginUser()!=null){
			int totalsize = instancemessagelogService.countLog(DataUtil.mapOf("sendUserId", getLoginUser().getId(), "recUserId", Long.parseLong(request.getParameter("id"))));
			Pager pager = new Pager(getSkipToPage(),getPageSize(),totalsize);
			request.setAttribute("pager", pager);
			return "/historymessage";
		}
		return null;
	}

	/**
	 * 过滤关键字
	 */
	@RequestMapping(value = "/getKeyword", method = RequestMethod.GET)
	@ResponseBody
	public Object getKeyword(){
		return JSONObject.fromObject(codeService.getKeyword());
	}

	//发送系统消息
	@RequestMapping(value = "/sedSystemMsg",produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object sedSystemMsg(@RequestParam String content,@RequestParam(defaultValue =Constants.Other.ADMIN_UUID ) String suuid,@RequestParam String ruuid,HttpServletRequest request){
		MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
		builder.setCmd(Constants.CmdType.MESSAGE);
		builder.setSender(Constants.Other.ADMIN_UUID);
		builder.setMsgtype(Constants.ProtobufType.NOTIFY);
		MessageBodyProto.MessageBody.Builder  msg =  MessageBodyProto.MessageBody.newBuilder();
		msg.setContent(content);
		builder.setContent(msg.build().toByteString());
		if(StringUtils.isNotEmpty(ruuid)){
			//推送到个人默认系统客服发送
			MessageWrapper msgWrapper = new MessageWrapper(MessageWrapper.MessageProtocol.NOTIFY,suuid,ruuid,builder);
			connertor.SyspushMessage(msgWrapper,content);
		}
		return JSONObject.fromObject(DataUtil.mapOf("code",0,"msg","操作成功"));
	}
}
