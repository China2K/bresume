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

import com.bresume.core.common.base.controller.StaticController;
import com.bresume.core.common.constant.IConstants;
import com.bresume.core.common.upload.FileUploadHandler;
import com.bresume.core.common.upload.UploadConfig.FileSource;
import com.bresume.core.common.upload.UploadConfig.FileType;
import com.bresume.core.common.utils.security.Encrypt;
import com.bresume.core.model.entity.sys.SysFile;
import com.bresume.core.model.entity.sys.SysUser;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.service.sys.IFileService;
import com.bresume.core.service.sys.ISysUserService;
import com.bresume.core.service.user.IUserService;

@Controller
@RequestMapping("/upload")
public class UploadController extends StaticController {

	private static final Logger logger = Logger
			.getLogger(UploadController.class);

	@Resource
	private IFileService fileService;

	@Resource
	private IUserService userService;

	@Resource
	private ISysUserService sysUserService;

	/*
	 * file name: imgFile
	 */
	@RequestMapping("/uploadImg")
	public @ResponseBody JSONObject uploadMaterialImg(
			@RequestParam(value = "imgFile", required = false) MultipartFile imgFile,
			@RequestParam(value = "source", required = false, defaultValue = "unknown") String source,
			@RequestParam(value = "upload_info", required = true) String upload_info,
			@RequestParam(value = "upload_key", required = false) String key)
			throws FileUploadException {
		/*
		 * key 为临时前台权限控制 TODO 待修改
		 */
		String userinfo = Encrypt
				.decryptSSO(upload_info, IConstants.HELLO_WORD);
		/*String[] split = userinfo.split("_");
		if (split == null || split.length < 2) {
			logger.error("upload_info 错误:" + userinfo);
			return this.toJSONResult(false, "非法权限");
		}
*/
		String user = userinfo;
		String psw =null/* split[1]*/;

		if (!check(source, user, psw, key)) {
			return this.toJSONResult(false, "非法权限");
		}

		logger.info("user:" + user + "上传文件，开始...");
		System.out.println(imgFile.getOriginalFilename());
		int index = imgFile.getOriginalFilename().lastIndexOf(".");
		String suffix = null;
		if (index > -1) {
			suffix = imgFile.getOriginalFilename().substring(index + 1);
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", user);

		String new_img_url = null;
		try {
			new_img_url = FileUploadHandler.uploadFile(imgFile.getBytes(),
					FileSource.valueOf(source.toUpperCase()), suffix,
					FileType.valueOf("IMAGE"), params);
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
		String imgId = null;
		if (multipartFile != null) {
		}
		return imgId;
	}

	// 记录上传图片到db
	private void saveFile(final MultipartFile multipartFile, final String user,
			final String dbURL) {
		new Thread(new Runnable() {
			public void run() {
				SysFile file = new SysFile();
				file.setFileName(dbURL.substring(dbURL.lastIndexOf("/") + 1));
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

	private boolean check(String source, String id, String psw, String key) {
		if (source.equalsIgnoreCase("PORTAL")) {
			User user = userService.findOne(id);
			if(user!=null&&user.getId()!=null){
				return true;
			}
			/*if (user != null
					&& (user.getPassword().equals(psw)
							|| CommonUtils.isEmpty(user.getPassword()) || "whatadiors"
								.equals(key))) {
				return true;
			}*/
		} else if (source.equalsIgnoreCase("ADMIN")) {
			SysUser user = sysUserService.findOne(id);
		/*	if (user != null && user.getPassword().equals(psw)) {
				return true;
			}*/
			
			if(user!=null&&user.getId()!=null){
				return true;
			}
		}
		return false;
	}

}
