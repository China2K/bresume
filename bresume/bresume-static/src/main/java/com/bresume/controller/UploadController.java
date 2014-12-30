package com.bresume.controller;

import java.io.File;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.entity.sys.SysFile;
import com.bresume.core.service.sys.IFileService;

@Controller
@RequestMapping("/upload")
public class UploadController {

	private static final Logger logger = Logger.getLogger(UploadController.class);
	
	
	@Resource
	private IFileService fileService;


	@RequestMapping("/uploadImg")
	public @ResponseBody String uploadMaterialImg(
			HttpServletRequest req,HttpServletResponse resp,
//			@RequestParam(value = "imgFile",required= false) MultipartFile multipartFile,
			@RequestParam(value = "source", required= false,defaultValue = "unknown") String source,
			@RequestParam(value = "user", required= false,defaultValue = "system") String user

	) throws FileUploadException {
		String filePath = null;
		if (!ServletFileUpload.isMultipartContent(req)) {
			return filePath;
		}

		String fileName = null;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);
		ServletFileUpload upload = new ServletFileUpload(factory);
		Iterator<?> iter = upload.parseRequest(req).iterator();

		// 保存图片文件信息
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (!item.isFormField()) {
				fileName = item.getName();
				if (CommonUtils.isNotEmpty(fileName)) {
					
				}
			}
		}
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:8089");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		String imgId = null;
		req.getAttribute("imgFile");
		/*if (multipartFile != null) {
			//TODO　upload 
			saveFile(multipartFile, user, null);
		}*/
		return imgId;
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
