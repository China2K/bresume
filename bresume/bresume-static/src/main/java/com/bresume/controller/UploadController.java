package com.bresume.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.upload.FileUploadHandler;
import com.bresume.core.common.upload.UploadConfig.FileSource;
import com.bresume.core.common.upload.UploadConfig.FileType;
import com.bresume.core.model.entity.sys.SysFile;
import com.bresume.core.service.sys.IFileService;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController{

	private static final Logger logger = Logger.getLogger(UploadController.class);
	
	
	@Resource
	private IFileService fileService;

	/*
	 * file name: imgFile
	 * 
	 * */
	@RequestMapping("/uploadImg")
	public @ResponseBody JSONObject uploadMaterialImg (
			@RequestParam(value = "imgFile", required= false) MultipartFile imgFile,
			@RequestParam(value = "source", required= false,defaultValue = "unknown") String source,
			@RequestParam(value = "user", required= false,defaultValue = "system") String user

	) throws FileUploadException {
		
		logger.info("user:"+user+"上传文件，开始...");
		System.out.println(imgFile.getOriginalFilename());
		int index=imgFile.getOriginalFilename().lastIndexOf(".");
		String suffix=null;
		if(index>-1){
			suffix=imgFile.getOriginalFilename().substring(index+1);
		}
		Map<String,String> params = new HashMap<String, String>();
		params.put("user",user);
		
		String new_img_url=null;
		try {
			new_img_url = FileUploadHandler.uploadFile(imgFile.getBytes(), FileSource.valueOf(source.toUpperCase()), suffix, FileType.valueOf("IMAGE"), params);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.saveFile(imgFile, user, new_img_url);
		return toJSONResult(true, new_img_url);
	}
	
	@RequestMapping("/uploadFile")
	public @ResponseBody String uploadFile(
			@RequestParam(value = "imgFile") MultipartFile multipartFile,
			@RequestParam(value = "source", defaultValue = "unknown") String source,
			@RequestParam(value = "user", defaultValue = "system") String user

	) {
		String resp = null;
		String imgId = null;
		if (multipartFile != null) {
		}
		return imgId;
	}


	// 记录上传图片到db
	private void saveFile(final MultipartFile multipartFile,final String user,final String dbURL) {
		new Thread(new Runnable() {
			public void run() {
				SysFile file=new SysFile();
				file.setFileName(dbURL.substring(dbURL.lastIndexOf("/")+1));
				file.setCreatedBy(user);
				file.setCreatedTime(new Date());
				file.setFileSize(multipartFile.getSize());
				file.setFileType(FileType.IMAGE.getCode());
				file.setFileUrl(dbURL);
				file.setUploadName(multipartFile.getOriginalFilename());
				fileService.save(file);
			}
		}).start();
	}
	
}
