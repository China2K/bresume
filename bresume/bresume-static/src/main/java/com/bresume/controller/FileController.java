package com.bresume.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bresume.upload.FileUploadHandler;
import com.bresume.upload.UploadConfig;

@Controller
@RequestMapping("/file")
public class FileController {

	private static final Logger logger = Logger.getLogger(FileController.class);

	private static UploadConfig UPLOAD_CONFIG = FileUploadHandler.UPLOAD_CONFIG;

}
