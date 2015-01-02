package com.bresume.controller;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bresume.core.model.entity.sys.SysFile;
import com.bresume.core.service.sys.IFileService;

@Controller
@RequestMapping("/upload")
public class UploadController {

	private static final Logger logger = Logger.getLogger(UploadController.class);
	
	
	@Resource
	private IFileService fileService;

	/*
	 * file name: imgFile
	 * 
	 * */
	@RequestMapping("/uploadImg")
	public @ResponseBody String uploadMaterialImg(
			@RequestParam(value = "imgFile", required= false) MultipartFile imgFile,
			@RequestParam(value = "source", required= false,defaultValue = "unknown") String source,
			@RequestParam(value = "user", required= false,defaultValue = "system") String user

	) throws FileUploadException {
		
		logger.info("user:"+user+"上传文件，开始...");
		File file= new File("");
		System.out.println(imgFile.getName());
		System.out.println(imgFile.getOriginalFilename());
		System.out.println(imgFile.getContentType());
		return null;
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
				file.setFileName(multipartFile.getOriginalFilename());
				file.setCreatedBy(user);
				file.setCreatedTime(new Date());
				file.setFileSize(multipartFile.getSize());
				file.setFileType(multipartFile.getContentType());
				file.setFileUrl(dbURL);
				file.setUploadName(multipartFile.getName());
			}
		}).start();
	}
}
