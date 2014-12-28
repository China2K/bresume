package com.bresume.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bresume.upload.FileUploadHandler;
import com.bresume.upload.UploadConfig;

@Controller
@RequestMapping("/upload")
public class UploadController {

	private static final Logger logger = Logger.getLogger(UploadController.class);

	private static UploadConfig UPLOAD_CONFIG = FileUploadHandler.UPLOAD_CONFIG;

	@RequestMapping("/uploadImg")
	public @ResponseBody String uploadMaterialImg(
			@RequestParam("imgFile") MultipartFile multipartFile) {
		String resp = null;
		String imgId = null;
		if (multipartFile != null) {
		}
		return imgId;
	}

	public static void main(String[] args) {

	}

	// 记录上传图片到db
	private void saveFile(final String pubNumId, final String dirName,
			final String fileName, final long fileSize, final String fileLink) {
		new Thread(new Runnable() {
			public void run() {
			}
		}).start();
	}
}
