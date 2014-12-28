package com.bresume.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.FileUploadUtils;
import com.bresume.upload.UploadConfig.FileSource;

public class FileUploadHandler {

	private static final Logger logger = Logger
			.getLogger(FileUploadHandler.class);

	public static final Properties uploadConf = new Properties();

	private static boolean uploadEnable = false;

	public static UploadConfig UPLOAD_CONFIG;

	private static int index = 0;

	private static final Object LOCK = new Object();

	static {
		InputStream is = FileUploadHandler.class
				.getResourceAsStream("/upload.properties");
		if (is != null) {
			uploadEnable = true;
			UPLOAD_CONFIG = new UploadConfig();
			try {
				uploadConf.load(is);

				UPLOAD_CONFIG.setRootFileDir(uploadConf
						.getProperty("root.file.dir"));
				UPLOAD_CONFIG.setAdminFileDir(uploadConf
						.getProperty("admin.file.dir"));
				UPLOAD_CONFIG.setDownloadParamName(uploadConf
						.getProperty("download.param.name"));
				UPLOAD_CONFIG.setPortalFileDir(uploadConf
						.getProperty("portal.file.dir"));
				UPLOAD_CONFIG.setStaticUrlPrefix(uploadConf
						.getProperty("static.url.prefix"));
				UPLOAD_CONFIG.setSystemFileDir(uploadConf
						.getProperty("system.file.dir"));

				String types = uploadConf.getProperty("image.type");
				UPLOAD_CONFIG.setImageTypes(Arrays.asList(types.split(",")));

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
	}

	public static String getDir(Map<String, String> map, String dir) {
		if (CommonUtils.isEmpty(map) || CommonUtils.isEmpty(dir)
				|| dir.indexOf("{") < 0) {
			return dir;
		}
		// 遍历Map中的所有Key，将得到的value值替换模板字符串中的变量值
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			dir = dir.replace("{" + key + "}", map.get(key));
		}
		return dir;
	}

	/**
	 * 
	 * @param img
	 * @param dir
	 *            if null,is default dir else storage.dir+dir
	 * @return
	 */
	public static String uploadImg(byte[] img, Map<String, String> params,
			FileSource source) {
		if (uploadEnable) {
			StringBuilder imgId = new StringBuilder();
			if (FileUploadUtils.validate_Image(img)) {
				String imgRootDir = UPLOAD_CONFIG.getRootFileDir();

				String dir = UPLOAD_CONFIG.getFileDir(source);
				dir = getDir(params, imgRootDir);
				String finalDir = imgRootDir + dir;
				File imgFileDir = new File(finalDir);
				if (!imgFileDir.exists()) {
					imgFileDir.mkdirs();
				}
				imgId.append(System.currentTimeMillis());
				imgId.append("_");
				imgId.append(getIndex());
				imgId.append(".img");
				File imgFile = new File(finalDir + imgId.toString());
				try {
					FileOutputStream fos = new FileOutputStream(imgFile);
					fos.write(img);
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}

			} else {
				try {
					throw new Exception("File doing uploading is not a Image!");
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
			return imgId.toString();
		} else {
			try {
				throw new Exception(
						"can't find upload.properties ,  upload is not enable!");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String uploadFile(byte[] file, Map<String, String> params,
			FileSource source) throws Exception {
		if (uploadEnable) {
			String fileRootDir = UPLOAD_CONFIG.getRootFileDir();
			String dir = UPLOAD_CONFIG.getFileDir(source);
			dir = getDir(params, fileRootDir);

			String finalDir = fileRootDir + dir;
			File fileDir = new File(finalDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			String fileName = System.currentTimeMillis() + "_" + getIndex();
			File upload_file = new File(finalDir + fileName);
			try {
				FileOutputStream fos = new FileOutputStream(upload_file);
				fos.write(file);
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
			return dir + fileName;
		} else {
			try {
				throw new Exception(
						"can't find upload.properties ,  upload is not enable!");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return null;
	}

	private static int getIndex() {
		int i = index;
		synchronized (LOCK) {
			if (index == 9) {
				index = 0;
			} else {
				index++;
			}
		}
		return i;
	}

	public static boolean isUploadEnabled() {
		return uploadEnable;
	}
}
