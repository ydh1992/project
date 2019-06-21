package com.qiqiim.webserver.user.service;

import com.qiqiim.webserver.user.model.Result;
import com.qiqiim.webserver.util.DataUtil;
import com.qiqiim.webserver.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Service
public class FileUploadService {
	@Autowired
	private InstancemessagedayService instancemessagedayService;

	/**
	 * 图片上传(服务器)
	 */
	public Result uploadFileToService(MultipartFile  file,HttpServletRequest request,int type) {
		String saveName= DateUtil.nows()+ DataUtil.createNums(7);
		String name = file.getOriginalFilename();
		saveName = saveName+name.substring(name.lastIndexOf("."));
		String filePath=null;
		if(type==1){
			filePath=request.getSession().getServletContext().getRealPath("upload/img/temp/");
		}else{
			filePath=request.getSession().getServletContext().getRealPath("upload/file/temp/");
		}
		File dirPath = new File(filePath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		// 创建文件
		File uploadFile = new File(filePath+"/"+saveName);
		OutputStream outputStream = null;
		InputStream inputStream=null;
		try {
			outputStream = new FileOutputStream(uploadFile);
			inputStream = file.getInputStream();
			FileCopyUtils.copy(inputStream, outputStream);
			return Result.putValue(DataUtil.mapOf("src", filePath + saveName));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return Result.putValue("1","上传过程中出现错误，请重新上传",null);
	}
}
